package de.flozo.latex.tikz.options;

public enum LineJoin {

    // TikZ graphic parameter: line join
    // permissible types are round, bevel, and miter

    ROUND("round"),
    BUTT("bevel"),
    RECT("miter");


    private final String lineJoin;

    LineJoin(String lineJoin) {
        this.lineJoin = lineJoin;
    }

    public String getString() {
        return lineJoin;
    }
}
