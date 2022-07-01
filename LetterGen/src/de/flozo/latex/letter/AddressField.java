package de.flozo.latex.letter;

import de.flozo.data.*;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.StatementTerminator;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;
import de.flozo.latex.tikz.Alignment;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

import java.util.List;

public class AddressField {

    public static final String FIELD_NAME = "receiver_address";

    // appearance
    private final Point position;
    private final double width;
    private final double height;
    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;

    // content
    private final String receiverFirstName;
    private final String receiverMiddleName;
    private final String receiverLastName;
    private final String receiverCompany;
    private final String receiverStreet;
    private final String receiverHouseNumber;
    private final String receiverPostalCode;
    private final String receiverCity;


    // Constructor with dependency injection
    public AddressField(LetterGeneral general, LetterGeometry geometry, LetterColor color, Address address) {
        this.position = Point.fromNumbers(geometry.getAddressX(), geometry.getAddressY());
        this.width = geometry.getAddressWidth();
        this.height = geometry.getAddressHeight();
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getAddressTextColor();
        this.receiverFirstName = address.getFirstName();
        this.receiverMiddleName = address.getMiddleName();
        this.receiverLastName = address.getLastName();
        this.receiverCompany = address.getCompany();
        this.receiverStreet = address.getStreet();
        this.receiverHouseNumber = address.getHouseNumber();
        this.receiverPostalCode = address.getPostalCode();
        this.receiverCity = address.getCity();
    }

    public String getAddressField() {
        return new Node.Builder(assembleText())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .minimumWidth(Length.inCentimeter(width))
                .minimumHeight(Length.inCentimeter(2.73))
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .textWidth(Length.inCentimeter(width))
                .alignment(Alignment.LEFT)
                .bodyTerminator(StatementTerminator.DOUBLE_BACKSLASH)
                .build().getInline();
    }

    private List<String> assembleText() {
        return List.of(assembleName(), receiverStreet + " " + receiverHouseNumber, receiverPostalCode + " " + receiverCity);
    }


    private String assembleName() {
        return receiverLastName.isEmpty() || receiverLastName.equals(AddressProperty.NAME_LAST.getStringValue()) ? receiverCompany : receiverFirstName + " " + receiverLastName;
    }

    public Point getPosition() {
        return position;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    @Override
    public String toString() {
        return "AddressField{" +
                "position=" + position +
                ", width=" + width +
                ", height=" + height +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", receiverFirstName='" + receiverFirstName + '\'' +
                ", receiverMiddleName='" + receiverMiddleName + '\'' +
                ", receiverLastName='" + receiverLastName + '\'' +
                ", receiverCompany='" + receiverCompany + '\'' +
                ", receiverStreet='" + receiverStreet + '\'' +
                ", receiverHouseNumber='" + receiverHouseNumber + '\'' +
                ", receiverPostalCode='" + receiverPostalCode + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                '}';
    }
}
