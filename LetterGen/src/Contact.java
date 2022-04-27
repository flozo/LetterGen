public class Contact extends Address {


    private String firstName;
    private String lastName;
    private String companyName;

    public Contact(String street, String houseNumber, String postalCode, String city, String country, String firstName, String lastName, String companyName) {
        super(street, houseNumber, postalCode, city, country);
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
