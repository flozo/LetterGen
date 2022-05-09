package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

public class Node {

    public static final String KEYWORD = CommandName.NODE.getString();

    // required
    private final double x;
    private final double y;
    private final String text;

    // optional
    private final Anchor anchor;
    private final FontSize fontSize;
    private final Color color;
    private final double xShift;
    private final double yShift;
    private final double textWidth;

    public Node(NodeBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.text = builder.text;
        this.anchor = builder.anchor;
        this.fontSize = builder.fontSize;
        this.color = builder.color;
        this.xShift = builder.xShift;
        this.yShift = builder.yShift;
        this.textWidth = builder.textWidth;
    }


    public static class NodeBuilder {

        // required
        private final double x;
        private final double y;
        private final String text;

        // optional
        private Anchor anchor = Anchor.CENTER;
        private FontSize fontSize = FontSize.NORMAL_SIZE;
        private Color color = new Color(ColorScheme.GREYS, ColorLetter.M);
        private double xShift = 0.0d;
        private double yShift = 0.0d;
        private double textWidth;


        public NodeBuilder(double x, double y, String text) {
            this.x = x;
            this.y = y;
            this.text = text;
        }

        public NodeBuilder anchor(Anchor anchor) {
            this.anchor = anchor;
            return this;
        }

        public NodeBuilder fontSize(FontSize fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public NodeBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public NodeBuilder xShift(double xShift) {
            this.xShift = xShift;
            return this;
        }
        public NodeBuilder yShift(double yShift) {
            this.yShift = yShift;
            return this;
        }
        public NodeBuilder textWidth(double textWidth) {
            this.textWidth = textWidth;
            return this;
        }


    }
}
