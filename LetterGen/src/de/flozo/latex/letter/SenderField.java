package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.tikz.Alignment;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;

public class SenderField {

    public static final String FIELD_NAME = "sender_address";

//    public SenderField(double x, double y, double width, double height, Anchor anchor) {
//        super(FIELD_NAME, x, y, width, height, anchor);
//    }
//
//    public SenderField(String name, double x, double y, double width, double height, Anchor anchor, FontSize fontSize) {
//        super(FIELD_NAME, x, y, width, height, anchor, fontSize);
//    }


    // appearance
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    // content
    private final String senderFirstName;
    private final String senderMiddleName;
    private final String senderLastName;
    private final String senderCompany;
    private final String senderStreet;
    private final String senderHouseNumber;
    private final String senderPostalCode;
    private final String senderCity;


    public SenderField(LetterGeometry geometry, Address address) {
        this.x = geometry.getAddressX();
        this.y = geometry.getAddressY();
        this.width = geometry.getAddressWidth();
        this.height = geometry.getAddressHeight();
        this.senderFirstName = address.getFirstName();
        this.senderMiddleName = address.getMiddleName();
        this.senderLastName = address.getLastName();
        this.senderCompany = address.getCompany();
        this.senderStreet = address.getStreet();
        this.senderHouseNumber = address.getHouseNumber();
        this.senderPostalCode = address.getPostalCode();
        this.senderCity = address.getCity();
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
                senderStreet + " " + senderHouseNumber + "\\\\" +
                senderPostalCode + " " + senderCity;
    }

    private String assembleName() {
        return senderLastName.isEmpty() ? senderCompany : senderFirstName + " " + senderLastName;
    }

    @Override
    public String toString() {
        return "SenderField{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", senderFirstName='" + senderFirstName + '\'' +
                ", senderMiddleName='" + senderMiddleName + '\'' +
                ", senderLastName='" + senderLastName + '\'' +
                ", senderCompany='" + senderCompany + '\'' +
                ", senderStreet='" + senderStreet + '\'' +
                ", senderHouseNumber='" + senderHouseNumber + '\'' +
                ", senderPostalCode='" + senderPostalCode + '\'' +
                ", senderCity='" + senderCity + '\'' +
                '}';
    }
}
