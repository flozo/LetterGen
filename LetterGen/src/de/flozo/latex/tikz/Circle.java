package de.flozo.latex.tikz;

import de.flozo.latex.core.CommandName;
import de.flozo.latex.core.color.Color;

import java.util.ArrayList;
import java.util.List;

public class Circle extends Path {

    // constants
    public static final CommandName KEYWORD = CommandName.FILLDRAW;
    public static final PathOperation OPERATION = PathOperation.CIRCLE;

    // optional
    private final double radius;
    private final double xRadius;
    private final double yRadius;

    public Circle(CircleBuilder builder) {
        super(builder.position, builder.optionalArguments, builder.name, builder.drawColor, builder.fillColor, builder.lineWidthStyle, builder.lineCap, builder.lineJoin, builder.dashPatternStyle);
        this.radius = builder.radius;
        this.xRadius = builder.xRadius;
        this.yRadius = builder.yRadius;
    }

    @Override
    public String getInline() {
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
        sb.append(" ").append(position.getStatement());
        sb.append(" ").append(OPERATION.getString());
        sb.append(TERMINATOR.getString());
        return sb.toString();
    }


    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", xRadius=" + xRadius +
                ", yRadius=" + yRadius +
                '}';
    }

    public static class CircleBuilder {
        // required
        private final Point position;

        // optional
        private String name;
        private final List<String> optionalArguments = new ArrayList<>();
        private double radius;
        private double xRadius;
        private double yRadius;
        private Color drawColor;
        private Color fillColor;
        private LineWidthStyle lineWidthStyle;
        private double lineWidth;
        private LineCap lineCap;
        private LineJoin lineJoin;
        private DashPatternStyle dashPatternStyle;

        public CircleBuilder(Point position) {
            this.position = position;
        }

        public CircleBuilder(double xOrigin, double yOrigin) {
            this(Point.fromNumbers(xOrigin,yOrigin));
        }

        public CircleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CircleBuilder radius(double radius) {
            this.radius = radius;
            this.optionalArguments.add("radius=" + radius);
            return this;
        }

        public CircleBuilder xRadius(double xRadius) {
            this.xRadius = xRadius;
            this.optionalArguments.add("x radius=" + xRadius);
            return this;
        }

        public CircleBuilder yRadius(double yRadius) {
            this.yRadius = yRadius;
            this.optionalArguments.add("y radius=" + yRadius);
            return this;
        }

        public CircleBuilder drawColor(Color drawColor) {
            this.drawColor = drawColor;
            this.optionalArguments.add("draw=" + drawColor.getString());
            return this;
        }

        public CircleBuilder fillColor(Color fillColor) {
            this.fillColor = fillColor;
            this.optionalArguments.add("fill=" + fillColor.getString());
            return this;
        }


        public CircleBuilder lineWidthStyle(LineWidthStyle lineWidthStyle) {
            this.lineWidthStyle = lineWidthStyle;
            this.optionalArguments.add(lineWidthStyle.getString());
            return this;
        }

        public CircleBuilder lineWidth(double lineWidth) {
            this.lineWidth = lineWidth;
            this.optionalArguments.add("line width=" + lineWidth);
            return this;
        }

        public CircleBuilder lineCap(LineCap lineCap) {
            this.lineCap = lineCap;
            this.optionalArguments.add("line cap=" + lineCap.getString());
            return this;
        }

        public CircleBuilder lineJoin(LineJoin lineJoin) {
            this.lineJoin = lineJoin;
            this.optionalArguments.add("line join=" + lineJoin.getString());
            return this;
        }

        public CircleBuilder dashPatternStyle(DashPatternStyle dashPatternStyle) {
            this.dashPatternStyle = dashPatternStyle;
            this.optionalArguments.add(dashPatternStyle.getString());
            return this;
        }


        public Circle build() {
            return new Circle(this);
        }


    }
}
