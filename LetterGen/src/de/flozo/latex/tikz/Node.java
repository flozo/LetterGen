package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

import java.util.ArrayList;
import java.util.List;

public class Node extends Path {

    public static final CommandName KEYWORD = CommandName.NODE;
    public static final Bracket BODY_BRACKETS = Bracket.CURLY_BRACES;

    // required
    private final String text;

    // optional
    private final String name;
//    private final List<String> optionalArguments;
//    private final Anchor anchor;
//    private final FontSize fontSize;
//    private final Color textColor;
//    private final Color drawColor;
//    private final Color fillColor;
//    private final LineWidthStyle lineWidthStyle;
//    private final LineCap lineCap;
//    private final LineJoin lineJoin;
//    private final DashPatternStyle dashPatternStyle;
//    private final double xShift;
//    private final double yShift;
//    private final double textWidth;
//    private double textDepth;
//    private double minimumWidth;
//    private double minimumHeight;
//    private Alignment alignment;
//    private double innerXSep;
//    private double innerYSep;

    private Node(NodeBuilder builder) {
        super(builder.xOrigin,
                builder.yOrigin,
                builder.optionalArguments,
                builder.name, builder.drawColor,
                builder.fillColor,
                builder.lineWidthStyle,
                builder.lineCap,
                builder.lineJoin,
                builder.dashPatternStyle);
        this.text = builder.text;
        this.name = builder.name;
//        this.anchor = builder.anchor;
//        this.fontSize = builder.fontSize;
//        this.textColor = builder.textColor;
//        this.xShift = builder.xShift;
//        this.yShift = builder.yShift;
//        this.textWidth = builder.textWidth;
//        this.textDepth = builder.textDepth;
//        this.minimumWidth = builder.minimumWidth;
//        this.minimumHeight = builder.minimumHeight;
//        this.alignment = builder.alignment;
//        this.innerXSep = builder.innerXSep;
//        this.innerYSep = builder.innerYSep;
    }

    @Override
    public String getStatement() {
        StringBuilder sb = new StringBuilder(COMMAND_MARKER_CHAR + KEYWORD.getString());
        // Append name in parentheses if name is not null, empty, or only whitespaces
        if (name != null && !name.strip().equals("")) {
            sb.append(String.format(" (%s)", name));
        }
        // Append required positioning statement
        sb.append(" at ");
        sb.append(coordinates(xOrigin, yOrigin));
        // Append options if at least one option is present
        if (!optionalArguments.isEmpty()) {
            sb.append(" ").append(inlineOptions());
        }
        // Append remaining required parts
        sb.append(" ");
        sb.append(BODY_BRACKETS.getLeftBracket());
        sb.append(text);
        sb.append(BODY_BRACKETS.getRightBracket());
        sb.append(TERMINATOR.getString());
        return sb.toString();
    }


    public static class NodeBuilder {

        // required
        private final double xOrigin;
        private final double yOrigin;
        private final String text;

        // optional
        private String name;
        private final List<String> optionalArguments = new ArrayList<>();
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


        public NodeBuilder(double xOrigin, double yOrigin, String text) {
            this.xOrigin = xOrigin;
            this.yOrigin = yOrigin;
            this.text = text;
        }

        public NodeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public NodeBuilder anchor(Anchor anchor) {
            this.anchor = anchor;
            this.optionalArguments.add("anchor=" + anchor.getString());
            return this;
        }

        public NodeBuilder fontSize(FontSize fontSize) {
            this.fontSize = fontSize;
            this.optionalArguments.add("font=" + COMMAND_MARKER_CHAR + fontSize.getString());
            return this;
        }

        public NodeBuilder drawColor(Color drawColor) {
            this.drawColor = drawColor;
            this.optionalArguments.add("draw=" + drawColor.getString());
            return this;
        }

        public NodeBuilder textColor(Color textColor) {
            this.textColor = textColor;
            this.optionalArguments.add("color=" + textColor.getString());
            return this;
        }

        public NodeBuilder fillColor(Color fillColor) {
            this.fillColor = fillColor;
            this.optionalArguments.add("fill=" + fillColor.getString());
            return this;
        }

        public NodeBuilder lineWidthStyle(LineWidthStyle lineWidthStyle) {
            this.lineWidthStyle = lineWidthStyle;
            this.optionalArguments.add(lineWidthStyle.getString());
            return this;
        }

        public NodeBuilder lineCap(LineCap lineCap) {
            this.lineCap = lineCap;
            this.optionalArguments.add("line cap=" + lineCap.getString());
            return this;
        }

        public NodeBuilder lineJoin(LineJoin lineJoin) {
            this.lineJoin = lineJoin;
            this.optionalArguments.add("line join=" + lineJoin.getString());
            return this;
        }

        public NodeBuilder dashPatternStyle(DashPatternStyle dashPatternStyle) {
            this.dashPatternStyle = dashPatternStyle;
            this.optionalArguments.add(dashPatternStyle.getString());
            return this;
        }

        public NodeBuilder xShift(double xShift) {
            return xShift(xShift, LengthUnit.CENTIMETER);
        }

        public NodeBuilder xShift(double xShift, LengthUnit lengthUnit) {
            Length length = new Length(xShift, lengthUnit);
            this.xShift = length.getNumericalValue();
            this.optionalArguments.add("xshift=" + length.getString());
            return this;
        }

        public NodeBuilder yShift(double yShift) {
            return yShift(yShift, LengthUnit.CENTIMETER);
        }

        public NodeBuilder yShift(double yShift, LengthUnit lengthUnit) {
            Length length = new Length(yShift, lengthUnit);
            this.yShift = length.getNumericalValue();
            this.optionalArguments.add("yshift=" + length.getString());
            return this;
        }

        public NodeBuilder textWidth(double textWidth) {
            return textWidth(textWidth, LengthUnit.CENTIMETER);
        }

        public NodeBuilder textWidth(double textWidth, LengthUnit lengthUnit) {
            Length length = new Length(textWidth, lengthUnit);
            this.textWidth = length.getNumericalValue();
            this.optionalArguments.add("text width=" + length.getString());
            return this;
        }

        public NodeBuilder textHeight(double textHeight) {
            return textHeight(textHeight, LengthUnit.CENTIMETER);
        }

        public NodeBuilder textHeight(double textHeight, LengthUnit lengthUnit) {
            Length length = new Length(textHeight, lengthUnit);
            this.textHeight = length.getNumericalValue();
            this.optionalArguments.add("text height=" + length.getString());
            return this;
        }

        public NodeBuilder textDepth(double textDepth) {
            return textDepth(textDepth, LengthUnit.CENTIMETER);
        }

        public NodeBuilder textDepth(double textDepth, LengthUnit lengthUnit) {
            Length length = new Length(textDepth, lengthUnit);
            this.textDepth = length.getNumericalValue();
            this.optionalArguments.add("text depth=" + length.getString());
            return this;
        }

        public NodeBuilder minimumWidth(double minimumWidth) {
            return minimumWidth(minimumWidth, LengthUnit.CENTIMETER);
        }

        public NodeBuilder minimumWidth(double minimumWidth, LengthUnit lengthUnit) {
            Length length = new Length(minimumWidth, lengthUnit);
            this.minimumWidth = length.getNumericalValue();
            this.optionalArguments.add("minimum width=" + length.getString());
            return this;
        }

        public NodeBuilder minimumHeight(double minimumHeight) {
            return minimumHeight(minimumHeight, LengthUnit.CENTIMETER);
        }

        public NodeBuilder minimumHeight(double minimumHeight, LengthUnit lengthUnit) {
            Length length = new Length(minimumHeight, lengthUnit);
            this.minimumHeight = length.getNumericalValue();
            this.optionalArguments.add("minimum height=" + length.getString());
            return this;
        }

        public NodeBuilder alignment(Alignment alignment) {
            this.alignment = alignment;
            this.optionalArguments.add("align=" + alignment.getString());
            return this;
        }

        public NodeBuilder innerXSep(double innerXSep) {
            return innerXSep(innerXSep, LengthUnit.CENTIMETER);
        }

        public NodeBuilder innerXSep(double innerXSep, LengthUnit lengthUnit) {
            Length length = new Length(innerXSep, lengthUnit);
            this.innerXSep = length.getNumericalValue();
            this.optionalArguments.add("inner xsep=" + length.getString());
            return this;
        }

        public NodeBuilder innerYSep(double innerYSep) {
            return innerYSep(innerYSep, LengthUnit.CENTIMETER);
        }

        public NodeBuilder innerYSep(double innerYSep, LengthUnit lengthUnit) {
            Length length = new Length(innerYSep, lengthUnit);
            this.innerYSep = length.getNumericalValue();
            this.optionalArguments.add("inner ysep=" + length.getString());
            return this;
        }


        public Node build() {
            return new Node(this);
        }

    }
}
