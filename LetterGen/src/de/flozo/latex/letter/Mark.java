package de.flozo.latex.letter;

import de.flozo.latex.tikz.CoordinateMode;
import de.flozo.latex.tikz.Line;

public class Mark {

    private final double x;
    private final double y;
    private final double width;
    private final double thickness;

    public Mark(String x, String y, String width, String thickness) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.width = Double.parseDouble(width);
        this.thickness = Double.parseDouble(thickness);
    }

//    public Mark(Properties properties) {
//        this.x = Double.parseDouble(properties.getProperty(LetterGeometryProperty.PERFORATION_MARK_X.getString()));
//        this.y = Double.parseDouble(properties.getProperty(LetterGeometryProperty.PERFORATION_MARK_Y.getString()));
//        this.width = Double.parseDouble(properties.getProperty(LetterGeometryProperty.PERFORATION_MARK_WIDTH.getString()));
//        this.thickness = Double.parseDouble(properties.getProperty(LetterGeometryProperty.PERFORATION_MARK_THICKNESS.getString()));
//    }

    public String getStatement() {
//        EnumSet<LetterGeometryProperty> perforationMarkSettings;
//        perforationMarkSettings = EnumSet.of(LetterGeometryProperty.PERFORATION_MARK_X,
//                LetterGeometryProperty.PERFORATION_MARK_Y,
//                LetterGeometryProperty.PERFORATION_MARK_WIDTH,
//                LetterGeometryProperty.PERFORATION_MARK_THICKNESS);
        return getLine().getInline();
    }

    public Line getLine() {
        return new Line.LineBuilder(x, y, width, 0, CoordinateMode.RELATIVE).lineWidth(thickness).build();
    }


}
