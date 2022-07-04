package de.flozo.latex.color;

import de.flozo.data.PropertyValueTypeCheck;

public class BrewerColor implements Color, PropertyValueTypeCheck {

    private final Scheme scheme;
    private final Letter letter;

    private BrewerColor(Scheme scheme, Letter letter) {
        this.scheme = scheme;
        this.letter = letter;
    }

    private BrewerColor(SequentialScheme scheme, SequentialLetter letter) {
        this.scheme = scheme;
        this.letter = letter;
    }

    private BrewerColor(DivergingScheme scheme, DivergingLetter letter) {
        this.scheme = scheme;
        this.letter = letter;
    }

    public static BrewerColor compose(SequentialScheme scheme, SequentialLetter letter) {
        return new BrewerColor(scheme, letter);
    }

    public static BrewerColor compose(DivergingScheme scheme, DivergingLetter letter) {
        return new BrewerColor(scheme, letter);
    }

    private static String schemeString(String colorString) {
        return colorString.split("-")[0];
    }

    private static String letterString(String colorString) {
        return colorString.split("-")[1];
    }


    public static BrewerColor parseColor(String colorString) {
        if (PropertyValueTypeCheck.isSequentialScheme().test(colorString)) {
            return compose(SequentialScheme.fromString(schemeString(colorString)).orElse(null), SequentialLetter.fromString(letterString(colorString)).orElse(null));
        } else if (PropertyValueTypeCheck.isDivergingScheme().test(colorString)) {
            return BrewerColor.compose(DivergingScheme.fromString(schemeString(colorString)).orElse(null), DivergingLetter.fromString(letterString(colorString)).orElse(null));
        }
        return null;
    }


    @Override
    public String getString() {
        if (scheme == null || letter == null) {
            return "";
        }
        return scheme.getString() + "-" + letter.getString();
    }

    @Override
    public String toString() {
        return "BrewerColor{" +
                "scheme=" + scheme +
                ", letter=" + letter +
                '}';
    }
}
