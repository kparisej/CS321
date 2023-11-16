package org.openjfx;

import java.time.LocalDate;

public class GreenCardReplacementBO {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String countryOfBirth;
    private HomeAddress homeAddress;
    private String reasonForReplacement;
    int change;

    public GreenCardReplacementBO(int id, String firstName, String middleName, String lastName, LocalDate dateOfBirth,
            String countryOfBirth, HomeAddress homeAddress, String reasonForReplacement) {

    }

    /**
     * Getter & Setter methods
     */
    public void setId(int id) {
    }

    public int getId() {
        return -1;
    }

    public void setFirstName(String firstName) {
    }

    public String getFirstName() {
        return null;
    }

    public void setMiddleName(String middleName) {
    }

    public String getMiddleName() {
        return null;
    }

    public void setLastName(String lastName) {

    }

    public String getLastName() {
        return null;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {

    }

    public LocalDate getDateOfBirth() {
        return null;
    }

    public void setCountryOfBirth(String countryOfBirth) {
    }

    public String getCountryOfBirth() {
        return null;
    }

    public void setHomeAddress(HomeAddress homeAddress) {

    }

    public String getHomeAddress() {
        return null;
    }

    public void setReasonForReplacement(String reasonForReplacement) {

    }

    public String getReasonForReplacement() {
        return null;
    }

    /**
     * Validates country of birth exists
     * 
     * @param countryOfBirth
     * @return bool
     */
    public Boolean isValidCountry(String countryOfBirth) {
        return false;
    }

    /**
     * Retrieves form with matching form id.
     * 
     * @param formId
     * @return GreenReplacementCardBO
     */
    public GreenCardReplacementBO getForm(int formId) {
        return null;
    }
}