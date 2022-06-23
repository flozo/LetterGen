package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.tikz.Alignment;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

public class Body {

    public static final String FIELD_NAME = "body";
    public static final String LINEBREAK = "\\\\";


    private final Point position;

    private final String text;
    private final String salutation;
    private final String valediction;

    private final Length textWidth;
    private final Length paragraphSpacing;

    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;

    public Body(LetterGeometry geometry, LetterColor color, String text) {
        this.position = Point.fromNumbers(geometry.getBorderMarginLeft(), geometry.getBodyY());
        this.text = text;
        this.salutation = "Dear Sir or Madam,";
        this.valediction = "Best regards,";
        this.textWidth = Length.inCentimeter(geometry.getPaperWidth() - geometry.getBorderMarginLeft() - geometry.getBorderMarginRight());
        this.paragraphSpacing = Length.inCentimeter(geometry.getBodyTextParagraphSpacing());
        this.backgroundColor = color.getDraftModeHighlightingBackgroundColor();
        this.borderColor = color.getDraftModeHighlightingBorderColor();
        this.textColor = color.getBodyTextColor();
    }


    public String generate() {
        return new Node.Builder(assembleText())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .textWidth(textWidth)
                .alignment(Alignment.JUSTIFY)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .build().getInline();
    }

    private String assembleText() {
        return salutation + LINEBREAK + spacing() + LINEBREAK + text +
                LINEBREAK + spacing() + LINEBREAK + valediction;
    }

    private String spacing() {
        return String.format("\\vspace{%s}", paragraphSpacing.getFormatted());
    }

    @Override
    public String toString() {
        return "Body{" +
                "position=" + position +
                ", text='" + text + '\'' +
                ", salutation='" + salutation + '\'' +
                ", valediction='" + valediction + '\'' +
                ", textWidth=" + textWidth +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                '}';
    }
}
