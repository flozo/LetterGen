package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.*;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.tikz.*;

public class BackaddressField {

    // appearance
    private final double x;
    private final double y;
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
    public BackaddressField(LetterGeometry geometry, LetterColor color, Address address) {
        this.x = geometry.getBackaddressX();
        this.y = geometry.getBackaddressY();
        this.width = geometry.getBackaddressWidth();
        this.height = geometry.getBackaddressHeight();
        this.backgroundColor = color.getDraftModeHighlightingBackgroundColor();
        this.borderColor = color.getDraftModeHighlightingBorderColor();
        this.textColor = color.getAddressTextColor();
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
                .name("backaddress")
                .position(Point.fromNumbers(x, y))
                .anchor(Anchor.SOUTH_WEST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .textWidth(width)
                .fontSize(fontSize)
                .alignment(Alignment.CENTER)
                .build().getInline();
    }

    public String getSeparationLine() {
        return new Line.Builder(separationLineX, y, width, 0, CoordinateMode.RELATIVE)
                .lineWidth(separationLineWidth)
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
        return new Command2.Builder(CommandName.HSPACE.getString())
                .body(Length.createFromNumberAndUnit(width, LengthUnit.POINT).getFormatted())
                .build()
                .getInline();
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

    public double getSeparationLineWidth() {
        return separationLineWidth;
    }

    public String getSeparationCharacter() {
        return separationCharacter;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "BackaddressField{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", separationLineX=" + separationLineX +
                ", separationLineWidth=" + separationLineWidth +
                ", separationCharacter='" + separationCharacter + '\'' +
                ", separationCharacterSpacing=" + separationCharacterSpacing +
                ", fontSize=" + fontSize +
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
