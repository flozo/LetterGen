package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.LengthUnit;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.tikz.*;

public class SenderField {

    public static final String FIELD_NAME = "sender_address";


    // appearance
    private final Point position;
    private final double width;
    private final double height;
    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;
    private final Color iconColor;


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
    public SenderField(LetterGeometry geometry, LetterColor color, Address address) {
        this.position = Point.fromNumbers(geometry.getSenderX(), geometry.getSenderY());
        this.width = geometry.getSenderWidth();
        this.height = geometry.getSenderHeight();
        this.backgroundColor = color.getDraftModeHighlightingBackgroundColor();
        this.borderColor = color.getDraftModeHighlightingBorderColor();
        this.textColor = color.getSenderTextColor();
        this.iconColor = color.getSenderIconColor();
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
        return new Node.Builder(assembleText())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .minimumWidth(Length.createFromNumberAndUnit(width, LengthUnit.CENTIMETER))
                .minimumHeight(Length.createFromNumberAndUnit(2.73, LengthUnit.CENTIMETER))
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .textWidth(Length.createFromNumberAndUnit(width, LengthUnit.CENTIMETER))
                .alignment(Alignment.LEFT)
                .build().getInline();
    }

    public MatrixOfNodes getMatrix() {
        return new MatrixOfNodes.Builder(FIELD_NAME, position, Anchor.NORTH_EAST)
                .backgroundColor(backgroundColor)
                .borderColor(borderColor)
                .textColor(textColor)
                .addRow(senderStreet + " " + senderHouseNumber + "\\\\" + senderPostalCode + " " + senderCity, ContactIcon.MAP_MARKER_ALT.getIconDefault())
                .addRow(phoneNumber, ContactIcon.PHONE_ALT.getIconDefault())
                .addRow(emailAddress, ContactIcon.ENVELOPE.getIconDefault())
                .addColumnStyle(assembleNodeStyle1())
                .addColumnStyle(assembleNodeStyle2())
                .build();
    }

    // preliminary
    private NodeStyle assembleNodeStyle1() {
        return new NodeStyle.Builder()
                .addCustomOption(PathOperation.RECTANGLE.getString())
//                .addNodeOption(NodeOption.DRAW, textColor.getString())
                .addNodeOption(NodeOption.FILL, backgroundColor.getString())
                .addNodeOption(NodeOption.TEXT, textColor.getString())
                .addNodeOption(NodeOption.ALIGN, Alignment.RIGHT.getString())
                .addNodeOption(NodeOption.INNER_X_SEP, Length.createFromNumberAndUnit(8, LengthUnit.POINT).getFormatted())
                .addNodeOption(NodeOption.INNER_Y_SEP, Length.createFromNumberAndUnit(6, LengthUnit.POINT).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_WIDTH, Length.createFromNumberAndUnit(0.6, LengthUnit.CENTIMETER).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_HEIGHT, Length.createFromNumberAndUnit(0.5, LengthUnit.CENTIMETER).getFormatted())
                .addNodeOption(NodeOption.TEXT_WIDTH, Length.createFromNumberAndUnit(7.8, LengthUnit.CENTIMETER).getFormatted())
                .addNodeOption(NodeOption.TEXT_HEIGHT, Length.createFromNumberAndUnit(0.25, LengthUnit.CENTIMETER).getFormatted())
                .build();
    }

    // preliminary
    private NodeStyle assembleNodeStyle2() {
        return new NodeStyle.Builder()
                .addCustomOption(PathOperation.RECTANGLE.getString())
//                .addNodeOption(NodeOption.DRAW, textColor.getString())
                .addNodeOption(NodeOption.FILL, backgroundColor.getString())
                .addNodeOption(NodeOption.TEXT, iconColor.getString())
                .addNodeOption(NodeOption.ALIGN, Alignment.CENTER.getString())
                .addNodeOption(NodeOption.INNER_X_SEP, Length.createFromNumberAndUnit(0, LengthUnit.POINT).getFormatted())
                .addNodeOption(NodeOption.INNER_Y_SEP, Length.createFromNumberAndUnit(6, LengthUnit.POINT).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_WIDTH, Length.createFromNumberAndUnit(0.4, LengthUnit.CENTIMETER).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_HEIGHT, Length.createFromNumberAndUnit(0.5, LengthUnit.CENTIMETER).getFormatted())
                .addNodeOption(NodeOption.TEXT_WIDTH, Length.createFromNumberAndUnit(0.4, LengthUnit.CENTIMETER).getFormatted())
                .addNodeOption(NodeOption.TEXT_HEIGHT, Length.createFromNumberAndUnit(0.25, LengthUnit.CENTIMETER).getFormatted())
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
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", iconColor=" + iconColor +
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
