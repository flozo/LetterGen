package de.flozo.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterFont;
import de.flozo.data.LetterGeneral;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.FontSize;
import de.flozo.latex.core.Length;
import de.flozo.latex.color.Color;
import de.flozo.latex.color.StandardColor;
import de.flozo.latex.tikz.options.Alignment;
import de.flozo.latex.tikz.options.Anchor;
import de.flozo.latex.tikz.commands.Node;
import de.flozo.latex.tikz.commands.Point;

import java.util.ArrayList;
import java.util.List;

public class Body {

    public static final String FIELD_NAME = "body";
    public static final String LATEX_LINEBREAK = "\\\\";
    public static final String NEWLINE = "";
    public static final String LINEBREAK = LATEX_LINEBREAK + NEWLINE;


    private final Point position;

    private final List<String> textLines;
    private final String salutation;
    private final String valediction;

    private final Length textWidth;
    private final Length paragraphSpacing;

    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;
    private final FontSize fontSize;


    public Body(LetterGeneral general, LetterGeometry geometry, LetterColor color, LetterFont font,List<String> textLines) {
        this.position = Point.fromNumbers(geometry.getBorderMarginLeft(), geometry.getBodyY());
        this.textLines = textLines;
        this.salutation = "Dear Sir or Madam,";
        this.valediction = "Best regards,";
        this.textWidth = Length.inCentimeter(geometry.getPaperWidth() - geometry.getBorderMarginLeft() - geometry.getBorderMarginRight());
        this.paragraphSpacing = Length.inCentimeter(geometry.getBodyTextParagraphSpacing());
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getBodyTextColor();
        this.fontSize = font.getBodyFontSize();
    }

    public List<String> getBlock() {
        return new Node.Builder(assembleText())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .textWidth(textWidth)
                .alignment(Alignment.JUSTIFY)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .fontSize(fontSize)
                .build().getBlock();
    }

    private List<String> assembleText() {
        appendToLastLine();
        List<String> lines = new ArrayList<>();
        lines.add(salutation + LINEBREAK);
        lines.add(spacing() + LINEBREAK);
        lines.addAll(textLines);
        lines.add(spacing() + LINEBREAK);
        lines.add(valediction);
        return lines;
    }

    private void appendToLastLine() {
        if (textLines.isEmpty()) {
            return;
        }
        int index = textLines.size() - 1;
        String lastLine = String.join("", textLines.get(index), LINEBREAK);
        textLines.set(index, lastLine);
    }

    private String spacing() {
        return String.format("\\vspace{%s}", paragraphSpacing.getFormatted());
    }

    @Override
    public String toString() {
        return "Body{" +
                "position=" + position +
                ", textLines=" + textLines +
                ", salutation='" + salutation + '\'' +
                ", valediction='" + valediction + '\'' +
                ", textWidth=" + textWidth +
                ", paragraphSpacing=" + paragraphSpacing +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", fontSize=" + fontSize +
                '}';
    }
}
