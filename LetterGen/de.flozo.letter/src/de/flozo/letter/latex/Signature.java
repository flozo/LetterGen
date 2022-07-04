package de.flozo.letter.latex;

import de.flozo.letter.data.Address;
import de.flozo.letter.data.LetterColor;
import de.flozo.letter.data.LetterGeneral;
import de.flozo.letter.data.LetterGeometry;
import de.flozo.io.File;
import de.flozo.latex.core.*;
import de.flozo.latex.color.Color;
import de.flozo.latex.color.StandardColor;
import de.flozo.latex.tikz.options.Anchor;
import de.flozo.latex.tikz.commands.Node;
import de.flozo.latex.tikz.commands.Point;

import java.util.ArrayList;
import java.util.List;

public class Signature {

    public static final String FIELD_NAME = "signature_field";

    // content
    private final String imageFileName;
    private final String name;

    // appearance
    private final Point position;
    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;
    private final Length textWidth;
    private final double imageScaleFactor;
    private final boolean imagePlaceholderOn;

    public Signature(LetterGeneral general, LetterGeometry geometry, LetterColor color, Address address) {
        this.imageFileName = general.getSignatureImageFile();
        this.name = address.getFirstName() + " " + address.getLastName();
        this.position = Point.fromNumbers(geometry.getBorderMarginLeft(), geometry.getSignatureY());
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getSignatureTextColor();
        this.textWidth = Length.inCentimeter(geometry.getPaperWidth() - geometry.getBorderMarginLeft() - geometry.getBorderMarginRight());
        this.imageScaleFactor = geometry.getSignatureImageScaleFactor();
        this.imagePlaceholderOn = general.isImagePlaceholderOn();
    }


    public String generate() {
        if (!File.fromString(imageFileName).exists()) {
            return "";
        }
        List<String> optionList = new ArrayList<>();
        optionList.add(Option.SCALE.getString() + "=" + imageScaleFactor);
        if (imagePlaceholderOn) {
            optionList.add(Option.DRAFT.getString());
        }
        ExpressionList options = new FormattedExpressionList.Builder(optionList)
                .terminator(StatementTerminator.COMMA)
                .build();
        Includegraphics includegraphics = new Includegraphics(imageFileName, options);
        return new Node.Builder(includegraphics.getInline() + "\\\\" + name)
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .textWidth(textWidth)
                .build().getInline();
    }


    @Override
    public String toString() {
        return "Signature{" +
                "imageFileName='" + imageFileName + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", textWidth=" + textWidth +
                ", imageScaleFactor=" + imageScaleFactor +
                ", imagePlaceholderOn=" + imagePlaceholderOn +
                '}';
    }
}
