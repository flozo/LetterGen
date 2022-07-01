package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeneral;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.*;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;
import de.flozo.latex.tikz.*;

public class BackaddressField {

    public static final String FIELD_NAME = "backaddress";

    // appearance
    private final Point position;
    private final double width;
    private final double height;

    private final double separationLineX;
    private final double separationLineWidth;
    private final String separationCharacter;
    private final double separationCharacterSpacing;
    private final FontSize fontSize;
    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;


    // content
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;


    // Constructor with dependency injection
    public BackaddressField(LetterGeneral general, LetterGeometry geometry, LetterColor color, Address address) {
        this.position = Point.fromNumbers(geometry.getBackaddressX(), geometry.getBackaddressY());
        this.width = geometry.getBackaddressWidth();
        this.height = geometry.getBackaddressHeight();
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getBackaddressTextColor();
        this.firstName = address.getFirstName();
        this.middleName = address.getMiddleName();
        this.lastName = address.getLastName();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.postalCode = address.getPostalCode();
        this.city = address.getCity();
        this.separationLineX = geometry.getBackaddressSeplineX();
        this.separationLineWidth = geometry.getBackaddressSeplineLineWidth();
        this.separationCharacter = geometry.getBackaddressSepChar();
        this.separationCharacterSpacing = geometry.getBackaddressSepCharSpacing();
        this.fontSize = FontSize.getByValue(geometry.getBackaddressFontSize());
    }

    public String getBackaddressText() {
        return new Node.Builder(assembleText())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.SOUTH_WEST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .textWidth(Length.inCentimeter(width))
                .fontSize(fontSize)
                .alignment(Alignment.CENTER)
                .build().getInline();
    }

    public String getSeparationLine() {
        return new Line.Builder(separationLineX, position.getYValue(), width, 0, CoordinateMode.RELATIVE)
                .lineWidth(Length.inDefaultUnit(separationLineWidth))
                .build().getInline();
    }

    private String assembleText() {
        return firstName +
                " " + lastName +
                assembleSeparator() +
                street + " " + houseNumber +
                assembleSeparator() +
                postalCode + " " + city;
    }

    private String assembleSeparator() {
        return hSpace(separationCharacterSpacing) + separationCharacter + hSpace(separationCharacterSpacing);
    }

    private String hSpace(double width) {
        return new GenericCommand.Builder(CommandName.HSPACE.getString())
                .body(Length.inPoint(width).getFormatted())
                .build()
                .getInline();
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
        return "BackaddressField{" +
                "position=" + position +
                ", width=" + width +
                ", height=" + height +
                ", separationLineX=" + separationLineX +
                ", separationLineWidth=" + separationLineWidth +
                ", separationCharacter='" + separationCharacter + '\'' +
                ", separationCharacterSpacing=" + separationCharacterSpacing +
                ", fontSize=" + fontSize +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
