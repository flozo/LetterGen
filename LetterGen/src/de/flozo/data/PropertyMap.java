package de.flozo.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PropertyMap {

    private final ConfigGroup configGroup;
    private Map<String, String> properties;

    public PropertyMap(ConfigGroup configGroup) {
        this.configGroup = configGroup;
        this.properties = new HashMap<>();
    }


    public void updateDefaults(Settings settings) {
        Map<String, String> updated = new HashMap<>(getDefaults());
        for (Map.Entry<String,String> entry: getFromFile(settings).entrySet()) {
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
        } else if (configGroup == ConfigGroup.SENDER_DATA || configGroup == ConfigGroup.RECEIVER_DATA) {
            for (AddressProperty property : AddressProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(), property.getStringValue());
            }
        } else if (configGroup == ConfigGroup.LETTER_GENERAL) {
            for (LetterGeneralProperty property : LetterGeneralProperty.values()) {
                propertiesRawMap.put(property.getPropertyKey(),property.getGenericStringValue());
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
                .filter(entry -> numericEntryCondition().negate().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> numericSubMap(Map<String, String> rawMap) {
        return rawMap.entrySet()
                .stream()
                .filter(entry -> numericEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> Double.parseDouble(value.getValue())));
    }

    public Map<String, Boolean> booleanSubMap(Map<String, String> rawMap) {
        return rawMap.entrySet()
                .stream()
                .filter(entry -> booleanEntryCondition().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> Boolean.parseBoolean(value.getValue())));
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


    public Map<String, String> getProperties() {
        return properties;
    }
}
