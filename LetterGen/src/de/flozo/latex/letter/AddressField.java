package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.LengthUnit;
import de.flozo.latex.core.StatementTerminator;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.tikz.Alignment;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

import java.util.List;

public class AddressField {

    public static final String FIELD_NAME = "receiver_address";

    // appearance
    private final double x;
    private final double y;
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
    public AddressField(LetterGeometry geometry, LetterColor color, Address address) {
        this.x = geometry.getAddressX();
        this.y = geometry.getAddressY();
        this.width = geometry.getAddressWidth();
        this.height = geometry.getAddressHeight();
        this.backgroundColor = color.getDraftModeHighlightingBackgroundColor();
        this.borderColor = color.getDraftModeHighlightingBorderColor();
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
                .position(Point.fromNumbers(x, y))
                .anchor(Anchor.NORTH_WEST)
                .minimumWidth(Length.createFromNumberAndUnit(width, LengthUnit.CENTIMETER))
                .minimumHeight(Length.createFromNumberAndUnit(2.73, LengthUnit.CENTIMETER))
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .textWidth(Length.createFromNumberAndUnit(width, LengthUnit.CENTIMETER))
                .alignment(Alignment.LEFT)
                .bodyTerminator(StatementTerminator.DOUBLE_BACKSLASH)
                .build().getInline();
    }

    private List<String> assembleText() {
        return List.of(assembleName(), receiverStreet + " " + receiverHouseNumber, receiverPostalCode + " " + receiverCity);
    }

//    private String assembleText() {
//        return assembleName() + "\\\\" +
//                receiverStreet + " " + receiverHouseNumber + "\\\\" +
//                receiverPostalCode + " " + receiverCity;
//    }

    private String assembleName() {
        return receiverLastName.isEmpty() ? receiverCompany : receiverFirstName + " " + receiverLastName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getReceiverFirstName() {
        return receiverFirstName;
    }

    public String getReceiverMiddleName() {
        return receiverMiddleName;
    }

    public String getReceiverLastName() {
        return receiverLastName;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public String getReceiverStreet() {
        return receiverStreet;
    }

    public String getReceiverHouseNumber() {
        return receiverHouseNumber;
    }

    public String getReceiverPostalCode() {
        return receiverPostalCode;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    @Override
    public String toString() {
        return "AddressField{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
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
