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


    public static final String COMMAND_MARKER_CHAR = "\\";
    private final String fontSizeName;

    FontSize(String fontSizeName) {
        this.fontSizeName = fontSizeName;
    }

    public String getString() {
        return fontSizeName;
    }

    public String getCommand() {
        return getString().isBlank() ? "" : COMMAND_MARKER_CHAR + getString();
    }

    public static FontSize getByValue(String fontSizeValue) {
        return Arrays.stream(values())
                .filter(value -> value.getString().equals(fontSizeValue))
                .findFirst()
                .orElse(DEFAULT);
    }

    @Override
    public String toString() {
        return "FontSize{" +
                "fontSizeName='" + fontSizeName + '\'' +
                '}';
    }
}
