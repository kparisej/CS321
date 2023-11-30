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
        System.out.println("1    " + x.getCountryOfBirth());
    }
}