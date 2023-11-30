package org.openjfx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FormReader {
    static List<GreenCardReplacementBO> forms;
    static ObjectMapper mapper = new ObjectMapper();
    private static File f;

    public static void addForm(int id, String firstName, String middleName, String lastName,
            String email,
            String dateOfBirth,
            String countryOfBirth, HomeAddress homeAddress,
            String reasonForReplacement) {
        // Create a new WorkFlow object with the provided ID and step
        GreenCardReplacementBO newGreenCardReplacementBO = new GreenCardReplacementBO(id, firstName, middleName,
                lastName, email,
                dateOfBirth,
                countryOfBirth, homeAddress,
                reasonForReplacement);

        // Add the new WorkFlow to the existing list
        forms.add(newGreenCardReplacementBO);

        // Serialize the updated list back into JSON
        String updatedJson;
        try {
            updatedJson = mapper.writeValueAsString(forms);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return; // Return without writing to the file if there's an error
        }

        // Write the updated JSON back to the file
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(updatedJson.getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editForm(int id, String firstName, String middleName, String lastName,
            String email,
            String dateOfBirth,
            String countryOfBirth, HomeAddress homeAddress,
            String reasonForReplacement) {
        // Find the WorkFlow with id 2 and update its step to "Done"
        for (GreenCardReplacementBO wf : forms) {
            if (wf.getId() == id) {
                wf.setId(id);
                wf.setFirstName(firstName);
                wf.setMiddleName(middleName);
                wf.setLastName(lastName);
                wf.setEmail(email);
                wf.setDateOfBirth(dateOfBirth);
                wf.setCountryOfBirth(countryOfBirth);
                wf.setHomeAddress(homeAddress);
                wf.setReasonForReplacement(reasonForReplacement);
                break; // Stop the loop once the desired object is found and updated
            }
        }

        // Serialize the updated list back into JSON
        String updatedJson;
        try {
            updatedJson = mapper.writeValueAsString(forms);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Write the updated JSON back to the file
        try (FileOutputStream fos = new FileOutputStream(
                f)) {
            updatedJson = mapper.writeValueAsString(forms);
            fos.write(updatedJson.getBytes());
            fos.flush();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GreenCardReplacementBO getForm(int id) {
        for (GreenCardReplacementBO wf : forms) {
            if (wf.getId() == (id)) {
                return wf;
            }
        }
        return null;
    }

    public FormReader(File file) {
        try {
            this.f = file;
            // Assuming 'workflows.json' is in the root of your project folder
            forms = mapper.readValue(
                    f,
                    new TypeReference<List<GreenCardReplacementBO>>() {
                    });

            // Now 'workflows' contains a list of WorkFlow objects created from the JSON
            // file
            // You can process the list as needed, for example print it out
            for (GreenCardReplacementBO wf : forms) {
                /*System.out.println(wf.getId() + "\n" +
                        wf.getFirstName() + "\n" +
                        wf.getMiddleName() + "\n" +
                        wf.getLastName() + "\n" +
                        wf.getEmail() + "\n" +
                        wf.getDateOfBirth() + "\n" +
                        wf.getCountryOfBirth() + "\n" +
                        wf.getHomeAddress().getStreetName() + "\n" +
                        wf.getHomeAddress().getApartmentNumber() + "\n" +
                        wf.getHomeAddress().getCity() + "\n" +
                        wf.getHomeAddress().getState() + "\n" +
                        wf.getHomeAddress().getZipCode() + "\n" +
                        wf.getReasonForReplacement() + "\n");
                */
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
