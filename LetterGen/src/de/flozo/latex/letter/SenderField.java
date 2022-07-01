package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeneral;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.Bracket;
import de.flozo.latex.core.GenericCommand;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;
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
    private final boolean hyperlinksOn;


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
    private final String emailSubject;
    private final String webpage;


    // Constructor with dependency injection
    public SenderField(LetterGeneral general, LetterGeometry geometry, LetterColor color, Address address, String emailSubject) {
        this.position = Point.fromNumbers(geometry.getSenderX(), geometry.getSenderY());
        this.width = geometry.getSenderWidth();
        this.height = geometry.getSenderHeight();
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getSenderTextColor();
        this.iconColor = color.getSenderIconColor();
        this.hyperlinksOn = general.isHyperlinksOn();
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
        this.emailSubject = emailSubject;
        this.webpage = address.getWebpage();
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
                .build().getInline();
    }

    public MatrixOfNodes getMatrix() {
        return new MatrixOfNodes.Builder(FIELD_NAME, position, Anchor.NORTH_EAST)
                .backgroundColor(backgroundColor)
                .borderColor(borderColor)
                .textColor(textColor)
                .addRow(senderStreet + " " + senderHouseNumber + "\\\\" + senderPostalCode + " " + senderCity, ContactIcon.MAP_MARKER_ALT.getIconDefault())
                .addRow(phoneNumber, ContactIcon.PHONE_ALT.getIconDefault())
                .addRow(formattedEmailAddress(), ContactIcon.ENVELOPE.getIconDefault())
                .addColumnStyle(assembleNodeStyle1())
                .addColumnStyle(assembleNodeStyle2())
                .build();
    }

    private String formattedEmailAddress() {
        if (hyperlinksOn) {
            GenericCommand href = new GenericCommand.Builder("href")
                    .optionList("mailto:" + emailAddress + "?subject=" + emailSubject)
                    .optionBrackets(Bracket.CURLY_BRACES)
                    .body(emailAddress)
                    .bodyBrackets(Bracket.CURLY_BRACES)
                    .build();
            return href.getInline();
        }
        return emailAddress;
    }

    // preliminary
    private NodeStyle assembleNodeStyle1() {
        return new NodeStyle.Builder()
                .addCustomOption(PathOperation.RECTANGLE.getString())
//                .addNodeOption(NodeOption.DRAW, textColor.getString())
                .addNodeOption(NodeOption.FILL, backgroundColor.getString())
                .addNodeOption(NodeOption.TEXT, textColor.getString())
                .addNodeOption(NodeOption.ALIGN, Alignment.RIGHT.getString())
                .addNodeOption(NodeOption.INNER_X_SEP, Length.inPoint(8).getFormatted())
                .addNodeOption(NodeOption.INNER_Y_SEP, Length.inPoint(6).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_WIDTH, Length.inCentimeter(0.6).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_HEIGHT, Length.inCentimeter(0.5).getFormatted())
                .addNodeOption(NodeOption.TEXT_WIDTH, Length.inCentimeter(7.8).getFormatted())
                .addNodeOption(NodeOption.TEXT_HEIGHT, Length.inCentimeter(0.25).getFormatted())
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
                .addNodeOption(NodeOption.INNER_X_SEP, Length.inPoint(0).getFormatted())
                .addNodeOption(NodeOption.INNER_Y_SEP, Length.inPoint(6).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_WIDTH, Length.inCentimeter(0.4).getFormatted())
                .addNodeOption(NodeOption.MINIMUM_HEIGHT, Length.inCentimeter(0.5).getFormatted())
                .addNodeOption(NodeOption.TEXT_WIDTH, Length.inCentimeter(0.4).getFormatted())
                .addNodeOption(NodeOption.TEXT_HEIGHT, Length.inCentimeter(0.25).getFormatted())
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
                ", hyperlinksOn=" + hyperlinksOn +
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
                ", emailSubject='" + emailSubject + '\'' +
                ", webpage='" + webpage + '\'' +
                '}';
    }
}
