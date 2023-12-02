package org.openjfx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WorkFlowReader {
    static List<WorkFlow> workflows;
    static ObjectMapper mapper = new ObjectMapper();
    private static File f;

    public static void addPost(int id, String step) {
        // Create a new WorkFlow object with the provided ID and step
        WorkFlow newWorkFlow = new WorkFlow(id, step);

        // Add the new WorkFlow to the existing list
        workflows.add(newWorkFlow);

        // Serialize the updated list back into JSON
        String updatedJson;
        try {
            updatedJson = mapper.writeValueAsString(workflows);
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

    public static void editStep(int id, String step) {
        // Find the WorkFlow with id 2 and update its step to "Done"
        for (WorkFlow wf : workflows) {
            if (wf.getId() == id) {
                wf.setStep(step);
                break; // Stop the loop once the desired object is found and updated
            }
        }

        // Serialize the updated list back into JSON
        String updatedJson;
        try {
            updatedJson = mapper.writeValueAsString(workflows);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Write the updated JSON back to the file
        try (FileOutputStream fos = new FileOutputStream(
                f)) {
            updatedJson = mapper.writeValueAsString(workflows);
            fos.write(updatedJson.getBytes());
            fos.flush();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId(String Step) {
        for (WorkFlow wf : workflows) {
            if (wf.getStep().equals(Step)) {
                return wf.getId();
            }
        }
        return -1;
    }

    public WorkFlowReader(File file) {
        try {
            f = file;
            // Assuming 'workflows.json' is in the root of your project folder
            workflows = mapper.readValue(
                    f,
                    new TypeReference<>() {
                    });

            // Now 'workflows' contains a list of WorkFlow objects created from the JSON
            // file
            // You can process the list as needed, for example print it out
            for (WorkFlow wf : workflows) {
                System.out.println(wf.getId() + ": " + wf.getStep());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}