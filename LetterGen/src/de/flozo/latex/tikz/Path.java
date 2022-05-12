package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public abstract class Path {

    // TikZ definition: a path is a series of straight and curved line segments

    // constants
    public static final Bracket OPTIONS_BRACKETS = Bracket.SQUARE_BRACKETS;
    public static final StatementTerminator TERMINATOR = StatementTerminator.SEMICOLON;


    // required
    private double xOrigin;
    private double yOrigin;

    // optional
    private String name;
    private List<String> optionalArguments;
    private Color color;


    public Path(double xOrigin, double yOrigin) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
    }


    public abstract String getStatement();


    private String inlineOptions() {
        Code options = new Code.CodeBuilder(new ExpressionList(optionalArguments))
                .brackets(OPTIONS_BRACKETS)
                .terminator(StatementTerminator.COMMA)
                .skipLast(true)
                .inlineSpacing(true)
                .build();
        return options.getInline();
    }

    private String coordinates(double x, double y) {
        // Avoid trailing zeros; ensure point is used as decimal separator
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return String.format("(%s, %s)", df.format(x), df.format(y));
    }


}
