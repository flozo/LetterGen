package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

public class DateField {

    public static final String FIELD_NAME = "date_field";


    private final Point position;

    private final String place;
    private final String date;

    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;

    public DateField(LetterGeometry geometry, LetterColor color, String place, String date) {
        this.position = Point.fromNumbers(geometry.getPaperWidth() - geometry.getBorderMarginRight(), geometry.getDateY());
        this.place = place;
        this.date = date;
        this.backgroundColor = color.getDraftModeHighlightingBackgroundColor();
        this.borderColor = color.getDraftModeHighlightingBorderColor();
        this.textColor = color.getDateTextColor();
    }

    public String generate() {
        return new Node.Builder(place + ", " + date)
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.SOUTH_EAST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .build().getInline();
    }

    @Override
    public String toString() {
        return "DateField{" +
                "position=" + position +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                '}';
    }
}
