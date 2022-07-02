package de.flozo.data;

import de.flozo.latex.core.FontSize;

import java.util.Map;

public class LetterFont {

    private final FontSize headlineFontSize;
    private final FontSize senderFontSize;
    private final FontSize addressFontSize;
    private final FontSize backaddressFontSize;
    private final FontSize dateFieldFontSize;
    private final FontSize subjectFieldFontSize;
    private final FontSize bodyFontSize;
    private final FontSize closingFontSize;
    private final FontSize enclosuresFontSize;

    public LetterFont(PropertyMap propertyMap) {
        Map<String, FontSize> fontSizeMap = propertyMap.fontSizeSubMap();
        this.headlineFontSize = fontSizeMap.get(LetterFontProperty.HEADLINE_FONT_SIZE.getPropertyKey());
        this.senderFontSize = fontSizeMap.get(LetterFontProperty.SENDER_FONT_SIZE.getPropertyKey());
        this.addressFontSize = fontSizeMap.get(LetterFontProperty.ADDRESS_FONT_SIZE.getPropertyKey());
        this.backaddressFontSize = fontSizeMap.get(LetterFontProperty.BACKADDRESS_FONT_SIZE.getPropertyKey());
        this.dateFieldFontSize =fontSizeMap.get(LetterFontProperty.DATE_FIELD_FONT_SIZE.getPropertyKey());
        this.subjectFieldFontSize = fontSizeMap.get(LetterFontProperty.SUBJECT_FONT_SIZE.getPropertyKey());
        this.bodyFontSize = fontSizeMap.get(LetterFontProperty.BODY_FONT_SIZE.getPropertyKey());
        this.closingFontSize = fontSizeMap.get(LetterFontProperty.CLOSING_FONT_SIZE.getPropertyKey());
        this.enclosuresFontSize = fontSizeMap.get(LetterFontProperty.ENCLSOURES_FONT_SIZE.getPropertyKey());
    }

    public FontSize getHeadlineFontSize() {
        return headlineFontSize;
    }

    public FontSize getSenderFontSize() {
        return senderFontSize;
    }

    public FontSize getAddressFontSize() {
        return addressFontSize;
    }

    public FontSize getBackaddressFontSize() {
        return backaddressFontSize;
    }

    public FontSize getDateFieldFontSize() {
        return dateFieldFontSize;
    }

    public FontSize getSubjectFieldFontSize() {
        return subjectFieldFontSize;
    }

    public FontSize getBodyFontSize() {
        return bodyFontSize;
    }

    public FontSize getClosingFontSize() {
        return closingFontSize;
    }

    public FontSize getEnclosuresFontSize() {
        return enclosuresFontSize;
    }

    @Override
    public String toString() {
        return "LetterFont{" +
                "headlineFontSize=" + headlineFontSize +
                ", senderFontSize=" + senderFontSize +
                ", addressFontSize=" + addressFontSize +
                ", backaddressFontSize=" + backaddressFontSize +
                ", dateFieldFontSize=" + dateFieldFontSize +
                ", subjectFieldFontSize=" + subjectFieldFontSize +
                ", bodyFontSize=" + bodyFontSize +
                ", closingFontSize=" + closingFontSize +
                ", enclosuresFontSize=" + enclosuresFontSize +
                '}';
    }
}
