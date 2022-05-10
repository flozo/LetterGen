package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Node {

    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final String KEYWORD = CommandName.NODE.getString();
    public static final Bracket BODY_BRACKETS = Bracket.CURLY_BRACES;
    public static final Bracket OPTIONS_BRACKETS = Bracket.SQUARE_BRACKETS;
    public static final StatementTerminator TERMINATOR = StatementTerminator.SEMICOLON;

    // required
    private final double x;
    private final double y;
    private final String text;

    // optional
    private final String name;
    private final List<String> optionalArguments;
    private final Anchor anchor;
    private final FontSize fontSize;
    private final Color color;
    private final double xShift;
    private final double yShift;
    private final double textWidth;
    private double textDepth;
    private double minimumWidth;
    private double minimumHeight;
    private Alignment alignment;


    public Node(NodeBuilder builder) {
        this.name = builder.name;
        this.optionalArguments = builder.optionalArguments;
        this.x = builder.x;
        this.y = builder.y;
        this.text = builder.text;
        this.anchor = builder.anchor;
        this.fontSize = builder.fontSize;
        this.color = builder.color;
        this.xShift = builder.xShift;
        this.yShift = builder.yShift;
        this.textWidth = builder.textWidth;
        this.textDepth = builder.textDepth;
        this.minimumWidth = builder.minimumWidth;
        this.minimumHeight = builder.minimumHeight;
        this.alignment = builder.alignment;
    }


    public String getStatement() {
        StringBuilder sb = new StringBuilder(COMMAND_MARKER_CHAR + KEYWORD);
        if (name != null && !name.strip().equals("")) {
            sb.append(String.format(" (%s)", name));
        }
        if (!optionalArguments.isEmpty()) {
            sb.append(" ").append(inlineOptions());
        }
        sb.append(" at ");
        sb.append(coordinates(x, y));
        sb.append(" ");
        sb.append(BODY_BRACKETS.getLeftBracket());
        sb.append(text);
        sb.append(BODY_BRACKETS.getRightBracket());
        sb.append(TERMINATOR.getString());
        return sb.toString();
    }

    private String inlineOptions() {
        Code options = new Code.CodeBuilder(new ExpressionList(optionalArguments))
                .brackets(OPTIONS_BRACKETS)
                .terminator(StatementTerminator.COMMA)
                .skipLast(true)
                .inlineSpacing(true)
                .build();
        return options.getInline();
    }

    private String coordinates(double x, double y) {
        // Avoid trailing zeros; ensure point is used as decimal separator
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return String.format("(%s, %s)", df.format(x), df.format(y));
    }


    public static class NodeBuilder {

        // required
        private final double x;
        private final double y;
        private final String text;

        // optional
        private String name;
        private List<String> optionalArguments = new ArrayList<>();
        private Anchor anchor;
        private FontSize fontSize;
        private Color color;
        private double xShift;
        private double yShift;
        private double textWidth;
        private double textDepth;
        private double minimumWidth;
        private double minimumHeight;
        private Alignment alignment;


        public NodeBuilder(double x, double y, String text) {
            this.x = x;
            this.y = y;
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

        public NodeBuilder color(Color color) {
            this.color = color;
            this.optionalArguments.add("color=" + color.getString());
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
