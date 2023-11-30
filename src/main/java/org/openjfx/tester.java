package org.openjfx;

import java.io.File;

public class tester {
    public static void main(String[] args) {
        File file2 = new File("C:/Users/Dell/Documents/GitHub/CS321/src/main/java/org/openjfx/info.json");
        File file1 = new File("C:/Users/Dell/Documents/GitHub/CS321/src/main/java/org/openjfx/forms.json");
        FormReader f = new FormReader(file1);
        WorkFlowReader w = new WorkFlowReader(file2);
        int id = w.getId("Review");
        GreenCardReplacementBO x = new GreenCardReplacementBO();
        x = f.getForm(id);
        // GreenCardReplacementBO x = new GreenCardReplacementBO(f.getForm(id).getId(),
        // f.getForm(id).getFirstName(),
        // f.getForm(id).getMiddleName(), f.getForm(id).getLastName(),
        // f.getForm(id).getEmail(), f.getForm(id).getDateOfBirth(),
        // f.getForm(id).getCountryOfBirth(), f.getForm(id).getHomeAddress(),
        // f.getForm(id).getReasonForReplacement());
        System.out.println(x.getFirstName());
        HomeAddress l = new HomeAddress("abc Street", "1212", "Sterling", "VA", "20164");
        f.addForm(w, "Naim", "M", "H", "Sesamestreet@gmail.com", "08/20/23", "Murica", l,
                "U.S. Resident card will expire soon");

    }
}