package de.flozo.data;

public class Address {

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String title;
    private final String company;

    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final String country;

    private final String phoneNumber;
    private final String mobileNumber;
    private final String emailAddress;
    private final String webpage;


    public Address() {
        this.firstName = AddressProperty.NAME_FIRST.getStringValue();
        this.middleName = AddressProperty.NAME_MIDDLE.getStringValue();
        this.lastName = AddressProperty.NAME_LAST.getStringValue();
        this.title = AddressProperty.NAME_TITLE.getStringValue();
        this.company = AddressProperty.NAME_COMPANY.getStringValue();
        this.street = AddressProperty.ADDRESS_STREET.getStringValue();
        this.houseNumber = AddressProperty.ADDRESS_HOUSE_NUMBER.getStringValue();
        this.postalCode = AddressProperty.ADDRESS_POSTAL_CODE.getStringValue();
        this.city = AddressProperty.ADDRESS_CITY.getStringValue();
        this.country = AddressProperty.ADDRESS_COUNTRY.getStringValue();
        this.phoneNumber = AddressProperty.COMMUNICATION_PHONE.getStringValue();
        this.mobileNumber = AddressProperty.COMMUNICATION_MOBILE.getStringValue();
        this.emailAddress = AddressProperty.COMMUNICATION_EMAIL.getStringValue();
        this.webpage = AddressProperty.COMMUNICATION_WEBPAGE.getStringValue();
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

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
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

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getWebpage() {
        return webpage;
    }
}
