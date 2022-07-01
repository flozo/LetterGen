package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeneral;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.FontSize;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;
import de.flozo.latex.tikz.*;

import java.util.ArrayList;
import java.util.List;

public class Headline {

    public static final String FIELD_NAME = "headline_text_field";

    private final String headlineText;

    private final Point position;
    private final Length xShift;
    private final Length yShift;
    private final Anchor anchor;
    private final Alignment alignment;
    private final FontSize fontSize;
    private final Length textWidth;
    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;

    private final Line headlineSeparationLine;


    public Headline(LetterGeneral general, LetterGeometry geometry, LetterColor color, Address address) {
        this.headlineText = address.getTitle() + " " + address.getFirstName() + " " + address.getLastName();
        this.position = Point.fromNumbers(geometry.getSenderX(), geometry.getSenderY());
        this.xShift = Length.inCentimeter(geometry.getHeadlineXShift());
        this.yShift = Length.inCentimeter(geometry.getHeadlineYShift());
        this.anchor = Anchor.SOUTH_EAST;
        this.alignment = Alignment.RIGHT;
        this.fontSize = FontSize.LARGE3;
        this.textWidth = Length.inCentimeter(geometry.getPaperWidth() - geometry.getBorderMarginLeft() - geometry.getBorderMarginRight());
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getHeadlineTextColor();
        this.headlineSeparationLine = new Line.Builder(0.0, geometry.getPaperHeight() - geometry.getBorderMarginTop(), geometry.getPaperWidth(), 0, CoordinateMode.RELATIVE)
                .drawColor(color.getHeadlineSeplineColor())
                .lineWidth(Length.inPoint(geometry.getHeadlineSeplineLineWidth()))
                .build();
    }


    public List<String> getBlock() {
        List<String> lines = new ArrayList<>();
        lines.add(headlineSeparationLine.getInline());
        lines.addAll(getTextField().getBlock());
        return lines;
    }

    private Node getTextField() {
        return new Node.Builder(headlineText)
                .name(FIELD_NAME)
                .position(position)
                .anchor(anchor)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .alignment(alignment)
                .fontSize(fontSize)
                .textWidth(textWidth)
                .xShift(xShift)
                .yShift(yShift)
                .build();
    }

    @Override
    public String toString() {
        return "Headline{" +
                "headlineText='" + headlineText + '\'' +
                ", position=" + position +
                ", xShift=" + xShift +
                ", yShift=" + yShift +
                ", anchor=" + anchor +
                ", alignment=" + alignment +
                ", fontSize=" + fontSize +
                ", textWidth=" + textWidth +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", headlineSeparationLine=" + headlineSeparationLine +
                '}';
    }
}
