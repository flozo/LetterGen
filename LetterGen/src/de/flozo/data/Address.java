package de.flozo.data;

import java.util.Map;

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


    public Address(PropertyMap propertyMap) {
        Map<String, String> map = propertyMap.getProperties();
        this.firstName = map.get(AddressProperty.NAME_FIRST.getPropertyKey());
        this.middleName = map.get(AddressProperty.NAME_MIDDLE.getPropertyKey());
        this.lastName = map.get(AddressProperty.NAME_LAST.getPropertyKey());
        this.title = map.get(AddressProperty.NAME_TITLE.getPropertyKey());
        this.company = map.get(AddressProperty.NAME_COMPANY.getPropertyKey());
        this.street = map.get(AddressProperty.ADDRESS_STREET.getPropertyKey());
        this.houseNumber = map.get(AddressProperty.ADDRESS_HOUSE_NUMBER.getPropertyKey());
        this.postalCode = map.get(AddressProperty.ADDRESS_POSTAL_CODE.getPropertyKey());
        this.city = map.get(AddressProperty.ADDRESS_CITY.getPropertyKey());
        this.country = map.get(AddressProperty.ADDRESS_COUNTRY.getPropertyKey());
        this.phoneNumber = map.get(AddressProperty.COMMUNICATION_PHONE.getPropertyKey());
        this.mobileNumber = map.get(AddressProperty.COMMUNICATION_MOBILE.getPropertyKey());
        this.emailAddress = map.get(AddressProperty.COMMUNICATION_EMAIL.getPropertyKey());
        this.webpage = map.get(AddressProperty.COMMUNICATION_WEBPAGE.getPropertyKey());
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

    @Override
    public String toString() {
        return "Address{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", webpage='" + webpage + '\'' +
                '}';
    }
}
