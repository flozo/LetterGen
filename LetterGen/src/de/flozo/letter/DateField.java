package de.flozo.letter;

import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;

public class DateField {

    private double x;
    private double y;
    private String place;
    private String date;

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
