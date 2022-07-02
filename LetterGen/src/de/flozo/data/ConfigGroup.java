package de.flozo.data;

public enum ConfigGroup {

//    MASTER("master"),
    LETTER_GENERAL("letter.general", "letter_general"),
    LETTER_GEOMETRY("letter.geometry", "letter_geometry"),
    LETTER_COLORS("letter.colors", "letter_colors"),
    LETTER_FONTS("letter.fonts", "letter_fonts"),
    LETTER_ENCLOSURES("letter.enclosures", "letter_enclosures"),
    LETTER_ICONS("letter.icons", "icons"),
    SENDER_DATA("letter.sender", "sender_data"),
    RECEIVER_DATA("letter.receiver", "receiver_data");


    private final String configGroup;
    private final String defaultFileName;

    ConfigGroup(String configGroup, String defaultFileName) {
        this.configGroup = configGroup;
        this.defaultFileName = defaultFileName + ".config";
    }

    public String getPropertyKey() {
        return configGroup;
    }

    public String getDefaultFileName() {
        return defaultFileName;
    }

    @Override
    public String toString() {
        return "ConfigGroup{" +
                "configGroup='" + configGroup + '\'' +
                ", defaultFileName='" + defaultFileName + '\'' +
                '}';
    }
}
