package de.flozo.data;

import de.flozo.latex.core.color.*;

import java.util.EnumSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface PropertyValueTypeCheck {


    static Predicate<String> validColorValue() {
        return validStandardColorValue().or(validBrewerColorValue());
    }

    static Predicate<String> validBrewerColorValue() {
        return isBrewerType().and(isSequentialScheme().or(isDivergingScheme()));
    }

    static Predicate<String> validStandardColorValue() {
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

    private static Predicate<String> isSequentialScheme() {
        Predicate<String> hasBrewerSequentialScheme = key -> EnumSet.allOf(SequentialScheme.class)
                .stream()
                .map(SequentialScheme::getString)
                .anyMatch(key::startsWith);
        Predicate<String> hasBrewerSequentialLetter = key -> EnumSet.allOf(Letter13.class)
                .stream()
                .map(Letter13::getString)
                .anyMatch(key::endsWith);
        return hasBrewerSequentialScheme.and(hasBrewerSequentialLetter);
    }

    private static Predicate<String> isDivergingScheme() {
        Predicate<String> hasBrewerDivergingScheme = key -> EnumSet.allOf(DivergingScheme.class)
                .stream()
                .map(DivergingScheme::getString)
                .anyMatch(key::startsWith);
        Predicate<String> hasBrewerDivergingLetter = key -> EnumSet.allOf(Letter15.class)
                .stream()
                .map(Letter15::getString)
                .anyMatch(key::endsWith);
        return hasBrewerDivergingScheme.and(hasBrewerDivergingLetter);
    }

}
