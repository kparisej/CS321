package org.openjfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

public class formHolder {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Assuming 'forms.json' is in the current working directory of the application
            InputStream inputStream = new FileInputStream(new File("forms.json"));
            // Use the inputStream as needed...

            // Close the inputStream when you're done with it
            inputStream.close();
        } catch (Exception e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }
}