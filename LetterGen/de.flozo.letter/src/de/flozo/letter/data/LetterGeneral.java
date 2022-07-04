package de.flozo.letter.data;

import java.util.Map;

public class LetterGeneral {

    private final String language;
    private final String dateFormat;
    private final String bodyTextFile;
    private final String signatureImageFile;
    private final boolean draftModeOn;
    private final boolean imagePlaceholderOn;
    private final boolean hyperlinksOn;
    private final boolean enclosureStyleHide;
    private final boolean enclosureStyleShowTag;
    private final boolean enclosureStyleShowNumber;
    private final boolean enclosureStyleShowTitles;
    private final String enclosureStyleTagSeparator;
    private final String enclosureStyleTitleSeparator;


    public LetterGeneral(PropertyMap propertyMap) {
        Map<String, String> stringSubMap = propertyMap.stringSubMap();
        Map<String, Boolean> booleanSubMap = propertyMap.booleanSubMap();
        this.language = stringSubMap.get(LetterGeneralProperty.LANGUAGE.getPropertyKey());
        this.dateFormat = stringSubMap.get(LetterGeneralProperty.DATE_FORMAT.getPropertyKey());
        this.bodyTextFile = stringSubMap.get(LetterGeneralProperty.LETTER_BODY_TEXT_FILE.getPropertyKey());
        this.signatureImageFile = stringSubMap.get(LetterGeneralProperty.LETTER_SIGNATURE_FILE.getPropertyKey());
        this.draftModeOn = booleanSubMap.get(LetterGeneralProperty.DRAFT_MODE_ON.getPropertyKey());
        this.imagePlaceholderOn = booleanSubMap.get(LetterGeneralProperty.IMAGE_PLACEHOLDER_ON.getPropertyKey());
        this.hyperlinksOn = booleanSubMap.get(LetterGeneralProperty.HYPERLINKS_ON.getPropertyKey());
        this.enclosureStyleHide = booleanSubMap.get(LetterGeneralProperty.ENCLOSURE_STYLE_HIDE.getPropertyKey());
        this.enclosureStyleShowTag = booleanSubMap.get(LetterGeneralProperty.ENCLOSURE_STYLE_SHOW_TAG.getPropertyKey());
        this.enclosureStyleShowNumber = booleanSubMap.get(LetterGeneralProperty.ENCLOSURE_STYLE_SHOW_NUMBER.getPropertyKey());
        this.enclosureStyleShowTitles = booleanSubMap.get(LetterGeneralProperty.ENCLOSURE_STYLE_SHOW_TITLES.getPropertyKey());
        this.enclosureStyleTagSeparator = stringSubMap.get(LetterGeneralProperty.ENCLOSURE_STYLE_TAG_SEPARATOR.getPropertyKey());
        this.enclosureStyleTitleSeparator = stringSubMap.get(LetterGeneralProperty.ENCLOSURE_STYLE_TITLE_SEPARATOR.getPropertyKey());
    }

    public String getLanguage() {
        return language;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getBodyTextFile() {
        return bodyTextFile;
    }

    public String getSignatureImageFile() {
        return signatureImageFile;
    }

    public boolean isDraftModeOn() {
        return draftModeOn;
    }

    public boolean isImagePlaceholderOn() {
        return imagePlaceholderOn;
    }

    public boolean isHyperlinksOn() {
        return hyperlinksOn;
    }

    public boolean isEnclosureStyleHide() {
        return enclosureStyleHide;
    }

    public boolean isEnclosureStyleShowTag() {
        return enclosureStyleShowTag;
    }

    public boolean isEnclosureStyleShowNumber() {
        return enclosureStyleShowNumber;
    }

    public boolean isEnclosureStyleShowTitles() {
        return enclosureStyleShowTitles;
    }

    public String getEnclosureStyleTitleSeparator() {
        return enclosureStyleTitleSeparator;
    }

    public String getEnclosureStyleTagSeparator() {
        return enclosureStyleTagSeparator;
    }

    @Override
    public String toString() {
        return "LetterGeneral{" +
                "language='" + language + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", bodyTextFile='" + bodyTextFile + '\'' +
                ", signatureImageFile='" + signatureImageFile + '\'' +
                ", draftModeOn=" + draftModeOn +
                ", imagePlaceholderOn=" + imagePlaceholderOn +
                ", hyperlinksOn=" + hyperlinksOn +
                ", enclosureStyleHide=" + enclosureStyleHide +
                ", enclosureStyleShowTag=" + enclosureStyleShowTag +
                ", enclosureStyleShowNumber=" + enclosureStyleShowNumber +
                ", enclosureStyleShowTitles=" + enclosureStyleShowTitles +
                ", enclosureStyleTagSeparator='" + enclosureStyleTagSeparator + '\'' +
                ", enclosureStyleTitleSeparator='" + enclosureStyleTitleSeparator + '\'' +
                '}';
    }
}
