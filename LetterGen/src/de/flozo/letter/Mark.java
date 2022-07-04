package de.flozo.letter;

import de.flozo.latex.core.Length;
import de.flozo.latex.color.Color;
import de.flozo.latex.tikz.options.CoordinateMode;
import de.flozo.latex.tikz.commands.Line;
import de.flozo.latex.tikz.commands.Point;

public class Mark {


    private final Point position;
    private final Length width;
    private final Length lineWidth;
    private final Color color;

    public Mark(Point position, Length width, Length lineWidth, Color color) {
        this.position = position;
        this.width = width;
        this.lineWidth = lineWidth;
        this.color = color;
    }

    public String getLine() {
        return new Line.Builder(position, Point.fromLengthsInMode(width, Length.inDefaultUnit(0), CoordinateMode.RELATIVE))
                .lineWidth(lineWidth)
                .drawColor(color)
                .build().getInline();
    }

}
