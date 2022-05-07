package de.flozo.latex.core;

public enum PackageName {
    STANDALONE("standalone"),
    INPUTENC("inputenc"),
    FONTENC("fontenc"),
    BABEL("babel"),
    HYPERXMP("hyperxmp"),
    FIRASANS("FiraSans"),
    NEWTXSF("newtxsf"),
    FONTAWESOME5("fontawesome5"),
    CSQUOTES("csquotes"),
    ENUMITEM("enumitem"),
    MICROTYPE("microtype"),
    TIKZ("tikz"),
    HYPERREF("hyperref");

    private final String packageName;

    PackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getString() {
        return packageName;
    }

}
