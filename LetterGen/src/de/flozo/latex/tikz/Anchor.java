package de.flozo.latex.tikz;

public enum Anchor {

    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    NORTH_EAST("north east"),
    SOUTH_EAST("south east"),
    SOUTH_WEST("south west"),
    NORTH_WEST("north west"),
    CENTER("center"),
    BASE("base"),
    MID("mid");


    private final String anchor;

    Anchor(String anchor) {
        this.anchor = anchor;
    }

    public String getString() {
        return anchor;
    }
}
