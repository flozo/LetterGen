package de.flozo.data;

import de.flozo.latex.core.color.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PropertyValueTypeCheckTest {


    @ParameterizedTest
    @EnumSource(SequentialScheme.class)
    void isValidBrewerColorValue(SequentialScheme sequentialScheme) {
        assertTrue(PropertyValueTypeCheck.isValidBrewerColorValue().test(BrewerColor.compose(sequentialScheme, SequentialLetter.A).getString()));
    }

    @ParameterizedTest
    @EnumSource(StandardColor.class)
    void isValidStandardColorValue_true(StandardColor standardColor) {
        assertTrue(PropertyValueTypeCheck.isValidColorValue().test(standardColor.getString()));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "violet5", "lightblue", "grey"})
    void isValidStandardColorValue_false(String falseColor) {
        assertFalse(PropertyValueTypeCheck.isValidColorValue().test(falseColor));
    }

    @ParameterizedTest
    @EnumSource(SequentialScheme.class)
    void isSequentialScheme_true(SequentialScheme sequentialScheme) {
        assertTrue(PropertyValueTypeCheck.isSequentialScheme().test(BrewerColor.compose(sequentialScheme, SequentialLetter.A).getString()));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "violet5", "lightblue", "grey", "Blues", "Blues-Z", "Yellows-A", "RdBu", "Spectral"})
    void isSequentialScheme_false(String falseSequentialScheme) {
        assertFalse(PropertyValueTypeCheck.isSequentialScheme().test(falseSequentialScheme));
    }

    @ParameterizedTest
    @EnumSource(DivergingScheme.class)
    void isDivergingScheme_true(DivergingScheme divergingScheme) {
        assertTrue(PropertyValueTypeCheck.isDivergingScheme().test(BrewerColor.compose(divergingScheme, DivergingLetter.O).getString()));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "violet5", "lightblue", "grey", "Blues", "Blues-Z", "Yellows-A", "Reds-A", "GnBu-M"})
    void isDivergingScheme_false(String falseDivergingScheme) {
        assertFalse(PropertyValueTypeCheck.isDivergingScheme().test(falseDivergingScheme));
    }

}
