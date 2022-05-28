package de.flozo.latex.tikz;

import de.flozo.latex.core.Color;
import de.flozo.latex.core.CommandName;
import de.flozo.latex.core.LengthUnit;

import java.util.ArrayList;
import java.util.List;

public class Line extends Path {

    // constants
    public static final CommandName KEYWORD = CommandName.FILLDRAW;
    public static final PathOperation OPERATION = PathOperation.LINE;

    // required
    private final double xNext;
    private final double yNext;

    // optional
    private final LengthUnit lengthUnit = LengthUnit.DEFAULT;
    private final List<Point> coordinateList;
    private final CoordinateMode coordinateMode;
    private final boolean cycle;


    public Line(LineBuilder builder) {
        super(builder.xOrigin,
                builder.yOrigin,
                builder.optionalArguments,
                builder.name,
                builder.drawColor,
                builder.fillColor,
                builder.lineWidthStyle,
                builder.lineCap,
                builder.lineJoin,
                builder.dashPatternStyle);
        this.xNext = builder.xNext;
        this.yNext = builder.yNext;
        this.coordinateList = builder.coordinateList;
        this.coordinateMode = builder.coordinateMode;
        this.cycle = builder.cycle;
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
        sb.append(" ").append(coordinates(xNext, yNext, coordinateMode));
        // Append line segments if at least one more is present
        if (!coordinateList.isEmpty()) {
            for (Point point : coordinateList) {
                sb.append(" ").append(OPERATION.getString());
                sb.append(" ").append(point.getStatement());
            }
        }
        // Optional cycle command
        if (cycle) {
            sb.append(" ").append(OPERATION.getString());
            sb.append(" ").append("cycle");
        }
        sb.append(TERMINATOR.getString());
        return sb.toString();
    }


    public static class LineBuilder {

        // required
        private final double xOrigin;
        private final double yOrigin;
        private final double xNext;
        private final double yNext;

        // optional
        private String name;
        private final CoordinateMode coordinateMode;
        private final LengthUnit lengthUnit = LengthUnit.DEFAULT;
        private final List<Point> coordinateList = new ArrayList<>();
        private boolean cycle = false;
        private final List<String> optionalArguments = new ArrayList<>();
        private Color drawColor;
        private Color fillColor;
        private LineWidthStyle lineWidthStyle;
        private double lineWidth;
        private LineCap lineCap;
        private LineJoin lineJoin;
        private DashPatternStyle dashPatternStyle;

        // Constructor with optional CoordinateMode parameter

//        public LineBuilder(String xOrigin, String yOrigin, String xNext, String yNext, CoordinateMode coordinateMode) {
//            this(Double.parseDouble(xOrigin), Double.parseDouble(yOrigin), Double.parseDouble(xNext), Double.parseDouble(yNext), coordinateMode);
//        }


        public LineBuilder(double xOrigin, double yOrigin, double xNext, double yNext) {
            this(xOrigin, yOrigin, xNext, yNext, CoordinateMode.ABSOLUTE);
        }

        public LineBuilder(double xOrigin, double yOrigin, double xNext, double yNext, CoordinateMode coordinateMode) {
            this.xOrigin = xOrigin;
            this.yOrigin = yOrigin;
            this.xNext = xNext;
            this.yNext = yNext;
            this.coordinateMode = coordinateMode;
        }


        public LineBuilder name(String name) {
            this.name = name;
            return this;
        }


        // Point coordinates with optional CoordinateMode parameter

        public LineBuilder nextPoint(double x, double y) {
            return nextPoint(x, y, coordinateMode);
        }

        public LineBuilder nextPoint(double x, double y, CoordinateMode coordinateMode) {
            return nextPoint(x, y, coordinateMode, lengthUnit);
        }

        public LineBuilder nextPoint(double x, double y, LengthUnit lengthUnit) {
            return nextPoint(x, y, coordinateMode, lengthUnit);
        }


        public LineBuilder nextPoint(double x, double y, CoordinateMode coordinateMode, LengthUnit lengthUnit) {
            this.coordinateList.add(new Point.PointBuilder(x, y).coordinateMode(coordinateMode).xUnit(lengthUnit).yUnit(lengthUnit).build());
            return this;
        }


        public LineBuilder cycle(boolean cycle) {
            this.cycle = cycle;
            return this;
        }


        public LineBuilder drawColor(Color drawColor) {
            this.drawColor = drawColor;
            this.optionalArguments.add("draw=" + drawColor.getString());
            return this;
        }

        public LineBuilder fillColor(Color fillColor) {
            this.fillColor = fillColor;
            this.optionalArguments.add("fill=" + fillColor.getString());
            return this;
        }


        public LineBuilder lineWidthStyle(LineWidthStyle lineWidthStyle) {
            this.lineWidthStyle = lineWidthStyle;
            this.optionalArguments.add(lineWidthStyle.getString());
            return this;
        }

        public LineBuilder lineWidth(double lineWidth) {
            this.lineWidth = lineWidth;
            this.optionalArguments.add("line width=" + lineWidth);
            return this;
        }

        public LineBuilder lineCap(LineCap lineCap) {
            this.lineCap = lineCap;
            this.optionalArguments.add("line cap=" + lineCap.getString());
            return this;
        }

        public LineBuilder lineJoin(LineJoin lineJoin) {
            this.lineJoin = lineJoin;
            this.optionalArguments.add("line join=" + lineJoin.getString());
            return this;
        }

        public LineBuilder dashPatternStyle(DashPatternStyle dashPatternStyle) {
            this.dashPatternStyle = dashPatternStyle;
            this.optionalArguments.add(dashPatternStyle.getString());
            return this;
        }


        public Line build() {
            return new Line(this);
        }


    }
}
