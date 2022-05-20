package de.flozo.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Settings {


    public Map<String, String> getVerifiedPropertiesMap() {
        if (!checkNumericValues(getPropertiesMap())) {
            System.out.println("Possible type error in settings file!");
        }
        return getPropertiesMap();
    }

    private Map<String, String> getPropertiesMap() {
        return getProperties().entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> String.valueOf(e.getKey()),
                                e -> String.valueOf(e.getValue()),
                                (oldValue, newValue) -> oldValue, HashMap::new
                        )
                );
    }

    private Properties getProperties() {

        String homeDirectory = System.getProperty("user.home");
        String fileName = "letter.config";
        Path configDirectory = Paths.get(homeDirectory, ".config", "LetterGen", fileName);
        System.out.println("[config] Read from config file \"" + configDirectory + "\" ...");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configDirectory.toFile()));
        } catch (IOException e) {
            System.out.println("[config] [IOException] Failed to open config file!");
        }
        return properties;
    }


    private boolean checkNumericValues(Map<String, String> properties) {
        String[] numericKeys = {".width", ".height", ".x", ".y", ".x_shift", ".y_shift", ".thickness"};
        for (Map.Entry<String, String> property : properties.entrySet()) {
            for (String numericKey : numericKeys) {
                if (property.getKey().endsWith(numericKey)) {
                    if (!isNumeric(property.getValue())) {
                        System.out.println("[config] [error] Numeric value expected for property \"" + property.getKey() + "\"; found non-numeric value \"" + property.getValue() + "\"");
                        return false;
                    }
                    ;
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
