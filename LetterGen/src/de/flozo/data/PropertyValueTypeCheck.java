package de.flozo.data;

import de.flozo.latex.core.FontSize;
import de.flozo.latex.core.color.*;

import java.util.EnumSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface PropertyValueTypeCheck {

    static Predicate<String> isValidFontSizeValue() {
        return key -> EnumSet.allOf(FontSize.class)
                .stream()
                .map(FontSize::getString)
                .collect(Collectors.toSet())
                .contains(key);
    }

    static Predicate<String> isValidColorValue() {
        return isValidStandardColorValue().or(isValidBrewerColorValue());
    }

    static Predicate<String> isValidBrewerColorValue() {
        return isBrewerType().and(isSequentialScheme().or(isDivergingScheme()));
    }

    static Predicate<String> isValidStandardColorValue() {
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isStandardColor = key -> EnumSet.allOf(StandardColor.class)
                .stream()
                .map(StandardColor::getString)
                .collect(Collectors.toSet())
                .contains(key);
        return isEmpty.or(isStandardColor);
    }


    private static Predicate<String> isBrewerType() {
        return key -> key.contains("-");
    }

    static Predicate<String> isSequentialScheme() {
        Predicate<String> hasBrewerSequentialScheme = key -> EnumSet.allOf(SequentialScheme.class)
                .stream()
                .map(SequentialScheme::getString)
                .anyMatch(key::startsWith);
        Predicate<String> hasBrewerSequentialLetter = key -> EnumSet.allOf(SequentialLetter.class)
                .stream()
                .map(SequentialLetter::getString)
                .anyMatch(key::endsWith);
        return hasBrewerSequentialScheme.and(hasBrewerSequentialLetter);
    }

    static Predicate<String> isDivergingScheme() {
        Predicate<String> hasBrewerDivergingScheme = key -> EnumSet.allOf(DivergingScheme.class)
                .stream()
                .map(DivergingScheme::getString)
                .anyMatch(key::startsWith);
        Predicate<String> hasBrewerDivergingLetter = key -> EnumSet.allOf(DivergingLetter.class)
                .stream()
                .map(DivergingLetter::getString)
                .anyMatch(key::endsWith);
        return hasBrewerDivergingScheme.and(hasBrewerDivergingLetter);
    }

}
