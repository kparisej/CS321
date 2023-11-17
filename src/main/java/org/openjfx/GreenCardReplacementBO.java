package org.openjfx;

import java.time.LocalDate;

public class GreenCardReplacementBO {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String countryOfBirth;
    private HomeAddress homeAddress;
    private String reasonForReplacement;

    // Constructor
    public GreenCardReplacementBO(int id, String firstName, String middleName, String lastName, LocalDate dateOfBirth,
            String countryOfBirth, HomeAddress homeAddress, String reasonForReplacement) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.homeAddress = homeAddress;
        this.reasonForReplacement = reasonForReplacement;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getReasonForReplacement() {
        return reasonForReplacement;
    }

    public void setReasonForReplacement(String reasonForReplacement) {
        this.reasonForReplacement = reasonForReplacement;
    }
}