package de.flozo.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterFont;
import de.flozo.data.LetterGeneral;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.FontSize;
import de.flozo.latex.color.Color;
import de.flozo.latex.color.StandardColor;
import de.flozo.latex.tikz.options.Anchor;
import de.flozo.latex.tikz.commands.Node;
import de.flozo.latex.tikz.commands.Point;

public class DateField {

    public static final String FIELD_NAME = "date_field";


    private final Point position;

    private final String place;
    private final String date;

    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;
    private final FontSize fontSize;

    public DateField(LetterGeneral general, LetterGeometry geometry, LetterColor color, LetterFont font, String place, String date) {
        this.position = Point.fromNumbers(geometry.getPaperWidth() - geometry.getBorderMarginRight(), geometry.getDateY());
        this.place = place;
        this.date = date;
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getDateTextColor();
        this.fontSize = font.getDateFieldFontSize();
    }

    public String generate() {
        return new Node.Builder(place + ", " + date)
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.SOUTH_EAST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .fontSize(fontSize)
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
                ", fontSize=" + fontSize +
                '}';
    }
}
