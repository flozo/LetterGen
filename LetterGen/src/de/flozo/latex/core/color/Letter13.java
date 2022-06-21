package de.flozo.latex.core.color;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Letter13 implements Letter {

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
    M("M");

    private final String scheme;

    Letter13(String scheme) {
        this.scheme = scheme;
    }

    private static final Map<String, Letter13> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e -> e));

    public static Optional<Letter13> fromString(String stringValue) {
        return Optional.ofNullable(stringToEnum.get(stringValue));
    }


    @Override
    public String getString() {
        return scheme;
    }

    @Override
    public String toString() {
        return "Letter13{" +
                "scheme='" + scheme + '\'' +
                '}';
    }
}
