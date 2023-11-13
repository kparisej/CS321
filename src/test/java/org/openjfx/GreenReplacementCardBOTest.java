package org.openjfx;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class GreenReplacementCardBOTest {
    private org.openjfx.GreenCardReplacementBO greenReplacementCardObj;
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String countryOfBirth;
    private org.openjfx.HomeAddress homeAddress;
    private String reasonForReplacement;

    @BeforeEach
    void setUp() {
        this.id = 1234;
        this.firstName = "Leonardo";
        this.middleName = "Wilhelm";
        this.lastName = "Dicaprio";
        this.dateOfBirth = new GregorianCalendar(1974, Calendar.NOVEMBER, 11).getTime();
        this.countryOfBirth = "United States of America";
        //this.homeAddress = new org.openjfx.HomeAddress("123 Any Street", "Bakersfield", "CA", "93399");
        this.reasonForReplacement = "Card was lost.";

        greenReplacementCardObj = new org.openjfx.GreenCardReplacementBO(id, firstName, middleName, lastName, null, countryOfBirth, homeAddress, reasonForReplacement);
    }

    @AfterEach
    void tearDown() {
        assertNull(this.id);
        assertNull(this.firstName);
        assertNull(this.middleName);
        assertNull(this.lastName);
        assertNull(this.dateOfBirth);
        assertNull(this.countryOfBirth);
        assertNull(this.homeAddress);
        assertNull(this.reasonForReplacement);
        assertNull(greenReplacementCardObj);
    }
    @Test
    public void getFormTest(){ assertTrue(greenReplacementCardObj.getForm(this.id) instanceof org.openjfx.GreenCardReplacementBO); }
    @Test
    public void getId(){ assertEquals(this.id, greenReplacementCardObj.getId()); }
    @Test
    public void getFirstNameTest(){
        assertEquals( this.firstName, greenReplacementCardObj.getFirstName());
    }
    @Test
    public void getMiddleNameTest(){
        assertEquals( this.middleName, greenReplacementCardObj.getMiddleName());
    }
    @Test
    public void getLastNameTest(){
        assertEquals( this.lastName, greenReplacementCardObj.getLastName());
    }
    @Test
    public void getDateOfBirthTest(){
        assertEquals( this.dateOfBirth, greenReplacementCardObj.getDateOfBirth());
    }
    @Test
    public void getCountryOfBirthTest(){ assertEquals( this.countryOfBirth, greenReplacementCardObj.getCountryOfBirth()); }
    @Test
    public void getHomeAddressTest(){
        assertEquals( this.homeAddress, greenReplacementCardObj.getHomeAddress());
    }
    @Test
    public void getReasonForReplacementTest(){ assertEquals( this.reasonForReplacement, greenReplacementCardObj.getReasonForReplacement()); }
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
//    @Test
//    public void setDateOfBirthTest(){
//        LocalDate newDateOfBirth =  new LocalDate(1975, Calendar.NOVEMBER, 11).getTime();
//        greenReplacementCardObj.setDateOfBirth(newDateOfBirth);
//        assertEquals(newDateOfBirth, greenReplacementCardObj.getDateOfBirth());
//    }
    @Test
    public void setCountryOfBirthTest(){
        String newCountryOfBirth = "England";
        greenReplacementCardObj.setCountryOfBirth(newCountryOfBirth);
        assertEquals(newCountryOfBirth, greenReplacementCardObj.getCountryOfBirth());
    }
//    @Test
//    public void setHomeAddressTest(){
//        org.openjfx.HomeAddress homeAddress = new org.openjfx.HomeAddress("123 Apple Street", "Fairfax", "VA", "12312");
//        greenReplacementCardObj.setHomeAddress(homeAddress);
//        assertEquals(homeAddress, greenReplacementCardObj.getHomeAddress());
//
//    }
    @Test
    public void setReasonForReplacementTest(){
        String reasonForReplacement = "Card was stolen.";
        greenReplacementCardObj.setReasonForReplacement(reasonForReplacement);
        assertEquals(reasonForReplacement, greenReplacementCardObj.getReasonForReplacement());
    }
    @Test
    public void isValidCountryTest(){
        assertTrue(greenReplacementCardObj.isValidCountry(this.countryOfBirth));
    }
}