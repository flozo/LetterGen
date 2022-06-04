package de.flozo.latex.letter;

import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;

public class DateField {

    private final double x;
    private final double y;
    private final String place;
    private final String date;

    public DateField(double x, double y, String place, String date) {
        this.x = x;
        this.y = y;
        this.place = place;
        this.date = date;
    }

    public String generate() {
        Node subjectField = new Node.NodeBuilder(x, y, place + ", " + date)
                .name("date field")
                .anchor(Anchor.SOUTH_EAST)
                .build();
        return subjectField.getStatement();
    }

}
