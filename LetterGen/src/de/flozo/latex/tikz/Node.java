package de.flozo.latex.tikz;

import de.flozo.latex.core.*;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;

import java.util.ArrayList;
import java.util.List;

public class Node extends Path {

    public static final CommandName KEYWORD = CommandName.NODE;
    public static final Bracket BODY_BRACKETS = Bracket.CURLY_BRACES;
    public static final boolean DEFAULT_SKIP_LAST_TERMINATOR = true;
    public static final boolean DEFAULT_IS_MATRIX = false;

    // required
    private final List<String> body;

    // optional
    private final String name;
    private final StatementTerminator bodyTerminator;

    private Node(Builder builder) {
        super(builder.position,
                builder.optionalArguments,
                builder.name,
                builder.drawColor,
                builder.fillColor,
                builder.lineWidthStyle,
                builder.lineCap,
                builder.lineJoin,
                builder.dashPatternStyle,
                builder.skipLastTerminator);
        this.body = builder.body;
        this.name = builder.name;
        this.bodyTerminator = builder.bodyTerminator;
    }

    @Override
    public String getInline() {
        StringBuilder sb = new StringBuilder(assembleOpeningTag());
        // Append options if at least one option is present
        if (!optionalArguments.isEmpty()) {
            sb.append(" ").append(inlineOptions());
        }
        // Append remaining required parts
        sb.append(" ");
        sb.append(BODY_BRACKETS.getLeftBracket());
        sb.append(String.join(bodyTerminator.getString(), body));
        sb.append(BODY_BRACKETS.getRightBracket());
        sb.append(TERMINATOR.getString());
        return sb.toString();
    }


    public List<String> getBlock() {
        List<String> lines = new ArrayList<>();
        lines.add(assembleOpeningTag());
        // Append block options if at least one option is present
        if (!optionalArguments.isEmpty()) {
            lines.addAll(blockOptions());
        }
        // Append remaining required parts
        lines.addAll(buildBody().getBlock());
        lines.add(TERMINATOR.getString());
        return lines;
    }

    private FormattedExpressionList buildBody() {
        return new FormattedExpressionList.Builder(body)
                .brackets(Bracket.CURLY_BRACES)
                .terminator(bodyTerminator)
                .skipLastTerminator(skipLastTerminator)
                .indentBlock(true)
                .build();
    }

    private String assembleOpeningTag() {
        StringBuilder sb = new StringBuilder(COMMAND_MARKER_CHAR + KEYWORD.getString());
        // Append name in parentheses if name is not null, empty, or only whitespaces
        if (name != null && !name.strip().equals("")) {
            sb.append(String.format(" (%s)", name));
        }
        // Append optional positioning statement
        if (position != null) {
            sb.append(" at ");
            sb.append(position.getStatement());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Node{" +
                "body=" + body +
                ", name='" + name + '\'' +
                ", bodyTerminator=" + bodyTerminator +
                '}';
    }

    public static class Builder {

        // required
        private final List<String> body;

        // optional
        private String name;
        private Point position;
        private final List<String> optionalArguments = new ArrayList<>();
        private StatementTerminator bodyTerminator = StatementTerminator.NONE;
        private boolean skipLastTerminator = DEFAULT_SKIP_LAST_TERMINATOR;
        private Anchor anchor;
        private FontSize fontSize;
        private Color textColor;
        private Color drawColor;
        private Color fillColor;
        private LineWidthStyle lineWidthStyle;
        private LineCap lineCap;
        private LineJoin lineJoin;
        private DashPatternStyle dashPatternStyle;
        private double xShift;
        private double yShift;
        private double textWidth;
        private double textHeight;
        private double textDepth;
        private double minimumWidth;
        private double minimumHeight;
        private Alignment alignment;
        private double innerXSep;
        private double innerYSep;
        private boolean isMatrix = DEFAULT_IS_MATRIX;

        public Builder(String... body) {
            this(new ArrayList<>(List.of(body)));
        }

        public Builder(List<String> body) {
            this.body = body;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder position(Point position) {
            this.position = position;
            return this;
        }

        public Builder bodyTerminator(StatementTerminator bodyTerminator) {
            this.bodyTerminator = bodyTerminator;
            return this;
        }

        public Builder skipLastTerminator(boolean skipLastTerminator) {
            this.skipLastTerminator = skipLastTerminator;
            return this;
        }

        public Builder anchor(Anchor anchor) {
            this.anchor = anchor;
            addOption("anchor", anchor.getString());
//            this.optionalArguments.add("anchor=" + anchor.getString());
            return this;
        }

        public Builder fontSize(FontSize fontSize) {
            this.fontSize = fontSize;
            addOption("font", COMMAND_MARKER_CHAR + fontSize.getString());
//            this.optionalArguments.add("font=" + COMMAND_MARKER_CHAR + fontSize.getString());
            return this;
        }

        public Builder drawColor(Color drawColor) {
            this.drawColor = drawColor;
//            this.optionalArguments.add("draw=" + drawColor.getString());
            addOption("draw", drawColor.getString());
//            this.optionalArguments.add(createColorPropertyString(drawColor, "draw"));
            return this;
        }

        public Builder textColor(Color textColor) {
            this.textColor = textColor;
//            this.optionalArguments.add("color=" + textColor.getString());
            addOption("color", textColor.getString());
//            this.optionalArguments.add(createColorPropertyString(textColor, "color"));
            return this;
        }

        public Builder fillColor(Color fillColor) {
            this.fillColor = fillColor;
//            this.optionalArguments.add("fill=" + fillColor.getString());
            addOption("fill", fillColor.getString());
//            this.optionalArguments.add(createColorPropertyString(fillColor, "fill"));
            return this;
        }

        public Builder lineWidthStyle(LineWidthStyle lineWidthStyle) {
            this.lineWidthStyle = lineWidthStyle;
            this.optionalArguments.add(lineWidthStyle.getString());
            return this;
        }

        public Builder lineCap(LineCap lineCap) {
            this.lineCap = lineCap;
            addOption("line cap", lineCap.getString());
//            this.optionalArguments.add("line cap=" + lineCap.getString());
            return this;
        }

        public Builder lineJoin(LineJoin lineJoin) {
            this.lineJoin = lineJoin;
            addOption("line join", lineJoin.getString());
//            this.optionalArguments.add("line join=" + lineJoin.getString());
            return this;
        }

        public Builder dashPatternStyle(DashPatternStyle dashPatternStyle) {
            this.dashPatternStyle = dashPatternStyle;
            this.optionalArguments.add(dashPatternStyle.getString());
            return this;
        }

        public Builder xShift(double xShift) {
            return xShift(xShift, LengthUnit.CENTIMETER);
        }

        public Builder xShift(double xShift, LengthUnit lengthUnit) {
            Length length = new Length(xShift, lengthUnit);
            this.xShift = length.getNumericalValue();
            addOption("xshift", length.getFormatted());
//            this.optionalArguments.add("xshift=" + length.getFormatted());
            return this;
        }

        public Builder yShift(double yShift) {
            return yShift(yShift, LengthUnit.CENTIMETER);
        }

        public Builder yShift(double yShift, LengthUnit lengthUnit) {
            Length length = new Length(yShift, lengthUnit);
            this.yShift = length.getNumericalValue();
            addOption("yshift", length.getFormatted());
//            this.optionalArguments.add("yshift=" + length.getFormatted());
            return this;
        }

        public Builder textWidth(double textWidth) {
            return textWidth(textWidth, LengthUnit.CENTIMETER);
        }

        public Builder textWidth(double textWidth, LengthUnit lengthUnit) {
            Length length = new Length(textWidth, lengthUnit);
            this.textWidth = length.getNumericalValue();
            addOption("text width", length.getFormatted());
//            this.optionalArguments.add("text width=" + length.getFormatted());
            return this;
        }

        public Builder textHeight(double textHeight) {
            return textHeight(textHeight, LengthUnit.CENTIMETER);
        }

        public Builder textHeight(double textHeight, LengthUnit lengthUnit) {
            Length length = new Length(textHeight, lengthUnit);
            this.textHeight = length.getNumericalValue();
            addOption("text height", length.getFormatted());
//            this.optionalArguments.add("text height=" + length.getFormatted());
            return this;
        }

        public Builder textDepth(double textDepth) {
            return textDepth(textDepth, LengthUnit.CENTIMETER);
        }

        public Builder textDepth(double textDepth, LengthUnit lengthUnit) {
            Length length = new Length(textDepth, lengthUnit);
            this.textDepth = length.getNumericalValue();
            addOption("text depth", length.getFormatted());
//            this.optionalArguments.add("text depth=" + length.getFormatted());
            return this;
        }

        public Builder minimumWidth(double minimumWidth) {
            return minimumWidth(minimumWidth, LengthUnit.CENTIMETER);
        }

        public Builder minimumWidth(double minimumWidth, LengthUnit lengthUnit) {
            Length length = new Length(minimumWidth, lengthUnit);
            this.minimumWidth = length.getNumericalValue();
            addOption("minimum width", length.getFormatted());
//            this.optionalArguments.add("minimum width=" + length.getFormatted());
            return this;
        }

        public Builder minimumHeight(double minimumHeight) {
            return minimumHeight(minimumHeight, LengthUnit.CENTIMETER);
        }

        public Builder minimumHeight(double minimumHeight, LengthUnit lengthUnit) {
            Length length = new Length(minimumHeight, lengthUnit);
            this.minimumHeight = length.getNumericalValue();
            addOption("minimum height", length.getFormatted());
//            this.optionalArguments.add("minimum height=" + length.getFormatted());
            return this;
        }

        public Builder alignment(Alignment alignment) {
            this.alignment = alignment;
            addOption("align", alignment.getString());
//            this.optionalArguments.add("align=" + alignment.getString());
            return this;
        }

        public Builder innerXSep(double innerXSep) {
            return innerXSep(innerXSep, LengthUnit.CENTIMETER);
        }

        public Builder innerXSep(double innerXSep, LengthUnit lengthUnit) {
            Length length = new Length(innerXSep, lengthUnit);
            this.innerXSep = length.getNumericalValue();
            addOption("inner xsep", length.getFormatted());
//            this.optionalArguments.add("inner xsep=" + length.getFormatted());
            return this;
        }

        public Builder innerYSep(double innerYSep) {
            return innerYSep(innerYSep, LengthUnit.CENTIMETER);
        }

        public Builder innerYSep(double innerYSep, LengthUnit lengthUnit) {
            Length length = new Length(innerYSep, lengthUnit);
            this.innerYSep = length.getNumericalValue();
            addOption("inner ysep", length.getFormatted());
//            this.optionalArguments.add("inner ysep=" + length.getFormatted());
            return this;
        }

        public Builder isMatrix(boolean isMatrix) {
            this.optionalArguments.add(0, PathOperation.MATRIX.getString());
            this.isMatrix = isMatrix;
            return this;
        }

        public Builder addCustomOption(String customOption) {
            this.optionalArguments.add(customOption);
            return this;
        }

        private String createColorPropertyString(Color color, String property) {
            return color != StandardColor.DEFAULT ? property + "=" + color.getString() : "";
        }

        private void addOption(String key, String value) {
            // Skip empty keys or values
            if (!key.isEmpty() && !value.isEmpty()) {
                this.optionalArguments.add(key + "=" + value);
            }
        }


        public Node build() {
            return new Node(this);
        }
    }
}
