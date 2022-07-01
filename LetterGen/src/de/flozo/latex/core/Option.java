package de.flozo.latex.core;

public enum Option {

    WIDTH("width"),
    HEIGHT("height"),
    SCALE("scale");

    private final String option;

    Option(String option) {
        this.option = option;
    }

    public String getString() {
        return option;
    }


    @Override
    public String toString() {
        return "Option{" +
                "option='" + option + '\'' +
                '}';
    }
}
