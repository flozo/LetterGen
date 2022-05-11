package de.flozo.data;

public class Address {

    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;

    public Address(String street, String houseNumber, String postalCode, String city, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
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
}
