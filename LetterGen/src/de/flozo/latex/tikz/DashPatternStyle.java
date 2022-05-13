package de.flozo.latex.tikz;

public enum DashPatternStyle {

    // TikZ graphic parameter: dash pattern
    // (used without parameter name; version with parameter name takes numerical values only)
    // permissible types are solid, dotted, densely dotted, loosely dotted,
    // dashed, densely dashed, loosely dashed, dash dot, densely dash dot, loosely dash dot,
    // dash dot dot, densely dash dot dot, and loosely dash dot dot


    SOLID("solid"),
    DOTTED("dotted"),
    DENSELY_DOTTED("densely dotted"),
    LOOSELY_DOTTED("loosely dotted"),
    DASHED("dashed"),
    DENSELY_DASHED("densely dashed"),
    LOOSELY_DASHED("loosely dashed"),
    DASH_DOT("dash dot"),
    DENSELY_DASH_DOT("densely dash dot"),
    LOOSELY_DASH_DOT("loosely dash dot"),
    DASH_DOT_DOT("dash dot dot"),
    DENSELY_DASH_DOT_DOT("densely dash dot dot"),
    LOOSELY_DASH_DOT_DOT("loosely dash dot dot");

    private final String dashPatternStyle;

    DashPatternStyle(String dashPatternStyle) {
        this.dashPatternStyle = dashPatternStyle;
    }

    public String getString() {
        return dashPatternStyle;
    }

}
