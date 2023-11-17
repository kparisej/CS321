package org.openjfx;

public class HomeAddress {
    private String apartmentNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipCode;

    // Constructor
    public HomeAddress(String streetName, String apartmentNumber, String city, String state, String zipCode) {
        this.streetName = streetName;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Getters and Setters
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
