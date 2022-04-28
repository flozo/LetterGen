package de.flozo.latex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void testToString_BluesK() {
        assertEquals("Blues-K", new Color(ColorScheme.BLUES, ColorLetter.K).toString());
    }

    @Test
    void testToString_YlGnBu() {
        assertEquals("YlGnBu-C", new Color(ColorScheme.YL_GN_BU, ColorLetter.C).toString());
    }

    @Test
    void testToString_BuGn() {
        assertEquals("BuGn-M", new Color(ColorScheme.BU_GN, ColorLetter.M).toString());
    }


}
