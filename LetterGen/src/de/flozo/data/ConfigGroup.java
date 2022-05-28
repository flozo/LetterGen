package de.flozo.data;

public enum ConfigGroup {

    LETTER_GENERAL("letter.general"),
    LETTER_GEOMETRY("letter.geometry"),
    LETTER_ENCLOSURES("letter.enclosures"),
    LETTER_ICONS("letter.icons"),
    SENDER_DATA("letter.sender"),
    RECEIVER_DATA("letter.receiver");


    private final String configGroup;

    ConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }

    public String getString() {
        return configGroup;
    }

}