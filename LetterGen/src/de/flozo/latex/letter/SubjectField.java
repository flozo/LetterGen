package de.flozo.latex.letter;

import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

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
        Node subjectField = new Node.Builder("\\bf " + subjectText)
                .name("subject field")
                .position(Point.fromNumbers(x, y))
                .anchor(Anchor.SOUTH_WEST)
                .build();
        return subjectField.getInline();
    }
}
