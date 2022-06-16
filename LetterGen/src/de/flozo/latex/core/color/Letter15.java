package de.flozo.latex.core.color;

public enum Letter15 implements Letter {

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
    M("M"),
    N("N"),
    O("O");

    private final String scheme;

    Letter15(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String getString() {
        return scheme;
    }

}
