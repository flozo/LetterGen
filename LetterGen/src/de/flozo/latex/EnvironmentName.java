package de.flozo.latex;

public enum EnvironmentName {
    DOCUMENT,
    SCOPE,
    TIKZPICTURE,
    PGFONLAYER;

    public String getLabel() {
        return name().toLowerCase();
    }
}
