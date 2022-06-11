package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.tikz.Alignment;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;

public class AddressField {

    public static final String FIELD_NAME = "receiver_address";

    // appearance
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    // content
    private final String receiverFirstName;
    private final String receiverMiddleName;
    private final String receiverLastName;
    private final String receiverCompany;
    private final String receiverStreet;
    private final String receiverHouseNumber;
    private final String receiverPostalCode;
    private final String receiverCity;


    public AddressField(LetterGeometry geometry, Address address) {
        this.x = geometry.getAddressX();
        this.y = geometry.getAddressY();
        this.width = geometry.getAddressWidth();
        this.height = geometry.getAddressHeight();
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
        Node addressNode = new Node.NodeBuilder(x, y, assembleText())
                .name(FIELD_NAME)
                .anchor(Anchor.NORTH_WEST)
                .minimumWidth(width)
                .minimumHeight(2.73)
                .textWidth(width)
                .alignment(Alignment.LEFT)
                .build();
        return addressNode.getStatement();
    }

    private String assembleText() {
        return assembleName() + "\\\\" +
                receiverStreet + " " + receiverHouseNumber + "\\\\" +
                receiverPostalCode + " " + receiverCity;
    }

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
