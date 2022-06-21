package de.flozo.latex.core.color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrewerColorTest {

    @Test
    void parseColor() {
        assertEquals(BrewerColor.compose(SequentialScheme.ORANGES, Letter13.A).getString(), "Oranges-A");
        assertEquals(BrewerColor.compose(SequentialScheme.RD_PU, Letter13.M).getString(), "RdPu-M");
        assertEquals(BrewerColor.compose(DivergingScheme.P_R_GN, Letter15.O).getString(), "PRGn-O");
    }
}
