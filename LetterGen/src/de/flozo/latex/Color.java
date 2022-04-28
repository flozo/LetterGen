package de.flozo.latex;

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

    @Override
    public String toString() {
        // Compose a valid ColorBrewer string from colorScheme and colorLetter
        // example:
        //      colorScheme = BLUES
        //      colorLetter = G
        //      => Color.toString = "Blues-G"
        return colorScheme.toString() + "-" + colorLetter.toString();
    }
}
