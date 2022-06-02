package de.flozo.data;

public enum AddressProperty implements Property {

    NAME_FIRST("name.first", "FIRST_NAME"),
    NAME_MIDDLE("name.middle", "MIDDLE_NAME"),
    NAME_LAST("name.last", "LAST_NAME"),
    NAME_TITLE("name.title", "TITLE"),
    NAME_COMPANY("name.company", "COMPANY"),
    ADDRESS_STREET("address.street", "STREET"),
    ADDRESS_HOUSE_NUMBER("address.house_number", "HOUSE_NUMBER"),
    ADDRESS_POSTAL_CODE("address.postal_code", "POSTAL_CODE"),
    ADDRESS_CITY("address.city", "CITY"),
    ADDRESS_COUNTRY("address.country", "COUNTRY"),
    COMMUNICATION_PHONE("communication.phone", "PHONE_NUMBER"),
    COMMUNICATION_MOBILE("communication.mobile", "MOBILE_NUMBER"),
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


}
