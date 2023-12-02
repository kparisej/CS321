package org.openjfx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkFlowTest {

    @Test
    public void constructorTest(){
        int expectedId = 5;
        String expectedString = "Test";
        var workflow = new WorkFlow(expectedId, expectedString);
        assertEquals(workflow.getId(), expectedId);
        assertEquals(workflow.getStep(), expectedString);
    }

    @Test
    public void getIdTest(){
        int expectedId = 5;
        var workflow = new WorkFlow(expectedId, null);
        assertEquals(workflow.getId(), expectedId);
        assertNotEquals(expectedId-1, workflow.getId());
    }

    @Test
    public void getStringTest(){
        String expectedString = "Test";
        var workflow = new WorkFlow(-1, expectedString);
        assertEquals(workflow.getStep(), expectedString);
        assertNotEquals(workflow.getId(), expectedString);
    }

    @Test
    public void setIdTest(){
        int original = 5;
        int newSetValue = 8;
        var workflow = new WorkFlow(original, null);
        workflow.setId(newSetValue);
        assertEquals(workflow.getId(), newSetValue);
        assertNotEquals(original, workflow.getId());
    }

    @Test
    public void setStringTest(){
        String original = "Old";
        String newSetValue = "New";
        var workflow = new WorkFlow(-1, original);
        workflow.setStep(newSetValue);
        assertEquals(workflow.getStep(), newSetValue);
        assertNotEquals(original, workflow.getStep());
    }
}