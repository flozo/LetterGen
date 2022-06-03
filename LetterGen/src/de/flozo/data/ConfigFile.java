package de.flozo.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConfigFile {

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
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(configDirectory.toFile()), StandardCharsets.UTF_8)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("[config] [IOException] Failed to open config file!");
        }
        if (!checkNumericValues(properties) || !checkBooleanValues(properties)) {
            System.out.println("Possible type error in settings file!");
        }
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


    private Map<String, String> toCheckIfNumeric(Properties properties) {
        return properties.entrySet()
                .stream()
                .filter(entry -> numericEntryCondition().test(entry.getKey().toString()))
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        entry -> String.valueOf(entry.getValue())));
    }

    private Map<String, String> toCheckIfBoolean(Properties properties) {
        return properties.entrySet()
                .stream()
                .filter(entry -> booleanEntryCondition().test(entry.getKey().toString()))
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


    private Predicate<String> numericEntryCondition() {
        Predicate<String> isWidth = key -> key.endsWith(".width");
        Predicate<String> isHeight = key -> key.endsWith(".height");
        Predicate<String> isLength = key -> key.endsWith(".length");
        Predicate<String> isLineWidth = key -> key.endsWith(".line_width");
        Predicate<String> isSpacing = key -> key.endsWith(".spacing");
        Predicate<String> isX = key -> key.endsWith(".x");
        Predicate<String> isY = key -> key.endsWith(".y");
        Predicate<String> isXShift = key -> key.endsWith(".x_shift");
        Predicate<String> isYShift = key -> key.endsWith(".y_shift");
        Predicate<String> isBorderMargin = key -> key.startsWith("border_margin");
        return isWidth.or(isHeight).or(isLength).or(isLineWidth).or(isSpacing).or(isX).or(isY).or(isXShift).or(isYShift).or(isBorderMargin);
    }

    private Predicate<String> booleanEntryCondition() {
        return key -> key.endsWith(".on");
    }

}
