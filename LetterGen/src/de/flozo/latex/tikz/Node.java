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
    private final Anchor anchor;
    private final FontSize fontSize;
    private final double xShift;
    private final double yShift;
    private final double textWidth;
    private double textDepth;
    private double minimumWidth;
    private double minimumHeight;
    private Alignment alignment;


    public Node(NodeBuilder builder) {
        super(builder.xOrigin, builder.yOrigin, builder.optionalArguments, builder.name, builder.drawColor, builder.fillColor, builder.lineWidthStyle, builder.lineCap, builder.lineJoin, builder.dashPatternStyle);
        this.text = builder.text;
        this.anchor = builder.anchor;
        this.fontSize = builder.fontSize;
        this.xShift = builder.xShift;
        this.yShift = builder.yShift;
        this.textWidth = builder.textWidth;
        this.textDepth = builder.textDepth;
        this.minimumWidth = builder.minimumWidth;
        this.minimumHeight = builder.minimumHeight;
        this.alignment = builder.alignment;
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
        sb.append(" at ");
        sb.append(coordinates(xOrigin, yOrigin));
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
        private double textDepth;
        private double minimumWidth;
        private double minimumHeight;
        private Alignment alignment;


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
            this.xShift = xShift;
            this.optionalArguments.add("xshift=" + xShift);
            return this;
        }

        public NodeBuilder yShift(double yShift) {
            this.yShift = yShift;
            this.optionalArguments.add("yshift=" + yShift);
            return this;
        }

        public NodeBuilder textWidth(double textWidth) {
            this.textWidth = textWidth;
            this.optionalArguments.add("text width=" + textWidth);
            return this;
        }

        public NodeBuilder textDepth(double textDepth) {
            this.textDepth = textDepth;
            this.optionalArguments.add("text depth=" + textDepth);
            return this;
        }

        public NodeBuilder minimumWidth(double minimumWidth) {
            this.minimumWidth = minimumWidth;
            this.optionalArguments.add("minimum width=" + minimumWidth);
            return this;
        }

        public NodeBuilder minimumHeight(double minimumHeight) {
            this.minimumHeight = minimumHeight;
            this.optionalArguments.add("minimum height=" + minimumHeight);
            return this;
        }

        public NodeBuilder alignment(Alignment alignment) {
            this.alignment = alignment;
            this.optionalArguments.add("align=" + alignment.getString());
            return this;
        }


        public Node build() {
            return new Node(this);
        }

    }
}
