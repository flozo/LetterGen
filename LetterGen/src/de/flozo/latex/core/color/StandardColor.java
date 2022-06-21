package de.flozo.latex.core.color;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum StandardColor implements Color {

    DEFAULT,
    NONE,
    BLACK,
    BLUE,
    BROWN,
    CYAN,
    DARKGRAY,
    GRAY,
    GREEN,
    LIGHTGRAY,
    LIME,
    MAGENTA,
    OLIVE,
    ORANGE,
    PINK,
    PURPLE,
    RED,
    TEAL,
    VIOLET,
    WHITE,
    YELLOW;

    private static final Map<String, StandardColor> stringToEnum = Stream.of(values()).collect(toMap(StandardColor::getString, Function.identity()));

    public static Optional<StandardColor> fromString(String stringValue) {
        return Optional.ofNullable(stringToEnum.get(stringValue));
    }

    @Override
    public String getString() {
        return !name().equals("DEFAULT") ? name().toLowerCase() : "";
    }
}
