package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

import java.util.List;

public abstract class Path {

    // TikZ definition: a path is a series of straight and curved line segments

    // constants
    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final Bracket OPTIONS_BRACKETS = Bracket.SQUARE_BRACKETS;
    public static final StatementTerminator TERMINATOR = StatementTerminator.SEMICOLON;


    // required; visible for subclasses
    protected double xOrigin;
    protected double yOrigin;

    // optional; visible for subclasses
    protected List<String> optionalArguments;
    protected String name;
    protected Color drawColor;
    protected Color fillColor;
    protected LineWidthStyle lineWidthStyle;
    protected LineCap lineCap;
    protected LineJoin lineJoin;
    protected DashPatternStyle dashPatternStyle;


    public Path(double xOrigin, double yOrigin, List<String> optionalArguments, String name,
                Color drawColor, Color fillColor, LineWidthStyle lineWidthStyle,
                LineCap lineCap, LineJoin lineJoin, DashPatternStyle dashPatternStyle) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.optionalArguments = optionalArguments;
        this.name = name;
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.lineWidthStyle = lineWidthStyle;
        this.lineCap = lineCap;
        this.lineJoin = lineJoin;
        this.dashPatternStyle = dashPatternStyle;
    }

    public abstract String getInline();

    private ExpressionList2 buildOptionList() {
        return new ExpressionList2.ExpressionList2Builder(optionalArguments)
                .brackets(OPTIONS_BRACKETS)
                .terminator(StatementTerminator.COMMA)
                .skipLastTerminator(true)
                .inlineSpacing(true)
                .build();
    }

    protected String inlineOptions() {
        return buildOptionList().getInline();
    }

    protected List<String> blockOptions() {
        return buildOptionList().getBlock();
    }


    protected String coordinates(double x, double y) {
        return coordinates(x, y, CoordinateMode.ABSOLUTE, LengthUnit.DEFAULT);
    }

    protected String coordinates(double x, double y, LengthUnit lengthUnit) {
        return coordinates(x, y, CoordinateMode.ABSOLUTE, lengthUnit);
    }

    protected String coordinates(double x, double y, CoordinateMode coordinateMode) {
        return coordinates(x, y, coordinateMode, LengthUnit.DEFAULT);
    }


    protected String coordinates(double x, double y, CoordinateMode coordinateMode, LengthUnit lengthUnit) {
        return new Point.PointBuilder(x, y)
                .xUnit(lengthUnit)
                .yUnit(lengthUnit)
                .coordinateMode(coordinateMode)
                .build().getStatement();
    }


    public double getXOrigin() {
        return xOrigin;
    }

    public double getYOrigin() {
        return yOrigin;
    }

    public List<String> getOptionalArguments() {
        return optionalArguments;
    }

    public String getName() {
        return name;
    }

    public Color getDrawColor() {
        return drawColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public LineWidthStyle getLineWidthStyle() {
        return lineWidthStyle;
    }

    public LineCap getLineCap() {
        return lineCap;
    }

    public LineJoin getLineJoin() {
        return lineJoin;
    }

    public DashPatternStyle getDashPatternStyle() {
        return dashPatternStyle;
    }
}
