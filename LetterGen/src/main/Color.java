package main;

public class Color {

    private final ColorSchemes colorScheme;
    private final ColorLetters colorLetter;

    public Color(ColorSchemes colorScheme, ColorLetters colorLetter) {
        this.colorScheme = colorScheme;
        this.colorLetter = colorLetter;
    }

    public ColorSchemes getColorScheme() {
        return colorScheme;
    }

    public ColorLetters getColorLetter() {
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
