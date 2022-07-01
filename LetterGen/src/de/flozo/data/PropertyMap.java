package de.flozo.data;

import de.flozo.io.ConfigFile;
import de.flozo.io.MasterConfigFile;
import de.flozo.latex.core.color.BrewerColor;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyMap {

    private final ConfigGroup configGroup;
    private final Map<String, String> properties;

    private PropertyMap(ConfigGroup configGroup, Map<String, String> properties) {
        this.configGroup = configGroup;
        this.properties = properties;
    }


    public static PropertyMap createWithDefaults(ConfigGroup configGroup) {
        Map<String, String> propertiesRawMap = new HashMap<>();
        if (configGroup == ConfigGroup.LETTER_GEOMETRY) {
            for (Property property : LetterGeometryProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        } else if (configGroup == ConfigGroup.LETTER_COLORS) {
            for (Property property : LetterColorProperty.values()) {
                System.out.println(property.getGenericStringValue());
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        } else if (configGroup == ConfigGroup.SENDER_DATA || configGroup == ConfigGroup.RECEIVER_DATA) {
            for (Property property : AddressProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        } else if (configGroup == ConfigGroup.LETTER_GENERAL) {
            for (Property property : LetterGeneralProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        }
        return new PropertyMap(configGroup, propertiesRawMap);
    }

    public static PropertyMap createFromFile(ConfigGroup configGroup, MasterConfigFile masterConfigFile) {
        ConfigFile configFile = ConfigFile.fromMasterConfig(masterConfigFile, configGroup);
        configFile.readProperties();
        configFile.checkProperties();
        Properties configFileProperties = configFile.getProperties();
        Map<String, String> propertiesRawMap = new HashMap<>();
        System.out.print("[config] Create settings from " + configGroup.getPropertyKey() + " ...");
        for (Map.Entry<Object, Object> entry : configFileProperties.entrySet()) {
            propertiesRawMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        System.out.println(" done!");
        return new PropertyMap(configGroup, propertiesRawMap);
    }

    public void updateWithConfigFileSettings(MasterConfigFile masterConfigFile) {
        ConfigFile configFile = ConfigFile.fromMasterConfig(masterConfigFile, configGroup);
        configFile.readProperties();
        configFile.checkProperties();
        Properties configFileProperties = configFile.getProperties();
        System.out.print("[config] Updating default settings in " + configGroup.getPropertyKey() + " ...");
        for (Map.Entry<Object, Object> entry : configFileProperties.entrySet()) {
            if (!entry.getValue().toString().isBlank()) {
                properties.replace(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        System.out.println(" done!");
    }


    public Map<String, String> keyValueMap() {
        return properties.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, String> stringSubMap() {
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.numericEntryCondition().negate().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> numericSubMap() {
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.numericEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> Double.parseDouble(value.getValue())));
    }

    public Map<String, Boolean> booleanSubMap() {
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.booleanEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> Boolean.parseBoolean(value.getValue())));
    }

    public Map<String, Color> colorSubMap() {
        Map<String, Color> map = new HashMap<>();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            if (PropertyKeyTypeCheck.colorEntryCondition().test(entry.getKey())) {
                map.put(entry.getKey(), parseColor(entry.getValue()));
            }
        }
        return map;
    }

    private Color parseColor(String colorString) {
        if (PropertyValueTypeCheck.isValidBrewerColorValue().test(colorString)) {
            return BrewerColor.parseColor(colorString);
        } else if (PropertyValueTypeCheck.isValidStandardColorValue().test(colorString) && StandardColor.fromString(colorString).isPresent()) {
            return StandardColor.fromString(colorString).get();
        }
        return null;
    }


    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "PropertyMap{" +
                "configGroup=" + configGroup +
                ", properties=" + properties +
                '}';
    }

}
