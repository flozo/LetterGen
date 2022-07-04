package de.flozo.latex;

import de.flozo.latex.color.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrewerColorTest {

    @Test
    void parseColor() {
        assertEquals(BrewerColor.compose(SequentialScheme.ORANGES, SequentialLetter.A).getString(), "Oranges-A");
        assertEquals(BrewerColor.compose(SequentialScheme.RD_PU, SequentialLetter.M).getString(), "RdPu-M");
        assertEquals(BrewerColor.compose(DivergingScheme.P_R_GN, DivergingLetter.O).getString(), "PRGn-O");
    }
}
