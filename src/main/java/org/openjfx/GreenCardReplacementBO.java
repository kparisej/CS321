package org.openjfx;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    // Constructor
    public GreenCardReplacementBO(@JsonProperty("id") int id, @JsonProperty("firstName") String firstName,
            @JsonProperty("middleName") String middleName, @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("dateOfBirth") String dateOfBirth,
            @JsonProperty("countryOfBirth") String countryOfBirth, @JsonProperty("homeAddress") HomeAddress homeAddress,
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
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // try {
        // // Parse the date string into a LocalDate object using the formatter
        // this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);

        // // Now 'dateOfBirth' contains the parsed date as a LocalDate object
        // System.out.println("Parsed Date: " + dateOfBirth);
        // } catch (Exception e) {
        // // Handle parsing errors here, if necessary
        // e.printStackTrace();
        // }
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
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // try {
        // // Parse the date string into a LocalDate object using the formatter
        // this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);

        // // Now 'dateOfBirth' contains the parsed date as a LocalDate object
        // System.out.println("Parsed Date: " + dateOfBirth);
        // } catch (Exception e) {
        // // Handle parsing errors here, if necessary
        // e.printStackTrace();
        // }
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