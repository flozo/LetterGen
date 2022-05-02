package de.flozo.latex;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorTest {

    public static final ColorLetter TEST_COLOR_LETTER = ColorLetter.A;
    public static final ColorScheme TEST_COLOR_SCHEME = ColorScheme.BLUES;
    public static final String SEPARATOR = "-";

    @ParameterizedTest
    @EnumSource(ColorScheme.class)
    void getString(ColorScheme colorScheme) {
        Color color = new Color(colorScheme, TEST_COLOR_LETTER);
        String expected = colorScheme.getString() + SEPARATOR + TEST_COLOR_LETTER;
        System.out.println(color.getString());
        assertEquals(expected, color.getString());
    }

    @ParameterizedTest
    @EnumSource(ColorLetter.class)
    void getString(ColorLetter colorLetter) {
        Color color = new Color(TEST_COLOR_SCHEME, colorLetter);
        String expected = TEST_COLOR_SCHEME.getString() + SEPARATOR + colorLetter;
        System.out.println(color.getString());
        assertEquals(expected, color.getString());
    }


}
