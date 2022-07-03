package de.flozo.data;

import de.flozo.io.ConfigFile;
import de.flozo.io.MasterConfigFile;
import de.flozo.latex.core.FontSize;
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

//        Map<String, String> propertiesRawMap = new HashMap<>();
//        if (configGroup == ConfigGroup.LETTER_GEOMETRY) {
//            for (Property property : LetterGeometryProperty.values()) {
//                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
//            }
//        } else if (configGroup == ConfigGroup.LETTER_COLORS) {
//            for (Property property : LetterColorProperty.values()) {
//                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
//            }
//        } else if (configGroup == ConfigGroup.LETTER_FONTS) {
//            for (Property property : LetterFontProperty.values()) {
//                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
//            }
//        } else if (configGroup == ConfigGroup.SENDER_DATA || configGroup == ConfigGroup.RECEIVER_DATA) {
//            for (Property property : AddressProperty.values()) {
//                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
//            }
//        } else if (configGroup == ConfigGroup.LETTER_GENERAL) {
//            for (Property property : LetterGeneralProperty.values()) {
//                propertiesRawMap.put(property.getPropertyKey(), property.getGenericStringValue());
//            }
//        }
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

//    public void writeToFile() {
//        Map<ConfigGroup, List<String>> specificLines = new HashMap<>();
//        List<String> letterGeneralComments = new ArrayList<>();
//        letterGeneralComments.add("# Program info: " + VERSION_INFO_PDF_META_DATA);
//        letterGeneralComments.add("#");
//        letterGeneralComments.add("# File info:");
//        letterGeneralComments.add("# This is the config file for general letter settings.");
//        letterGeneralComments.add("#");
//        letterGeneralComments.add("# Format info:");
//        letterGeneralComments.add("# Please note also the general format info in \"master.config\".");
//        letterGeneralComments.add("# Keys containing \"on\", \"hide\", or \"show\" require boolean values \"true\" or \"false\".");
//        letterGeneralComments.add("# If a value is left empty, a default value is used automatically.");
//        letterGeneralComments.add("# Escape backslashes.");
//        letterGeneralComments.add("# LaTeX commands can be used where characters/strings are to be specified.");
//        letterGeneralComments.add("# LaTeX math symbols have to be surrounded with dollar signs.");
//        specificLines.put(ConfigGroup.LETTER_GENERAL, letterGeneralComments);
//
//        List<String> letterGeometryComments = new ArrayList<>();
//        letterGeometryComments.add("# Program info: " + VERSION_INFO_PDF_META_DATA);
//        letterGeometryComments.add("#");
//        letterGeometryComments.add("# File info:");
//        letterGeometryComments.add("# This is the config file for the letter geometry.");
//        letterGeometryComments.add("# It specifies the positions, widths, heights, lengths, and sizes of all letter elements.");
//        letterGeometryComments.add("#");
//        letterGeometryComments.add("# Format info:");
//        letterGeometryComments.add("# Please note also the general format info in \"master.config\".");
//        letterGeometryComments.add("# Values are numeric and can be integer or decimal. Use the point as decimal separator.");
//        letterGeometryComments.add("# If a value is left empty, a default value is used automatically.");
//        specificLines.put(ConfigGroup.LETTER_GEOMETRY, letterGeometryComments);
//
//        List<String> letterColorComments = new ArrayList<>();
//        letterColorComments.add("# Program info: " + VERSION_INFO_PDF_META_DATA);
//        letterColorComments.add("#");
//        letterColorComments.add("# File info:");
//        letterColorComments.add("# This is the config file for the letter colors.");
//        letterColorComments.add("# It specifies the background colors, border colors, and text colors of all letter elements.");
//        letterColorComments.add("#");
//        letterColorComments.add("# Format info:");
//        letterColorComments.add("# Please note also the general format info in \"master.config\".");
//        letterColorComments.add("# If a value is left empty, a default value is used automatically.");
//        letterColorComments.add("# Set value to \"none\" to achieve transparency.");
//        letterColorComments.add("#");
//        letterColorComments.add("# Choose color specifiers from the LaTeX standard color palette");
//        letterColorComments.add("# or from ColorBrewer's sequential and diverging color schemes (see https://colorbrewer2.org).");
//        letterColorComments.add("#");
//        letterColorComments.add("# Standard colors:");
//        letterColorComments.add("#");
//        letterColorComments.add("# black, blue, brown, cyan, darkgray, gray, green, lightgray, lime, magenta,");
//        letterColorComments.add("# olive, orange, pink, purple, red, teal, violet, white, yellow");
//        letterColorComments.add("#");
//        letterColorComments.add("# ColorBrewer colors:");
//        letterColorComments.add("#");
//        letterColorComments.add("# Color values are specified as: SchemeName-Letter; Examples: Oranges-D, Greys-K, PuBu-M, RdYlGn-F");
//        letterColorComments.add("# Sequential color schemes are:");
//        letterColorComments.add("# BuGn, PuRd, BuPu, RdPu, GnBu, YlGn, OrRd, YlGnBu, PuBu, YlOrBr, PuBnGn, YlOrRd, Blues, Greens, Greys, Oranges, Purples, Reds");
//        letterColorComments.add("# with available letters: A, B, C, D, E, F, G, H, I, J, K, L, M");
//        letterColorComments.add("# Diverging color schemes are:");
//        letterColorComments.add("# BrBG, RdGy, RdYlBu, PiYG, PuOr, RdYlGn, PRGn, RdBu, Spectral");
//        letterColorComments.add("# with available letters: A, B, C, D, E, F, G, H, I, J, K, L, M, N, O");
//        letterColorComments.add("# ColorBrewer's qualitative schemes are not (yet) supported.");
//        specificLines.put(ConfigGroup.LETTER_COLORS, letterColorComments);
//
//        List<String> letterFontComments = new ArrayList<>();
//        letterFontComments.add("# Program info: " + VERSION_INFO_PDF_META_DATA);
//        letterFontComments.add("#");
//        letterFontComments.add("# File info:");
//        letterFontComments.add("# This is the config file for the letter fonts.");
//        letterFontComments.add("# It specifies the font sizes in all letter elements.");
//        letterFontComments.add("#");
//        letterFontComments.add("# Format info:");
//        letterFontComments.add("# Please note also the general format info in \"master.config\".");
//        letterFontComments.add("# Values are LaTeX font size command names (without leading backslash).");
//        letterFontComments.add("# Choose from: tiny, scriptsize, footnotesize, small, normalsize, large, Large, LARGE, huge, Huge");
//        letterFontComments.add("# If a value is left empty, a default value is used automatically.");
//        specificLines.put(ConfigGroup.LETTER_FONTS, letterFontComments);
//
//        if (exists()) {
//            System.out.println("[config] Master config file \"" + getCompletePath() + "\" already exists!");
//            return;
//        }
//        System.out.println("[config] No master config file found. Creating new master config with default values.");
//        System.out.print("[config] Collecting default values ...");
//        masterConfigLines.add("");
//        masterConfigLines.addAll(Arrays.stream(ConfigGroup.values())
//                .map(value -> value.getPropertyKey() + " = " + value.getDefaultFileName())
//                .collect(Collectors.toList()));
//        System.out.println(" done!");
//        File masterConfigFile = File.fromPath(getCompletePath());
//        if (!masterConfigFile.writeLines(masterConfigLines)) {
//            System.out.println("[config] [error] Failed to write master config file!");
//        }
//
//    }


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
//        Map<String, Color> map = new HashMap<>();
//        for (Map.Entry<String, String> entry : properties.entrySet()) {
//            if (PropertyKeyTypeCheck.colorEntryCondition().test(entry.getKey())) {
//                map.put(entry.getKey(), parseColor(entry.getValue()));
//            }
//        }
//        return map;
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
