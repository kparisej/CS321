package org.openjfx;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkFlow {
    private int id;
    private String step;

    @JsonCreator
    public WorkFlow(@JsonProperty("id") int id, @JsonProperty("Step") String step) {
        this.id = id;
        this.step = step;
    }

    // Getter for 'id' field
    public int getId() {
        return id;
    }

    // Setter for 'id' field
    public void setId(int id) {
        this.id = id;
    }

    // Getter for 'step' field
    public String getStep() {
        return step;
    }

    // Setter for 'step' field
    public void setStep(String step) {
        this.step = step;
    }

    public static void main(String[] args) {
        File file = new File("C:/Users/Dell/Documents/GitHub/CS321/src/main/java/org/openjfx/info.json");
        WorkFlowReader w = new WorkFlowReader(file);
        w.editStep(w.getId("Approve"), "Done");
        w.addPost(6, "Review");

    }
}
