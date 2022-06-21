package de.flozo.latex.core.color;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum DivergingScheme implements Scheme {

    BR_B_G("BrBG"),
    RD_GY("RdGy"),
    RD_YL_BU("RdYlBu"),
    PI_Y_G("PiYG"),
    PU_OR("PuOr"),
    RD_YL_GN("RdYlGn"),
    P_R_GN("PRGn"),
    RD_BU("RdBu"),
    SPECTRAL("Spectral");

    private final String scheme;

    DivergingScheme(String scheme) {
        this.scheme = scheme;
    }

    private static final Map<String, DivergingScheme> stringToEnum = Stream.of(values()).collect(toMap(DivergingScheme::getString, Function.identity()));

    public static Optional<DivergingScheme> fromString(String stringValue) {
        return Optional.ofNullable(stringToEnum.get(stringValue));
    }


    @Override
    public String getString() {
        return scheme;
    }

    @Override
    public String toString() {
        return "DivergingScheme{" +
                "scheme='" + scheme + '\'' +
                '}';
    }
}
