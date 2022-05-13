package de.flozo.latex.tikz;

import de.flozo.latex.core.Color;
import de.flozo.latex.core.CommandName;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Path {

    // constants
    public static final CommandName KEYWORD = CommandName.FILLDRAW;
    public static final PathOperation OPERATION = PathOperation.RECTANGLE;

    // required
    private final double xOppositeCorner;
    private final double yOppositeCorner;

    // optional
    private Color fillColor;
    private LineWidthStyle lineWidthStyle;


    private Rectangle(RectangleBuilder builder) {
        super(builder.xOrigin, builder.yOrigin, builder.optionalArguments, builder.name, builder.drawColor,builder.fillColor, builder.lineWidthStyle, builder.lineCap, builder.lineJoin, builder.dashPatternStyle);
        this.xOppositeCorner = builder.xOppositeCorner;
        this.yOppositeCorner = builder.yOppositeCorner;
        this.fillColor = builder.fillColor;
        this.lineWidthStyle = builder.lineWidthStyle;
    }


    @Override
    public String getStatement() {
        StringBuilder sb = new StringBuilder(COMMAND_MARKER_CHAR + KEYWORD.getString());
        // Append name in parentheses if name is not null, empty, or only whitespaces
        if (name != null && !name.strip().equals("")) {
            sb.append(String.format(" (%s)", name));
        }
        // Append options if at least one option is present
        if (!optionalArguments.isEmpty()) {
            sb.append(" ").append(inlineOptions());
        }
        // Append required parts
        sb.append(" ").append(coordinates(xOrigin, yOrigin));
        sb.append(" ").append(OPERATION.getString());
        sb.append(" ").append(coordinates(xOppositeCorner, yOppositeCorner));
        sb.append(TERMINATOR.getString());
        return sb.toString();
    }





    public static class RectangleBuilder {
        // required
        private final double xOrigin;
        private final double yOrigin;
        private final double xOppositeCorner;
        private final double yOppositeCorner;

        // optional
        private String name;
        private final List<String> optionalArguments = new ArrayList<>();
        private Color drawColor;
        private Color fillColor;
        private LineWidthStyle lineWidthStyle;
        private double lineWidth;
        private LineCap lineCap;
        private LineJoin lineJoin;
        private DashPatternStyle dashPatternStyle;


        public RectangleBuilder(double xOrigin, double yOrigin, double xOppositeCorner, double yOppositeCorner) {
            this.xOrigin = xOrigin;
            this.yOrigin = yOrigin;
            this.xOppositeCorner = xOppositeCorner;
            this.yOppositeCorner = yOppositeCorner;
        }

        public RectangleBuilder drawColor(Color drawColor) {
            this.drawColor = drawColor;
            this.optionalArguments.add("draw=" + drawColor.getString());
            return this;
        }

        public RectangleBuilder fillColor(Color fillColor) {
            this.fillColor = fillColor;
            this.optionalArguments.add("fill=" + fillColor.getString());
            return this;
        }


        public RectangleBuilder lineWidthStyle(LineWidthStyle lineWidthStyle) {
            this.lineWidthStyle = lineWidthStyle;
            this.optionalArguments.add(lineWidthStyle.getString());
            return this;
        }

        public RectangleBuilder lineWidth(double lineWidth) {
            this.lineWidth = lineWidth;
            this.optionalArguments.add("line width=" + lineWidth);
            return this;
        }

        public RectangleBuilder lineCap(LineCap lineCap) {
            this.lineCap = lineCap;
            this.optionalArguments.add("line cap=" + lineCap.getString());
            return this;
        }

        public RectangleBuilder lineJoin(LineJoin lineJoin) {
            this.lineJoin = lineJoin;
            this.optionalArguments.add("line join=" + lineJoin.getString());
            return this;
        }

        public RectangleBuilder dashPatternStyle(DashPatternStyle dashPatternStyle) {
            this.dashPatternStyle = dashPatternStyle;
            this.optionalArguments.add(dashPatternStyle.getString());
            return this;
        }



        public Rectangle build() {
            return new Rectangle(this);
        }


//        private void validate() throws IllegalStateException {
//            if (lineWidth != 0) {
//
//            }
//        }


    }
}
