package de.flozo.io;

import de.flozo.data.ConfigGroup;
import de.flozo.data.PropertyKeyTypeCheck;
import de.flozo.data.PropertyValueTypeCheck;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigFile {

    private final ConfigGroup configGroup;
    private final File configFile;
    private final Properties properties;

    private ConfigFile(ConfigGroup configGroup, File configFile) {
        this.configGroup = configGroup;
        this.configFile = configFile;
        this.properties = new Properties();
    }

    public static ConfigFile fromMasterConfig(MasterConfigFile masterConfigFile, ConfigGroup configGroup) {
        return new ConfigFile(configGroup, masterConfigFile.getConfigGroupFiles().get(configGroup));
    }

    public void readProperties() {
        if (!configFile.exists()) {
            System.out.println("[config] Config file \"" + configFile.getCompletePath() + "\" does not exist!");
            return;
        }
        System.out.print("[config] Read config file \"" + configFile.getCompletePath() + "\" ...");
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(configFile.getCompletePath().toFile()), StandardCharsets.UTF_8)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println();
            System.out.println("[config] [IOException] Failed to open config file!");
        }
        System.out.println(" done!");
    }

    public boolean checkProperties() {
        System.out.print("[config] Checking property types ...");
        if (!checkNumericValues(properties) || !checkBooleanValues(properties) || !checkColorValues(properties)) {
            System.out.println();
            System.out.println("[config] [warning] Possible type error in settings file!");
            return false;
        }
        System.out.println(" done! Property types are correct!");
        return true;
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

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "ConfigFile2{" +
                "configGroup=" + configGroup +
                ", configFile=" + configFile +
                ", properties=" + properties +
                '}';
    }
}
