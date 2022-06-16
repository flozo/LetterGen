package de.flozo.latex.core.color;

public enum SequentialScheme implements Scheme {

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
    REDS("Reds");

    private final String scheme;

    SequentialScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String getString() {
        return scheme;
    }
}
