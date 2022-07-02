package de.flozo.latex.core;

import java.util.Arrays;

public enum FontSize {

    DEFAULT(""),
    TINY("tiny"),
    SCRIPT_SIZE("scriptsize"),
    FOOTNOTE_SIZE("footnotesize"),
    SMALL("small"),
    NORMAL_SIZE("normalsize"),
    LARGE("large"),
    LARGE2("Large"),
    LARGE3("LARGE"),
    HUGE("huge"),
    HUGE2("Huge");


    private final String fontSizeName;

    FontSize(String fontSizeName) {
        this.fontSizeName = fontSizeName;
    }

    public String getString() {
        return fontSizeName;
    }

    public static FontSize getByValue(String fontSizeValue) {
        return Arrays.stream(values()).filter(value -> value.getString().equals(fontSizeValue)).findFirst().orElse(NORMAL_SIZE);
    }

    @Override
    public String toString() {
        return "FontSize{" +
                "fontSizeName='" + fontSizeName + '\'' +
                '}';
    }
}
