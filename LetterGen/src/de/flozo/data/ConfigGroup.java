package de.flozo.data;

public enum ConfigGroup {

//    MASTER("master"),
    LETTER_GENERAL("letter.general"),
    LETTER_GEOMETRY("letter.geometry"),
    LETTER_COLORS("letter.colors"),
    LETTER_ENCLOSURES("letter.enclosures"),
    LETTER_ICONS("letter.icons"),
    SENDER_DATA("letter.sender"),
    RECEIVER_DATA("letter.receiver");


    private final String configGroup;

    ConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }

    public String getPropertyKey() {
        return configGroup;
    }


}
