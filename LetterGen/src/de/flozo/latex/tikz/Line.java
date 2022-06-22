package de.flozo.latex.tikz;

import de.flozo.latex.core.CommandName;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.LengthUnit;
import de.flozo.latex.core.color.Color;

import java.util.ArrayList;
import java.util.List;

public class Line extends Path {

    // constants
    public static final CommandName KEYWORD = CommandName.FILLDRAW;
    public static final PathOperation OPERATION = PathOperation.LINE;
    public static final LengthUnit DEFAULT_LENGTH_UNIT = LengthUnit.DEFAULT;
    public static final CoordinateMode DEFAULT_COORDINATE_MODE = CoordinateMode.ABSOLUTE;


    // required
    private final Point next;

    // optional
    private final List<Point> coordinateList;
    private final boolean cycle;

    public Line(Builder builder) {
        super(builder.origin,
                builder.optionalArguments,
                builder.name,
                builder.drawColor,
                builder.fillColor,
                builder.lineWidthStyle,
                builder.lineCap,
                builder.lineJoin,
                builder.dashPatternStyle,
                builder.skipLastTerminator);
        this.next = builder.next;
        this.coordinateList = builder.coordinateList;
        this.cycle = builder.cycle;
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
        sb.append(" ").append(next.getStatement());
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


    @Override
    public String toString() {
        return "Line{" +
                "next=" + next +
                ", coordinateList=" + coordinateList +
                ", cycle=" + cycle +
                ", position=" + position +
                ", optionalArguments=" + optionalArguments +
                ", name='" + name + '\'' +
                ", drawColor=" + drawColor +
                ", fillColor=" + fillColor +
                ", lineWidthStyle=" + lineWidthStyle +
                ", lineCap=" + lineCap +
                ", lineJoin=" + lineJoin +
                ", dashPatternStyle=" + dashPatternStyle +
                '}';
    }


    public static class Builder {

        // required
        private final Point origin;
        private final Point next;

        // optional
        private String name;
        private final List<Point> coordinateList = new ArrayList<>();
        private boolean cycle = false;
        private final List<String> optionalArguments = new ArrayList<>();
        private Color drawColor;
        private Color fillColor;
        private LineWidthStyle lineWidthStyle;
        private Length lineWidth;
        private LineCap lineCap;
        private LineJoin lineJoin;
        private DashPatternStyle dashPatternStyle;
        private boolean skipLastTerminator;


        public Builder(Point origin, Point next) {
            this.origin = origin;
            this.next = next;
        }

        public Builder(double xOrigin, double yOrigin, double xNext, double yNext, CoordinateMode coordinateMode) {
            this(Point.fromNumbersInMode(xOrigin, yOrigin, CoordinateMode.ABSOLUTE), Point.fromNumbersInMode(xNext, yNext, coordinateMode));
        }

        public Builder(double xOrigin, double yOrigin, double xNext, double yNext) {
            this(Point.fromNumbers(xOrigin, yOrigin), Point.fromNumbers(xNext, yNext));
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        // Point coordinates with optional CoordinateMode parameter

        public Builder nextPoint(double x, double y) {
            return nextPoint(x, y, DEFAULT_COORDINATE_MODE);
        }

        public Builder nextPoint(double x, double y, CoordinateMode coordinateMode) {
            return nextPoint(x, y, coordinateMode, DEFAULT_LENGTH_UNIT);
        }

        public Builder nextPoint(double x, double y, LengthUnit lengthUnit) {
            return nextPoint(x, y, DEFAULT_COORDINATE_MODE, lengthUnit);
        }

        public Builder nextPoint(double x, double y, CoordinateMode coordinateMode, LengthUnit lengthUnit) {
            this.coordinateList.add(Point.fromLengthsInMode(Length.fromNumberAndUnit(x, lengthUnit), Length.fromNumberAndUnit(y, lengthUnit), coordinateMode));
            return this;
        }

        public Builder cycle(boolean cycle) {
            this.cycle = cycle;
            return this;
        }

        public Builder drawColor(Color drawColor) {
            this.drawColor = drawColor;
            addOption(NodeOption.DRAW, drawColor.getString());
            return this;
        }

        public Builder fillColor(Color fillColor) {
            this.fillColor = fillColor;
            addOption(NodeOption.FILL, fillColor.getString());
            return this;
        }

        public Builder lineWidthStyle(LineWidthStyle lineWidthStyle) {
            this.lineWidthStyle = lineWidthStyle;
            this.optionalArguments.add(lineWidthStyle.getString());
            return this;
        }

        public Builder lineWidth(Length lineWidth) {
            this.lineWidth = lineWidth;
            addOption(NodeOption.LINE_WIDTH, lineWidth.getFormatted());
            return this;
        }

        public Builder lineCap(LineCap lineCap) {
            this.lineCap = lineCap;
            addOption(NodeOption.LINE_CAP, lineCap.getString());
            return this;
        }

        public Builder lineJoin(LineJoin lineJoin) {
            this.lineJoin = lineJoin;
            addOption(NodeOption.LINE_JOIN, lineJoin.getString());
            return this;
        }

        public Builder dashPatternStyle(DashPatternStyle dashPatternStyle) {
            this.dashPatternStyle = dashPatternStyle;
            this.optionalArguments.add(dashPatternStyle.getString());
            return this;
        }

        public Builder skipLastTerminator(boolean skipLastTerminator) {
            this.skipLastTerminator = skipLastTerminator;
            return this;
        }

        private void addOption(NodeOption key, String value) {
            // Skip empty keys or values
            if (key != null && value != null) {
                if (!key.getString().isEmpty() && !value.isEmpty()) {
                    this.optionalArguments.add(key.getString() + "=" + value);
                }
            }
        }

        public Line build() {
            return new Line(this);
        }
    }
}
