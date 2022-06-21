package de.flozo.latex.core.color;

import de.flozo.data.PropertyValueTypeCheck;

public class BrewerColor implements Color, PropertyValueTypeCheck {

    private final Scheme scheme;
    private final Letter letter;

    private BrewerColor(Scheme scheme, Letter letter) {
        this.scheme = scheme;
        this.letter = letter;
    }

    private BrewerColor(SequentialScheme scheme, Letter13 letter) {
        this.scheme = scheme;
        this.letter = letter;
    }

    private BrewerColor(DivergingScheme scheme, Letter15 letter) {
        this.scheme = scheme;
        this.letter = letter;
    }

    public static BrewerColor compose(SequentialScheme scheme, Letter13 letter) {
        return new BrewerColor(scheme, letter);
    }

    public static BrewerColor compose(DivergingScheme scheme, Letter15 letter) {
        return new BrewerColor(scheme, letter);
    }


//    public static BrewerColor parseColor(String colorString) {
//        System.out.println("GGGGGGGGGGGGGGGGGGGg");
//        System.out.println(parseScheme(schemeString(colorString)));
//        System.out.println(parseLetter(letterString(colorString)));
//        System.out.println("GGGGGGGGGGGGGGGGGGGg");
//        return new BrewerColor(parseScheme(schemeString(colorString)), parseLetter(letterString(colorString)));
//    }

    private static String schemeString(String colorString) {
        System.out.println(colorString);
        System.out.println(colorString.split("-")[0]);
        return colorString.split("-")[0];
    }

    private static String letterString(String colorString) {
        System.out.println(colorString);
        System.out.println(colorString.split("-")[1]);
        return colorString.split("-")[1];
    }

//    private static Scheme parseScheme(String schemeString) {
//        if (PropertyValueTypeCheck.isSequentialScheme().test(schemeString)) {
//            return SequentialScheme.fromString(schemeString).get();
//        } else if (PropertyValueTypeCheck.isDivergingScheme().test(schemeString)) {
//            return DivergingScheme.fromString(schemeString).get();
//        }
//        return null;
//    }
//
//    private static Letter parseLetter(String letterString) {
//        if (Letter13.fromString(letterString).isPresent()) {
//            return Letter13.fromString(letterString).get();
//        } else if (Letter15.fromString(letterString).isPresent()) {
//            return Letter15.fromString(letterString).get();
//        }
//        return null;
//    }

    public static BrewerColor parseColor(String colorString) {
        if (PropertyValueTypeCheck.isSequentialScheme().test(colorString)) {
            return BrewerColor.compose(SequentialScheme.fromString(schemeString(colorString)).orElse(null),Letter13.fromString(letterString(colorString)).orElse(null));
        } else if (PropertyValueTypeCheck.isDivergingScheme().test(colorString)) {
            return BrewerColor.compose(DivergingScheme.fromString(schemeString(colorString)).orElse(null),Letter15.fromString(letterString(colorString)).orElse(null));
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
