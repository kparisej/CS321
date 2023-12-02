package org.openjfx;

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
}