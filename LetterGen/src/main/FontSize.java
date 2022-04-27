package main;

public enum FontSize {
    TINY("tiny"),
    SCRIPT_SIZE("scriptsize"),
    FOOTNOTE_SIZE("footnotesize"),
    SMALL("small"),
    NORMAL_SIZE("normalsize"),
    LARGE("large"),
    LARGE_CAPITALIZED("Large"),
    LARGE_ALL_UPPER_CASE("LARGE"),
    HUGE("huge"),
    HUGE_CAPITALIZED("Huge");

    private final String fontSizeName;

    FontSize(String fontSizeName) {
        this.fontSizeName = fontSizeName;
    }

    public String getFontSizeName() {
        return fontSizeName;
    }
}
