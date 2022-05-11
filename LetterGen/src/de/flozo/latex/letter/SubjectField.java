package de.flozo.latex.letter;

import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;

public class SubjectField {

    private double x;
    private double y;
    private String subjectText;

    public SubjectField(double x, double y, String subjectText) {
        this.x = x;
        this.y = y;
        this.subjectText = subjectText;
    }

    public String generate() {
        Node subjectField = new Node.NodeBuilder(x, y, "\\bf " + subjectText)
                .name("subject field")
                .anchor(Anchor.SOUTH_WEST)
                .build();
        return subjectField.getStatement();
    }
}
