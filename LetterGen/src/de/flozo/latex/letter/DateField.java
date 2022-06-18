package de.flozo.latex.letter;

import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

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
        Node subjectField = new Node.Builder(place + ", " + date)
                .name("date field")
                .position(Point.fromNumbers(x, y))
                .anchor(Anchor.SOUTH_EAST)
                .build();
        return subjectField.getInline();
    }

}
