package de.flozo.latex.core.color;

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
