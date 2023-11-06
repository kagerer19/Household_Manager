package org.example.PV;

class Address {
    private String streetName;
    private String city;
    private String postCode;
    private String houseNum;

    //Constructors
    Address(String street, String town, String postalCode, String houseNumber) {
        this.streetName = street;
        this.city = town;
        this.postCode = postalCode;
        this.houseNum = houseNumber;
    }

    //Getters
    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getHouseNum() {
        return houseNum;
    }
}