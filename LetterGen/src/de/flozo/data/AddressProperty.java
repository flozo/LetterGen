package de.flozo.data;

public enum AddressProperty implements Property {

    NAME_FIRST("name.first", "FIRST\\_NAME"),
    NAME_MIDDLE("name.middle", "MIDDLE\\_NAME"),
    NAME_LAST("name.last", "LAST\\_NAME"),
    NAME_TITLE("name.title", "TITLE"),
    NAME_COMPANY("name.company", "COMPANY"),

    ADDRESS_STREET("address.street", "STREET"),
    ADDRESS_HOUSE_NUMBER("address.house_number", "123a"),
    ADDRESS_POSTAL_CODE("address.postal_code", "12345"),
    ADDRESS_CITY("address.city", "CITY"),
    ADDRESS_COUNTRY("address.country", "COUNTRY"),

    COMMUNICATION_PHONE("communication.phone", "PHONE\\_NUMBER"),
    COMMUNICATION_MOBILE("communication.mobile", "MOBILE\\_NUMBER"),
    COMMUNICATION_EMAIL("communication.email", "EMAIL@ADDRESS.TLD"),
    COMMUNICATION_WEBPAGE("communication.webpage", "WWW.WEBPAGE.TLD");

    private final String property;
    private final String stringValue;

    AddressProperty(String property, String stringValue) {
        this.property = property;
        this.stringValue = stringValue;
    }

    @Override
    public String getPropertyKey() {
        return property;
    }

    public String getStringValue() {
        return stringValue;
    }



    @Override
    public String getEntry() {
        return property + " = " + stringValue;
    }

    @Override
    public String getGenericStringValue() {
        return stringValue;
    }

    @Override
    public String toString() {
        return "AddressProperty{" +
                "property='" + property + '\'' +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}
