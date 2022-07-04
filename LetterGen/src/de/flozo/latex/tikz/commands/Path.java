package de.flozo.latex.tikz.commands;

import de.flozo.latex.core.Bracket;
import de.flozo.latex.core.FormattedExpressionList;
import de.flozo.latex.core.StatementTerminator;
import de.flozo.latex.color.Color;
import de.flozo.latex.tikz.options.DashPatternStyle;
import de.flozo.latex.tikz.options.LineCap;
import de.flozo.latex.tikz.options.LineJoin;
import de.flozo.latex.tikz.options.LineWidthStyle;

import java.util.List;

public abstract class Path {

    // TikZ definition: a path is a series of straight and curved line segments

    // constants
    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final Bracket OPTIONS_BRACKETS = Bracket.SQUARE_BRACKETS;
    public static final StatementTerminator TERMINATOR = StatementTerminator.SEMICOLON;

    // optional; visible for subclasses
    protected Point position;
    protected List<String> optionalArguments;
    protected String name;
    protected Color drawColor;
    protected Color fillColor;
    protected LineWidthStyle lineWidthStyle;
    protected LineCap lineCap;
    protected LineJoin lineJoin;
    protected DashPatternStyle dashPatternStyle;
    protected boolean skipLastTerminator;


    public Path(Point position, List<String> optionalArguments, String name,
                Color drawColor, Color fillColor, LineWidthStyle lineWidthStyle,
                LineCap lineCap, LineJoin lineJoin, DashPatternStyle dashPatternStyle,
                boolean skipLastTerminator) {
        this.position = position;
        this.optionalArguments = optionalArguments;
        this.name = name;
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.lineWidthStyle = lineWidthStyle;
        this.lineCap = lineCap;
        this.lineJoin = lineJoin;
        this.dashPatternStyle = dashPatternStyle;
        this.skipLastTerminator = skipLastTerminator;
    }

    public abstract String getInline();

    private FormattedExpressionList.Builder buildOptionList() {
        return new FormattedExpressionList.Builder(optionalArguments)
                .brackets(OPTIONS_BRACKETS)
                .terminator(StatementTerminator.COMMA)
                .skipLastTerminator(skipLastTerminator)
                .inlineSpacing(true);
    }

    protected String inlineOptions() {
        return buildOptionList().build().getInline();
    }

    protected List<String> blockOptions() {
        return buildOptionList().indentBlock(true).build().getBlock();
    }



    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Path{" +
                "position=" + position +
                ", optionalArguments=" + optionalArguments +
                ", name='" + name + '\'' +
                ", drawColor=" + drawColor +
                ", fillColor=" + fillColor +
                ", lineWidthStyle=" + lineWidthStyle +
                ", lineCap=" + lineCap +
                ", lineJoin=" + lineJoin +
                ", dashPatternStyle=" + dashPatternStyle +
                ", skipLastTerminator=" + skipLastTerminator +
                '}';
    }
}
