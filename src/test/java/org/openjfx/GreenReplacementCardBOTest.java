package org.openjfx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreenReplacementCardBOTest {
    private org.openjfx.GreenCardReplacementBO greenReplacementCardObj;
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String countryOfBirth;
    private HomeAddress homeAddress;
    private String reasonForReplacement;

    @BeforeEach
    void setUp() {
        this.id = 1234;
        this.firstName = "Leonardo";
        this.middleName = "Wilhelm";
        this.lastName = "Dicaprio";
        this.email = "leonardo_dicaprio@gmail.com";
        this.dateOfBirth = "2000-6-20";
        this.countryOfBirth = "United States of America";
        this.homeAddress = new HomeAddress("123 Any Street", null, "Bakersfield", "CA", "93399");
        this.reasonForReplacement = "Card was lost.";

        greenReplacementCardObj = new GreenCardReplacementBO(id, firstName, middleName, lastName, email, dateOfBirth, countryOfBirth, homeAddress, reasonForReplacement);
    }

    @Test
    public void getIdTest() {
        assertEquals(this.id, greenReplacementCardObj.getId());
    }

    @Test
    public void getFirstNameTest() {
        assertEquals(this.firstName, greenReplacementCardObj.getFirstName());
    }

    @Test
    public void getMiddleNameTest() {
        assertEquals(this.middleName, greenReplacementCardObj.getMiddleName());
    }

    @Test
    public void getLastNameTest() {
        assertEquals(this.lastName, greenReplacementCardObj.getLastName());
    }
    @Test
    public void getEmail(){
        assertEquals(this.email, greenReplacementCardObj.getEmail());
    }

    @Test
    public void getDateOfBirthTest() {
        assertEquals(this.dateOfBirth, greenReplacementCardObj.getDateOfBirth());
    }

    @Test
    public void getCountryOfBirthTest() {
        assertEquals(this.countryOfBirth, greenReplacementCardObj.getCountryOfBirth());
    }

    @Test
    public void getHomeAddressTest() {
        assertEquals(this.homeAddress, greenReplacementCardObj.getHomeAddress());
    }

    @Test
    public void getReasonForReplacementTest() {
        assertEquals(this.reasonForReplacement, greenReplacementCardObj.getReasonForReplacement());
    }

    @Test
    public void setIdTest() {
        int newId = 1234;
        greenReplacementCardObj.setId(newId);
        assertEquals(newId, greenReplacementCardObj.getId());
    }
    @Test
    public void setFirstNameTest(){
        String newFirstName = "Leo";
        greenReplacementCardObj.setFirstName(newFirstName);
        assertEquals(newFirstName, greenReplacementCardObj.getFirstName());
    }
    @Test
    public void setMiddleNameTest(){
        String newMiddleName = "Will";
        greenReplacementCardObj.setMiddleName(newMiddleName);
        assertEquals(newMiddleName, greenReplacementCardObj.getMiddleName());
    }
    @Test
    public void setLastNameTest(){
        String newLastName = "Dicap";
        greenReplacementCardObj.setLastName(newLastName);
        assertEquals(newLastName, greenReplacementCardObj.getLastName());
    }
    @Test
    public void setEmailTest(){
        String newEmail = "leoDicap10@gmail.com";
        greenReplacementCardObj.setEmail(newEmail);
        assertEquals(newEmail, greenReplacementCardObj.getEmail());
    }
    @Test
    public void setDateOfBirthTest(){
        String newDateOfBirth = "2000-1-1";
        greenReplacementCardObj.setDateOfBirth(newDateOfBirth);
        assertEquals(newDateOfBirth, greenReplacementCardObj.getDateOfBirth());
    }
    @Test
    public void setCountryOfBirthTest(){
        String newCountryOfBirth = "England";
        greenReplacementCardObj.setCountryOfBirth(newCountryOfBirth);
        assertEquals(newCountryOfBirth, greenReplacementCardObj.getCountryOfBirth());
    }
    @Test
    public void setHomeAddressTest(){
        HomeAddress homeAddress = new HomeAddress("123 Apple Street","301B", "Fairfax", "VA", "12312");
        greenReplacementCardObj.setHomeAddress(homeAddress);
        assertEquals(homeAddress, greenReplacementCardObj.getHomeAddress());

    }
    @Test
    public void setReasonForReplacementTest(){
        String reasonForReplacement = "Card was stolen.";
        greenReplacementCardObj.setReasonForReplacement(reasonForReplacement);
        assertEquals(reasonForReplacement, greenReplacementCardObj.getReasonForReplacement());
    }
    @Test
    public void dateOfBirthIsNullTest(){
        // Test with null date of birth
        greenReplacementCardObj.setDateOfBirth(null);
        assertNull(greenReplacementCardObj.getDateOfBirth());
    }
    @Test
    public void dateOfBirthIsEmpty(){
        // Test with empty date of birth
        greenReplacementCardObj.setDateOfBirth("");
        assertEquals("", greenReplacementCardObj.getDateOfBirth());
    }
    @Test
    public void emailIsNullTest(){
        // Test with null email
        greenReplacementCardObj.setEmail(null);
        assertNull(greenReplacementCardObj.getEmail());
    }
    @Test
    public void emailIsEmptyTest(){
        // Test with empty email
        greenReplacementCardObj.setEmail("");
        assertEquals("", greenReplacementCardObj.getEmail());
    }
    @Test
    public void homeAddressIsNullTest(){
        // Test with null home address
        greenReplacementCardObj.setHomeAddress(null);
        assertNull(greenReplacementCardObj.getHomeAddress());

        // Test with home address having null or empty values
        HomeAddress invalidHomeAddress = new HomeAddress("", null, null, null, "");
        greenReplacementCardObj.setHomeAddress(invalidHomeAddress);
        assertEquals(invalidHomeAddress, greenReplacementCardObj.getHomeAddress());
    }
    @Test
    public void reasonForReplacementIsNullTest(){
        // Test with null reason for replacement
        greenReplacementCardObj.setReasonForReplacement(null);
        assertNull(greenReplacementCardObj.getReasonForReplacement());
    }
    @Test
    public void reasonForReplacementIsEmptyTest(){
        // Test with empty reason for replacement
        greenReplacementCardObj.setReasonForReplacement("");
        assertEquals("", greenReplacementCardObj.getReasonForReplacement());
    }
}