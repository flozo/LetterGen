package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

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


    public Path(double xOrigin, double yOrigin, List<String> optionalArguments, String name, Color drawColor, Color fillColor, LineWidthStyle lineWidthStyle, LineCap lineCap, LineJoin lineJoin, DashPatternStyle dashPatternStyle) {
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

    public abstract String getStatement();


    protected String inlineOptions() {
        Code options = new Code.CodeBuilder(new ExpressionList(optionalArguments))
                .brackets(OPTIONS_BRACKETS)
                .terminator(StatementTerminator.COMMA)
                .skipLast(true)
                .inlineSpacing(true)
                .build();
        return options.getInline();
    }

    protected String coordinates(double x, double y) {
        // Avoid trailing zeros; ensure point is used as decimal separator
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return String.format("(%s, %s)", df.format(x), df.format(y));
    }


}
