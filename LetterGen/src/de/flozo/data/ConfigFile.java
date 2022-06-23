package de.flozo.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigFile implements PropertyKeyTypeCheck, PropertyValueTypeCheck {

    public static final String HOME_DIR_SYSTEM_PROPERTY_NAME = "user.home";
    public static final String CONFIG_DIR = ".config";
    public static final String SUB_DIR = "LetterGen";
    private final String fileName;


    public ConfigFile(String fileName) {
        this.fileName = fileName;
    }


    public Properties getProperties() {
        String homeDirectory = System.getProperty(HOME_DIR_SYSTEM_PROPERTY_NAME);
        Path configDirectory = Paths.get(homeDirectory, CONFIG_DIR, SUB_DIR, fileName);
        System.out.println("[config] Read from config file \"" + configDirectory + "\" ...");
        Properties properties = new Properties();
        System.out.println("******************");
        System.out.println(fileName);
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(configDirectory.toFile()), StandardCharsets.UTF_8)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("[config] [IOException] Failed to open config file!");
        }
        System.out.println("[config] Checking property types ...");
        if (!checkNumericValues(properties) || !checkBooleanValues(properties) || !checkColorValues(properties)) {
            System.out.println("[config] [warning] Possible type error in settings file!");
        } else {
            System.out.println("[config] ... done! Property types are correct!");
        }
        System.out.println("[config] Type checking complete.");
        return properties;
    }


    private boolean checkNumericValues(Properties properties) {
        for (Map.Entry<String, String> entry : toCheckIfNumeric(properties).entrySet()) {
            if (!isNumeric(entry.getValue())) {
                System.out.println("[config] [error] Numeric value expected for property \"" + entry.getKey() + "\"; found non-numeric value \"" + entry.getValue() + "\"");
                return false;
            }
        }
        return true;
    }

    private boolean checkBooleanValues(Properties properties) {
        for (Map.Entry<String, String> entry : toCheckIfBoolean(properties).entrySet()) {
            if (!isBoolean(entry.getValue())) {
                System.out.println("[config] [error] Boolean value expected for property \"" + entry.getKey() + "\"; found non-boolean value \"" + entry.getValue() + "\"");
                return false;
            }
        }
        return true;
    }

    private boolean checkColorValues(Properties properties) {
        for (Map.Entry<String, String> entry : toCheckIfColor(properties).entrySet()) {
            if (!isColor(entry.getValue())) {
                System.out.println("[config] [error] Color value expected for property \"" + entry.getKey() + "\"; found non-color value \"" + entry.getValue() + "\"");
                return false;
            }
        }
        return true;
    }


    private Map<String, String> toCheckIfNumeric(Properties properties) {
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.numericEntryCondition().test(entry.getKey().toString()))
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        entry -> String.valueOf(entry.getValue())));
    }

    private Map<String, String> toCheckIfBoolean(Properties properties) {
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.booleanEntryCondition().test(entry.getKey().toString()))
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        entry -> String.valueOf(entry.getValue())));
    }

    private Map<String, String> toCheckIfColor(Properties properties) {
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.colorEntryCondition().test(entry.getKey().toString()))
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        entry -> String.valueOf(entry.getValue())));
    }


    private boolean isNumeric(String propertyValue) {
        if (propertyValue == null) {
            return false;
        }
        try {
            Double.parseDouble(propertyValue);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isBoolean(String propertyValue) {
        if (propertyValue == null) {
            return false;
        }
        try {
            Boolean.parseBoolean(propertyValue);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isColor(String propertyValue) {
        if (propertyValue == null) {
            return false;
        }
        return PropertyValueTypeCheck.isValidColorValue().test(propertyValue);
    }


}
