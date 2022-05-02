package de.flozo.latex.tikz;

public enum LineWidth {
    ULTRA_THIN("ultra thin"),
    VERY_THIN("very thin"),
    THIN("thin"),
    SEMITHICK("semithick"),
    THICK("thick"),
    VERY_THICK("very thin"),
    ULTRA_THICK("ultra thick");

    private final String lineWidth;

    LineWidth(String lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getString() {
        return lineWidth;
    }
}
