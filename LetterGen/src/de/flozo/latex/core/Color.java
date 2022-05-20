package de.flozo.latex.core;

public class Color {

//    private final ColorScheme colorScheme;
//    private final ColorLetter colorLetter;
    private final String name;


    public Color(ColorScheme colorScheme, ColorLetter colorLetter) {
//        this.colorScheme = colorScheme;
//        this.colorLetter = colorLetter;
        this.name = colorScheme.getString() + "-" + colorLetter.toString();
    }

    public Color(StandardColorName colorName) {
        this.name = colorName.getString();
    }


    public String getString() {
        return name;
    }
}
