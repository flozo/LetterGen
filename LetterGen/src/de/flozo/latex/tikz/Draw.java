package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

import java.util.List;

public class Draw {

    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final CommandName DRAW_KEYWORD = CommandName.DRAW;
    public static final CommandName FILL_KEYWORD = CommandName.FILL;
    public static final CommandName FILLDRAW_KEYWORD = CommandName.FILLDRAW;
    public static final Bracket OPTIONS_BRACKETS = Bracket.SQUARE_BRACKETS;
    public static final StatementTerminator TERMINATOR = StatementTerminator.SEMICOLON;

    // required
    private final double x;
    private final double y;
    private final String text;

    // optional
    private final String name;
    private final List<String> optionalArguments;
    private final Anchor anchor;
    private final Color drawColor;
    private final Color fillColor;
    private final double xShift;
    private final double yShift;
    private double minimumWidth;
    private double minimumHeight;



}
