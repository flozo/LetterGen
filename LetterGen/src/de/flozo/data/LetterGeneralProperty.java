package de.flozo.data;

import java.util.Objects;

public enum LetterGeneralProperty implements Property {

    LANGUAGE("language", "en"),
    DATE_FORMAT("date_format", "%d.%m.%Y"),
    DRAFT_MODE_ON("draft_mode.on", false);

    private final String property;
    private final String stringValue;
    private final boolean booleanValue;

    LetterGeneralProperty(String property, String stringValue) {
        this.property = property;
        this.stringValue = stringValue;
        this.booleanValue = false;
    }

    LetterGeneralProperty(String property, boolean booleanValue) {
        this.property = property;
        this.stringValue = "";
        this.booleanValue = false;
    }


    @Override
    public String getPropertyKey() {
        return property;
    }

    public String getStringValue() {
        return stringValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public String getGenericStringValue() {
        return (Objects.equals(stringValue, "") ? String.valueOf(booleanValue) : stringValue);
    }


    @Override
    public String getEntry() {
        return property + " = " + getGenericStringValue();
    }
}
