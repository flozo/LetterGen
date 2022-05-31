package de.flozo.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PropertyMap {

    private final ConfigGroup configGroup;

    public PropertyMap(ConfigGroup configGroup) {
        this.configGroup = configGroup;
    }

//    public Map<String, GenericTypeValue> getMap() {
//        String[] numericKeys = {".width", ".height", ".length", ".x", ".y", ".x_shift", ".y_shift", ".line_width",
//                ".top", ".bottom", ".left", ".right"};
//        for (LetterGeometryProperty property : LetterGeometryProperty.values()) {
//            boolean isNumericKey = false;
//            for (String numericKey : numericKeys) {
//                if (property.getPropertyName().endsWith(numericKey)) {
//                    isNumericKey = true;
//                    break;
//                }
//            }
//            if (isNumericKey) {
//                propertyMap.put(property, new DoubleTypeValue(property.getNumericalValue()));
//            } else {
//                propertyMap.put(property, new StringTypeValue(property.getStringValue()));
//            }
//        }
//        return propertyMap;
//    }


    public Map<String, String> getRawMap() {
        Map<String, String> propertiesRawMap = new HashMap<>();
        if (configGroup == ConfigGroup.LETTER_GEOMETRY) {
            for (LetterGeometryProperty property : LetterGeometryProperty.values()) {
                propertiesRawMap.put(property.getPropertyName(), property.getGenericStringValue());
            }
        } else if (configGroup == ConfigGroup.SENDER_DATA || configGroup == ConfigGroup.RECEIVER_DATA) {
            for (AddressProperty property : AddressProperty.values()) {
                propertiesRawMap.put(property.getPropertyName(), property.getStringValue());
            }
        }
        return propertiesRawMap;
    }

    public Map<String,GenericTypeValue> getTypedMap(Map<String, String> rawMap) {
        Map<String, GenericTypeValue> typedMap = new HashMap<>();
        typedMap.putAll(stringSubMap(rawMap).entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, value -> new StringTypeValue(value.getValue()))));
        typedMap.putAll(numericSubMap(rawMap).entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, value -> new DoubleTypeValue(value.getValue()))));
        return typedMap;
    }

    public Map<String, String> stringSubMap(Map<String, String> rawMap) {
        return rawMap.entrySet()
                .stream()
                .filter(entry -> numericEntry().negate().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> numericSubMap(Map<String, String> rawMap) {
        return rawMap.entrySet()
                .stream()
                .filter(entry -> numericEntry().test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, value -> Double.parseDouble(value.getValue())));
    }

    private Predicate<String> numericEntry() {
        Predicate<String> isWidth = key -> key.endsWith(".width");
        Predicate<String> isHeight = key -> key.endsWith(".height");
        Predicate<String> isLength = key -> key.endsWith(".length");
        Predicate<String> isLineWidth = key -> key.endsWith(".line_width");
        Predicate<String> isX = key -> key.endsWith(".x");
        Predicate<String> isY = key -> key.endsWith(".y");
        Predicate<String> isXShift = key -> key.endsWith(".x_shift");
        Predicate<String> isYShift = key -> key.endsWith(".y_shift");
        Predicate<String> isBorderMargin = key -> key.startsWith("border_margin");
        return isWidth.or(isHeight).or(isLength).or(isLineWidth).or(isX).or(isY).or(isXShift).or(isYShift).or(isBorderMargin);
    }


}
