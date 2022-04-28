package main;

public enum FontSize {
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

    public String getFontSizeName() {
        return fontSizeName;
    }
}
