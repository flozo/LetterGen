package de.flozo.latex.core.color;

public enum DivergingScheme implements Scheme {

    BR_B_G("BrBG"),
    RD_GY("RdGy"),
    RD_YL_BU("RdYlBu"),
    PI_Y_G("PiYG"),
    PU_OR("PuOr"),
    RD_YL_GN("RdYlGn"),
    P_R_GN("PRGn"),
    RD_BU("RdBu"),
    SPECTRAL("Spectral");

    private final String scheme;

    DivergingScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String getString() {
        return scheme;
    }

}
