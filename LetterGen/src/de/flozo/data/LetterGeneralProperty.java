package de.flozo.data;

import java.util.Objects;

public enum LetterGeneralProperty implements Property {

    LANGUAGE("language", "en"),
    DATE_FORMAT("date_format", "%d.%m.%Y"),
    DRAFT_MODE_ON("draft_mode.on", false),
    LETTER_BODY_TEXT_FILE("letter.body.text_file", "letter_body.txt"),
    LETTER_SIGNATURE_FILE("letter.signature_file", ""),
    ENCLOSURE_STYLE_HIDE("enclosure.style.hide", false),
    ENCLOSURE_STYLE_SHOW_TAG("enclosure.style.show_tag", true),
    ENCLOSURE_STYLE_SHOW_NUMBER("enclosure.style.show_number", false),
    ENCLOSURE_STYLE_SHOW_TITLES("enclosure.style.show_titles", true),
    ENCLOSURE_STYLE_TAG_SEPARATOR("enclosure.style.tag_separator", ":"),
    ENCLOSURE_STYLE_TITLE_SEPARATOR("enclosure.style.title_separator", ",");


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
        this.booleanValue = booleanValue;
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

    @Override
    public String getGenericStringValue() {
        return (Objects.equals(stringValue, "") ? String.valueOf(booleanValue) : stringValue);
    }


    @Override
    public String getEntry() {
        return property + " = " + getGenericStringValue();
    }
}
