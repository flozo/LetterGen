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

    private ExpressionList buildBody() {
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
        private Length xShift;
        private Length yShift;
        private Length textWidth;
        private Length textHeight;
        private Length textDepth;
        private Length minimumWidth;
        private Length minimumHeight;
        private Alignment alignment;
        private Length innerXSep;
        private Length innerYSep;
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
            addOption(NodeOption.ANCHOR, anchor.getString());
            return this;
        }

        public Builder fontSize(FontSize fontSize) {
            this.fontSize = fontSize;
            addOption(NodeOption.FONT, COMMAND_MARKER_CHAR + fontSize.getString());
            return this;
        }

        public Builder drawColor(Color drawColor) {
            this.drawColor = drawColor;
            addOption(NodeOption.DRAW, drawColor.getString());
            return this;
        }

        public Builder textColor(Color textColor) {
            this.textColor = textColor;
            addOption(NodeOption.TEXT, textColor.getString());
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

        public Builder xShift(Length xShift) {
            this.xShift = xShift;
            addOption(NodeOption.X_SHIFT, xShift.getFormatted());
            return this;
        }

        public Builder yShift(Length yShift) {
            this.yShift = yShift;
            addOption(NodeOption.Y_SHIFT, yShift.getFormatted());
            return this;
        }

        public Builder textWidth(Length textWidth) {
            this.textWidth = textWidth;
            addOption(NodeOption.TEXT_WIDTH, textWidth.getFormatted());
            return this;
        }

        public Builder textHeight(Length textHeight) {
            this.textHeight = textHeight;
            addOption(NodeOption.TEXT_HEIGHT, textHeight.getFormatted());
            return this;
        }

        public Builder textDepth(Length textDepth) {
            this.textDepth = textDepth;
            addOption(NodeOption.TEXT_DEPTH, textDepth.getFormatted());
            return this;
        }


        public Builder minimumWidth(Length minimumWidth) {
            this.minimumWidth = minimumWidth;
            addOption(NodeOption.MINIMUM_WIDTH, minimumWidth.getFormatted());
            return this;
        }

        public Builder minimumHeight(Length minimumHeight) {
            this.minimumHeight = minimumHeight;
            addOption(NodeOption.MINIMUM_HEIGHT, minimumHeight.getFormatted());
            return this;
        }

        public Builder alignment(Alignment alignment) {
            this.alignment = alignment;
            addOption(NodeOption.ALIGN, alignment.getString());
            return this;
        }

        public Builder innerXSep(Length innerXSep) {
            this.innerXSep = innerXSep;
            addOption(NodeOption.INNER_X_SEP, innerXSep.getFormatted());
            return this;
        }

        public Builder innerYSep(Length innerYSep) {
            this.innerYSep = innerYSep;
            addOption(NodeOption.INNER_Y_SEP, innerYSep.getFormatted());
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

        private void addOption(NodeOption key, String value) {
            // Skip empty keys or values
            if (key != null && value != null) {
                if (!key.getString().isEmpty() && !value.isEmpty()) {
                    this.optionalArguments.add(key.getString() + "=" + value);
                }
            }
        }


        public Node build() {
            return new Node(this);
        }
    }
}
