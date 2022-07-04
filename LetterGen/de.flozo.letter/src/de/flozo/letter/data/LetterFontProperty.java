package de.flozo.letter.data;

import de.flozo.latex.core.FontSize;

public enum LetterFontProperty implements Property {

    HEADLINE_FONT_SIZE("headline.font.size", FontSize.LARGE2),
    SENDER_FONT_SIZE("sender.font.size", FontSize.DEFAULT),
    ADDRESS_FONT_SIZE("address.font.size", FontSize.DEFAULT),
    BACKADDRESS_FONT_SIZE("backaddress.font.size", FontSize.SCRIPT_SIZE),
    DATE_FIELD_FONT_SIZE("date.font.size", FontSize.DEFAULT),
    SUBJECT_FONT_SIZE("subject.font.size", FontSize.DEFAULT),
    BODY_FONT_SIZE("body.font.size", FontSize.DEFAULT),
    CLOSING_FONT_SIZE("closing.font.size", FontSize.DEFAULT),
    ENCLSOURES_FONT_SIZE("enclosures.font.size", FontSize.DEFAULT);

    private final String property;
    private final FontSize fontSize;

    LetterFontProperty(String property, FontSize fontSize) {
        this.property = property;
        this.fontSize = fontSize;
    }

    @Override
    public String getPropertyKey() {
        return property;
    }

    @Override
    public String getGenericStringValue() {
        return fontSize.getString();
    }

    @Override
    public String getEntry() {
        return property + " = " + getGenericStringValue();
    }


    @Override
    public String toString() {
        return "LetterFontProperty{" +
                "property='" + property + '\'' +
                ", fontSize=" + fontSize +
                '}';
    }
}
