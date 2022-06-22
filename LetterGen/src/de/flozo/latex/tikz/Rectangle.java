package de.flozo.latex.tikz;

import de.flozo.latex.core.CommandName;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.color.Color;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Path {

    // constants
    public static final CommandName KEYWORD = CommandName.FILLDRAW;
    public static final PathOperation OPERATION = PathOperation.RECTANGLE;

    // required
    private final Point oppositeCorner;


    private Rectangle(Builder builder) {
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
        this.oppositeCorner = builder.oppositeCorner;
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
        sb.append(" ").append(oppositeCorner.getStatement());
        sb.append(TERMINATOR.getString());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "oppositeCorner=" + oppositeCorner +
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
        private final Point oppositeCorner;

        // optional
        private String name;
        private final List<String> optionalArguments = new ArrayList<>();
        private Color drawColor;
        private Color fillColor;
        private LineWidthStyle lineWidthStyle;
        private Length lineWidth;
        private LineCap lineCap;
        private LineJoin lineJoin;
        private DashPatternStyle dashPatternStyle;
        private boolean skipLastTerminator;

        public Builder(Point origin, Point oppositeCorner) {
            this.origin = origin;
            this.oppositeCorner = oppositeCorner;
        }

        public Builder(double xOrigin, double yOrigin, double xOppositeCorner, double yOppositeCorner) {
            this(Point.fromNumbers(xOrigin, yOrigin), Point.fromNumbers(xOppositeCorner, yOppositeCorner));
        }


        public Builder name(String name) {
            this.name = name;
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


        public Rectangle build() {
            return new Rectangle(this);
        }
    }
}
