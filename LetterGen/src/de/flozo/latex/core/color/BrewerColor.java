package de.flozo.latex.core.color;

public class BrewerColor implements Color {

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


    public static BrewerColor parseColor(String colorString) {
        return new BrewerColor(parseScheme(schemeString(colorString)), parseLetter(letterString(colorString)));
    }

    private static String schemeString(String colorString) {
        return colorString.split("-")[0];
    }

    private static String letterString(String colorString) {
        return colorString.split("-")[1];
    }

    private static Scheme parseScheme(String schemeString) {
        if (SequentialScheme.fromString(schemeString).isPresent()) {
            return SequentialScheme.fromString(schemeString).get();
        } else if (DivergingScheme.fromString(schemeString).isPresent()) {
            return DivergingScheme.fromString(schemeString).get();
        }
        return null;
    }

    private static Letter parseLetter(String letterString) {
        if (Letter13.fromString(letterString).isPresent()) {
            System.out.println("ZZZZZZZZZZZZZZZZZ");
            System.out.println(Letter13.fromString(letterString).get());
            return Letter13.fromString(letterString).get();
        } else if (Letter15.fromString(letterString).isPresent()) {
            System.out.println(Letter15.fromString(letterString).get());
            return Letter15.fromString(letterString).get();
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
