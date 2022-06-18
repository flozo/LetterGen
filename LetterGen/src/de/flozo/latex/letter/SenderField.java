package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.tikz.*;

public class SenderField {

    public static final String FIELD_NAME = "sender_address";


    // appearance
    private final Point position;
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
    private final String country;
    private final String phoneNumber;
    private final String mobileNumber;
    private final String emailAddress;
    private final String webpage;


    // Constructor with dependency injection
    public SenderField(LetterGeometry geometry, Address address) {
        this.position = Point.fromNumbers(geometry.getSenderX(), geometry.getSenderY());
        this.width = geometry.getSenderWidth();
        this.height = geometry.getSenderHeight();
        this.senderFirstName = address.getFirstName();
        this.senderMiddleName = address.getMiddleName();
        this.senderLastName = address.getLastName();
        this.senderCompany = address.getCompany();
        this.senderStreet = address.getStreet();
        this.senderHouseNumber = address.getHouseNumber();
        this.senderPostalCode = address.getPostalCode();
        this.senderCity = address.getCity();
        this.country = address.getCountry();
        this.phoneNumber = address.getPhoneNumber();
        this.mobileNumber = address.getMobileNumber();
        this.emailAddress = address.getEmailAddress();
        this.webpage = address.getWebpage();
    }

    public String getAddressField() {
        Node addressNode = new Node.Builder(assembleText())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .minimumWidth(width)
                .minimumHeight(2.73)
                .textWidth(width)
                .alignment(Alignment.LEFT)
                .build();
        return addressNode.getInline();
    }

    public MatrixOfNodes getMatrix() {
        return new MatrixOfNodes.Builder(FIELD_NAME, position, Anchor.NORTH_EAST)
                .addRow(senderStreet + " " + senderHouseNumber + "\\\\" + senderPostalCode + " " + senderCity, ContactIcon.MAP_MARKER_ALT.getIconDefault())
                .addRow(phoneNumber, ContactIcon.PHONE_ALT.getIconDefault())
                .addRow(emailAddress, ContactIcon.ENVELOPE.getIconDefault())
                .build();
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
                "position=" + position +
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
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", webpage='" + webpage + '\'' +
                '}';
    }
}
