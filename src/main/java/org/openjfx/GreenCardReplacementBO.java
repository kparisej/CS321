package org.openjfx;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GreenCardReplacementBO {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String countryOfBirth;
    private HomeAddress homeAddress;
    private String reasonForReplacement;

    public GreenCardReplacementBO() {

    }

    // Constructor
    public GreenCardReplacementBO(@JsonProperty("id") int id,
                                  @JsonProperty("firstName") String firstName,
                                  @JsonProperty("middleName") String middleName,
                                  @JsonProperty("lastName") String lastName,
                                  @JsonProperty("email") String email,
                                  @JsonProperty("dateOfBirth") String dateOfBirth,
                                  @JsonProperty("countryOfBirth") String countryOfBirth,
                                  @JsonProperty("homeAddress") HomeAddress homeAddress,
                                  @JsonProperty("reasonForReplacement") String reasonForReplacement) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.countryOfBirth = countryOfBirth;
        this.homeAddress = homeAddress;
        this.reasonForReplacement = reasonForReplacement;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public int getId() {
        return this.id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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