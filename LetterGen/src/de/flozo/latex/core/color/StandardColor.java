package de.flozo.latex.core.color;

public enum StandardColor implements Color {

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

    @Override
    public String getString() {
        return name().toLowerCase();
    }
}