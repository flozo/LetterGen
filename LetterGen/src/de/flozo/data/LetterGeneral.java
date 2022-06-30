package de.flozo.data;

import java.util.Map;

public class LetterGeneral {

    private final String language;
    private final String dateFormat;
    private final boolean draftModeOn;

    public LetterGeneral(PropertyMap propertyMap) {
//        Map<String, String> rawMap = propertyMap.getProperties();
        Map<String, String> stringSubMap = propertyMap.stringSubMap();
        Map<String, Boolean> booleanSubMap = propertyMap.booleanSubMap();
        this.language = stringSubMap.get(LetterGeneralProperty.LANGUAGE.getPropertyKey());
        this.dateFormat = stringSubMap.get(LetterGeneralProperty.DATE_FORMAT.getPropertyKey());
        this.draftModeOn = booleanSubMap.get(LetterGeneralProperty.DRAFT_MODE_ON.getPropertyKey());
    }

    public String getLanguage() {
        return language;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public boolean isDraftModeOn() {
        return draftModeOn;
    }
}
