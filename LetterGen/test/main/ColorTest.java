package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void testToString_BluesK() {
        assertEquals("Blues-K", new Color(ColorSchemes.BLUES, ColorLetters.K).toString());
    }

    @Test
    void testToString_YlGnBu() {
        assertEquals("YlGnBu-C", new Color(ColorSchemes.YL_GN_BU, ColorLetters.C).toString());
    }

    @Test
    void testToString_BuGn() {
        assertEquals("BuGn-M", new Color(ColorSchemes.BU_GN, ColorLetters.M).toString());
    }


}
