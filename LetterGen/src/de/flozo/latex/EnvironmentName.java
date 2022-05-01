package de.flozo.latex;

public enum EnvironmentName {
    DOCUMENT,
    SCOPE,
    TIKZPICTURE,
    PGFONLAYER;

    public String getString() {
        return name().toLowerCase();
    }
}
