package de.flozo.data;

import de.flozo.io.ConfigFile;
import de.flozo.io.MasterConfigFile;
import de.flozo.latex.core.FontSize;
import de.flozo.latex.color.BrewerColor;
import de.flozo.latex.color.Color;
import de.flozo.latex.color.StandardColor;

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
        return new PropertyMap(configGroup, configGroup.getDefaultPropertyMap());
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
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.colorEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> parseColor(value.getValue())));
    }

    public Map<String, FontSize> fontSizeSubMap() {
        return properties.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.fontSizeEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> parseFontSize(value.getValue())));
    }


    private Color parseColor(String colorString) {
        if (PropertyValueTypeCheck.isValidBrewerColorValue().test(colorString)) {
            return BrewerColor.parseColor(colorString);
        } else if (PropertyValueTypeCheck.isValidStandardColorValue().test(colorString) && StandardColor.fromString(colorString).isPresent()) {
            return StandardColor.fromString(colorString).get();
        }
        return StandardColor.DEFAULT;
    }

    private FontSize parseFontSize(String fontSize) {
        return FontSize.getByValue(fontSize);
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
