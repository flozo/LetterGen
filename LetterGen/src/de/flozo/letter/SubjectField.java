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

public class SubjectField {

    public static final String FIELD_NAME = "subject_field";


    private final Point position;
    private final String subjectText;

    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;
    private final FontSize fontSize;


    public SubjectField(LetterGeneral general, LetterGeometry geometry, LetterColor color, LetterFont font, String subjectText) {
        this.position = Point.fromNumbers(geometry.getBorderMarginLeft(), geometry.getSubjectY());
        this.subjectText = subjectText;
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getSubjectTextColor();
        this.fontSize = font.getSubjectFieldFontSize();
    }

    public String generate() {
        return new Node.Builder("\\bf " + subjectText)
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.SOUTH_WEST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .fontSize(fontSize)
                .build().getInline();
    }

    @Override
    public String toString() {
        return "SubjectField{" +
                "position=" + position +
                ", subjectText='" + subjectText + '\'' +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", fontSize=" + fontSize +
                '}';
    }
}
