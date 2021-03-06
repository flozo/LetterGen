package de.flozo.letter.latex;

import de.flozo.latex.core.FontSize;
import de.flozo.latex.tikz.options.Anchor;

public class Area {

    // required
    private final String name;

    private final double x;
    private final double y;
    private final double width;
    private final double height;

    private final Anchor anchor;

    // optional
    private final FontSize fontSize;

    public Area(String name, double x, double y, double width, double height, Anchor anchor) {
        this(name, x, y, width, height, anchor, FontSize.NORMAL_SIZE);
    }

    public Area(String name, double x, double y, double width, double height, Anchor anchor, FontSize fontSize) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.anchor = anchor;
        this.fontSize = fontSize;
    }


    @Override
    public String toString() {
        return "Area{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", anchor=" + anchor +
                ", fontSize=" + fontSize +
                '}';
    }
}
