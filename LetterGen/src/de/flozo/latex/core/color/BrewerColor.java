package de.flozo.latex.core.color;

public class BrewerColor implements Color {

    private final Scheme scheme;
    private final Letter letter;

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


    @Override
    public String getString() {
        return scheme + "-" + letter;
    }
}
