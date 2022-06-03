package de.flozo.latex.letter;

import de.flozo.data.Address;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.FontSize;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;

public class BackaddressLine {

    // appearance
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    private final double separationLineWidth;
    private final String separationCharacter;
    private final double separationCharacterSpacing;
    private final FontSize fontSize;

    // content
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;

    public BackaddressLine(LetterGeometry geometry, Address address) {
        this.x = geometry.getBackaddressX();
        this.y = geometry.getBackaddressY();
        this.width = geometry.getBackaddressWidth();
        this.height = geometry.getBackaddressHeight();
        this.firstName = address.getFirstName();
        this.middleName = address.getMiddleName();
        this.lastName = address.getLastName();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.postalCode = address.getPostalCode();
        this.city = address.getCity();
        this.separationLineWidth = geometry.getBackaddressSeplineLineWidth();
        this.separationCharacter = geometry.getBackaddressSepChar();
        this.separationCharacterSpacing = geometry.getBackaddressSepCharSpacing();
        this.fontSize = FontSize.valueOf(geometry.getBackaddressFontSize());
    }

    public String getBackaddressLine() {
        Node backaddressNode = new Node.NodeBuilder(x, y, assembleText())
                .name("backaddress")
                .anchor(Anchor.SOUTH_WEST)
                .textWidth(width)
                .fontSize(fontSize)
                .build();
        return backaddressNode.getStatement();
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
        return separationCharacterSpacing + separationCharacter + separationCharacterSpacing;
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
}
