package de.flozo.latex.core.color;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Letter15 implements Letter {

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

    Letter15(String scheme) {
        this.scheme = scheme;
    }

    private static final Map<String, Letter15> stringToEnum = Stream.of(values()).collect(toMap(Letter15::getString, Function.identity()));

    public static Optional<Letter15> fromString(String stringValue) {
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
