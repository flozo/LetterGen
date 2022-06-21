package de.flozo.data;

import de.flozo.latex.core.color.BrewerColor;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertyMap implements PropertyKeyTypeCheck, PropertyValueTypeCheck {

    private final ConfigGroup configGroup;
    private Map<String, String> properties;

    public PropertyMap(ConfigGroup configGroup) {
        this.configGroup = configGroup;
        this.properties = new HashMap<>();
    }


    public void updateDefaults(Settings settings) {
        Map<String, String> updated = new HashMap<>(getDefaults());
        for (Map.Entry<String, String> entry : getFromFile(settings).entrySet()) {
            updated.replace(entry.getKey(), entry.getValue());
        }
        this.properties = updated;
    }

    public Map<String, String> getFromFile(Settings settings) {
        return settings.getRawMap(configGroup);
    }

    public Map<String, String> getDefaults() {
        Map<String, String> propertiesRawMap = new HashMap<>();
        if (configGroup == ConfigGroup.LETTER_GEOMETRY) {
            for (LetterGeometryProperty property : LetterGeometryProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        } else if (configGroup == ConfigGroup.LETTER_COLORS) {
            for (LetterColorProperty property : LetterColorProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        } else if (configGroup == ConfigGroup.SENDER_DATA || configGroup == ConfigGroup.RECEIVER_DATA) {
            for (AddressProperty property : AddressProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getStringValue());
            }
        } else if (configGroup == ConfigGroup.LETTER_GENERAL) {
            for (LetterGeneralProperty property : LetterGeneralProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
            }
        }
        return propertiesRawMap;
    }

//    public Map<String, GenericTypeValue> getTypedMap(Map<String, String> rawMap) {
//        Map<String, GenericTypeValue> typedMap = new HashMap<>();
//        typedMap.putAll(stringSubMap(rawMap).entrySet()
//                .stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, value -> new StringTypeValue(value.getValue()))));
//        typedMap.putAll(numericSubMap(rawMap).entrySet()
//                .stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, value -> new DoubleTypeValue(value.getValue()))));
//        return typedMap;
//    }

    public Map<String, String> stringSubMap(Map<String, String> rawMap) {
        return rawMap.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.numericEntryCondition().negate().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> numericSubMap(Map<String, String> rawMap) {
        return rawMap.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.numericEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> Double.parseDouble(value.getValue())));
    }

    public Map<String, Boolean> booleanSubMap(Map<String, String> rawMap) {
        return rawMap.entrySet()
                .stream()
                .filter(entry -> PropertyKeyTypeCheck.booleanEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> Boolean.parseBoolean(value.getValue())));
    }

    public Map<String, Color> colorSubMap(Map<String, String> rawMap) {
        Map<String, Color> map = new HashMap<>();
        for (Map.Entry<String, String> entry : rawMap.entrySet()) {
            if (PropertyKeyTypeCheck.colorEntryCondition().test(entry.getKey())) {
                map.put(entry.getKey(), parseColor(entry.getValue()));
//                    if (map.put(entry.getKey(), LetterColorProperty.fromString(entry.getKey()).orElseThrow(IllegalArgumentException::new).getColorValue()) != null) {
//                    throw new IllegalStateException("Duplicate key");
//                }
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        return map;
    }

    private Color parseColor(String colorString) {
        System.out.println(colorString);
        if (PropertyValueTypeCheck.validBrewerColorValue().test(colorString)) {
            return BrewerColor.parseColor(colorString);
        } else if (PropertyValueTypeCheck.validStandardColorValue().test(colorString)) {
            System.out.println(PropertyValueTypeCheck.validStandardColorValue().test(colorString));
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
