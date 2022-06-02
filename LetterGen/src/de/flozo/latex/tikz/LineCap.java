package de.flozo.latex.tikz;

public enum LineCap {

    // TikZ graphic parameter: line cap
    // permissible types are round, rect, and butt

    ROUND("round"),
    BUTT("butt"),
    RECT("rect");


    private final String lineCap;

    LineCap(String lineCap) {
        this.lineCap = lineCap;
    }

    public String getString() {
        return lineCap;
    }
}
