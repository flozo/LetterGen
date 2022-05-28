package de.flozo.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

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
        try {
            properties.load(new FileInputStream(configDirectory.toFile()));
        } catch (IOException e) {
            System.out.println("[config] [IOException] Failed to open config file!");
        }
        if (!checkNumericValues(properties)) {
            System.out.println("Possible type error in settings file!");
        }
        return properties;
    }


    private boolean checkNumericValues(Properties properties) {
        String[] numericKeys = {".width", ".height", ".length", ".x", ".y", ".x_shift", ".y_shift", ".line_width"};
        for (Map.Entry<Object, Object> property : properties.entrySet()) {
            for (String numericKey : numericKeys) {
                if (property.getKey().toString().endsWith(numericKey)) {
                    if (!isNumeric(property.getValue().toString())) {
                        System.out.println("[config] [error] Numeric value expected for property \"" + property.getKey() + "\"; found non-numeric value \"" + property.getValue() + "\"");
                        return false;
                    }
                }
            }
        }
        return true;
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

}
