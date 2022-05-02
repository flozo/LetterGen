package de.flozo.latex.core;

public class Color {

    private final ColorScheme colorScheme;
    private final ColorLetter colorLetter;

    public Color(ColorScheme colorScheme, ColorLetter colorLetter) {
        this.colorScheme = colorScheme;
        this.colorLetter = colorLetter;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public ColorLetter getColorLetter() {
        return colorLetter;
    }

    public String getString() {
        // Compose a valid ColorBrewer string from colorScheme and colorLetter
        // example:
        //      colorScheme = BLUES
        //      colorLetter = G
        //      => Color.toString = "Blues-G"
        return colorScheme.getString() + "-" + colorLetter.toString();
    }
}
