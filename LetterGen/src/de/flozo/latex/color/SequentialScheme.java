package de.flozo.latex.color;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum SequentialScheme implements Scheme {

    BU_GN("BuGn"),
    PU_RD("PuRd"),
    BU_PU("BuPu"),
    RD_PU("RdPu"),
    GN_BU("GnBu"),
    YL_GN("YlGn"),
    OR_RD("OrRd"),
    YL_GN_BU("YlGnBu"),
    PU_BU("PuBu"),
    YL_OR_BR("YlOrBr"),
    PU_BU_GN("PuBnGn"),
    YL_OR_RD("YlOrRd"),
    BLUES("Blues"),
    GREENS("Greens"),
    GREYS("Greys"),
    ORANGES("Oranges"),
    PURPLES("Purples"),
    REDS("Reds");

    private final String scheme;

    SequentialScheme(String scheme) {
        this.scheme = scheme;
    }

    private static final Map<String, SequentialScheme> stringToEnum = Stream.of(values()).collect(toMap(SequentialScheme::getString, Function.identity()));

    public static Optional<SequentialScheme> fromString(String stringValue) {
        return Optional.ofNullable(stringToEnum.get(stringValue));
    }

    @Override
    public String getString() {
        return scheme;
    }

    @Override
    public String toString() {
        return "SequentialScheme{" +
                "scheme='" + scheme + '\'' +
                '}';
    }
}
