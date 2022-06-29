package de.flozo.data;

import de.flozo.io.ConfigDirectory;
import de.flozo.io.ConfigFile2;
import de.flozo.io.MasterConfigFile;
import de.flozo.latex.core.color.BrewerColor;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyMap2 {

    private final ConfigGroup configGroup;
    private final Map<String, String> properties;

    private PropertyMap2(ConfigGroup configGroup, Map<String, String> properties) {
        this.configGroup = configGroup;
        this.properties = properties;
    }


    public static PropertyMap2 createWithDefaults(ConfigGroup configGroup) {
        Map<String, String> propertiesRawMap = new HashMap<>();
        if (configGroup == ConfigGroup.LETTER_GEOMETRY) {
            for (Property property : LetterGeometryProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        } else if (configGroup == ConfigGroup.LETTER_COLORS) {
            for (Property property : LetterColorProperty.values()) {
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
        return new PropertyMap2(configGroup, propertiesRawMap);
    }


    public void updateWithConfigFileSettings() {
        ConfigDirectory configDirectory = ConfigDirectory.fromDefaultDirectory();
        System.out.println(configDirectory);
        MasterConfigFile masterConfigFile = MasterConfigFile.withDefaultFileName(configDirectory);
        masterConfigFile.readProperties();
        System.out.println(masterConfigFile);
        ConfigFile2 configFile2 = ConfigFile2.fromMasterConfig(masterConfigFile, configGroup);
        System.out.println(configFile2);
        configFile2.readProperties();
        configFile2.checkProperties();
        Properties configFileProperties = configFile2.getProperties();
        System.out.print("[config] Updating default settings in " + configGroup.getPropertyKey() + " ...");
        for (Map.Entry<Object, Object> entry : configFileProperties.entrySet()) {
            properties.replace(entry.getKey().toString(), entry.getValue().toString());
        }
        System.out.println("[config] done!");
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
        } else if (PropertyValueTypeCheck.isValidStandardColorValue().test(colorString)) {
            return StandardColor.fromString(colorString).orElse(StandardColor.DEFAULT);
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
