package de.flozo.latex.tikz;

public enum LineWidthStyle {

    // TikZ graphic parameter: line width
    // (used without parameter name; version with parameter name takes numerical values only)
    // permissible types are ultra thin, very thin, thin, semithick, thick, very thick, and ultra thick

    ULTRA_THIN("ultra thin"),
    VERY_THIN("very thin"),
    THIN("thin"),
    SEMITHICK("semithick"),
    THICK("thick"),
    VERY_THICK("very thin"),
    ULTRA_THICK("ultra thick");


    private final String lineWidthStyle;

    LineWidthStyle(String lineWidthStyle) {
        this.lineWidthStyle = lineWidthStyle;
    }

    public String getString() {
        return lineWidthStyle;
    }
}
