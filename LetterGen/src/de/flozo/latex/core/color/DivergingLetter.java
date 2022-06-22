package de.flozo.latex.core.color;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum DivergingLetter implements Letter {

    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    I("I"),
    J("J"),
    K("K"),
    L("L"),
    M("M"),
    N("N"),
    O("O");

    private final String scheme;

    DivergingLetter(String scheme) {
        this.scheme = scheme;
    }

    private static final Map<String, DivergingLetter> stringToEnum = Stream.of(values()).collect(toMap(DivergingLetter::getString, Function.identity()));

    public static Optional<DivergingLetter> fromString(String stringValue) {
        return Optional.ofNullable(stringToEnum.get(stringValue));
    }

    @Override
    public String getString() {
        return scheme;
    }

    @Override
    public String toString() {
        return "Letter15{" +
                "scheme='" + scheme + '\'' +
                '}';
    }
}
