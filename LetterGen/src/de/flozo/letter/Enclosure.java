package de.flozo.letter;

import de.flozo.latex.core.Code;
import de.flozo.latex.core.ExpressionList;
import de.flozo.latex.core.StatementTerminator;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;

public class Enclosure {

    private String enclosureTag;
    private ExpressionList enclosureItems;
    private double x;
    private double y;


    public Enclosure(String enclosureTag, ExpressionList enclosureItems, double x, double y) {
        this.enclosureTag = enclosureTag;
        this.enclosureItems = enclosureItems;
        this.x = x;
        this.y = y;
    }


    public String generate() {
        Code enclosureText = new Code.CodeBuilder(enclosureItems)
                .terminator(StatementTerminator.COMMA)
                .inlineSpacing(true)
                .build();
        Node enclosure = new Node.NodeBuilder(x, y, enclosureTag + ": " + enclosureText.getInline())
                .name("enclosure")
                .anchor(Anchor.NORTH_WEST)
                .textWidth(16.5)
                .build();
        return enclosure.getStatement();

    }
}
