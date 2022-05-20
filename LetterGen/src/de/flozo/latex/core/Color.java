package de.flozo.latex.core;

public class Color {

    private final String name;

    public Color(ColorScheme colorScheme, ColorLetter colorLetter) {
        this.name = colorScheme.getString() + "-" + colorLetter.toString();
    }

    public Color(StandardColorName colorName) {
        this.name = colorName.getString();
    }

    public String getString() {
        return name;
    }
}
