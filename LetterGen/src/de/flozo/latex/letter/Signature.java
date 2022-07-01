package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeneral;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.*;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

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

    public Signature(LetterGeneral general, LetterGeometry geometry, LetterColor color, Address address) {
        this.imageFileName = general.getSignatureImageFile();
        this.name = address.getFirstName() + " " + address.getLastName();
        this.position = Point.fromNumbers(geometry.getBorderMarginLeft(), geometry.getSignatureY());
        this.backgroundColor = color.getDraftModeHighlightingBackgroundColor();
        this.borderColor = color.getDraftModeHighlightingBorderColor();
        this.textColor = color.getSignatureTextColor();
        this.textWidth = Length.inCentimeter(geometry.getPaperWidth() - geometry.getBorderMarginLeft() - geometry.getBorderMarginRight());
        this.imageScaleFactor = geometry.getSignatureImageScaleFactor();
    }


    public String generate() {
        ExpressionList options = new FormattedExpressionList.Builder(Option.SCALE.getString() + "=" + imageScaleFactor).build();
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
                '}';
    }
}
