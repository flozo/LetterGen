package de.flozo.latex.color;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum SequentialLetter implements Letter {

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

    SequentialLetter(String scheme) {
        this.scheme = scheme;
    }

    private static final Map<String, SequentialLetter> stringToEnum = Stream.of(values()).collect(toMap(SequentialLetter::getString, Function.identity()));

    public static Optional<SequentialLetter> fromString(String stringValue) {
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
