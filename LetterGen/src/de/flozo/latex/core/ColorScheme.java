package de.flozo.latex.core;

public enum ColorScheme {
    BU_GN("BuGn"),
    PU_RD("PuRd"),
    BU_PU("BuPu"),
    RD_PU("RdPu"),
    GN_BU("GnBu"),
    YL_GN("YlGn"),
    OR_RD("OrRd"),
    YL_GN_BU("YlGnBu"),
    PU_BU("PuBu"),
    YL_OR_BR("YlOrBr"),
    PU_BU_GN("PuBnGn"),
    YL_OR_RD("YlOrRd"),
    BLUES("Blues"),
    GREENS("Greens"),
    GREYS("Greys"),
    ORANGES("Oranges"),
    PURPLES("Purples"),
    RED("Reds");

    private final String schemeName;

    ColorScheme(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getString() {
        return schemeName;
    }
}
