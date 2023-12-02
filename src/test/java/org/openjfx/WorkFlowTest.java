package org.openjfx;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class WorkFlowTest {
    @Test
    public void test1() {
        int expectedId = 5;
        String expectedString = "Test";
        var workflow = new WorkFlow(expectedId, expectedString);
        assertEquals(workflow.getId(), expectedId);
        assertEquals(workflow.getStep(), expectedString);
    }

    @Test
    public void test2() {
        int expectedId = 5;
        var workflow = new WorkFlow(expectedId, null);
        assertEquals(workflow.getId(), expectedId);
        assertNotEquals(expectedId - 1, workflow.getId());
    }

    @Test
    public void test3() {
        String expectedString = "Test";
        var workflow = new WorkFlow(-1, expectedString);
        assertEquals(workflow.getStep(), expectedString);
        assertNotEquals(workflow.getId(), expectedString);
    }

    @Test
    public void test4() {
        int original = 5;
        int newSetValue = 8;
        var workflow = new WorkFlow(original, null);
        workflow.setId(newSetValue);
        assertEquals(workflow.getId(), newSetValue);
        assertNotEquals(original, workflow.getId());
    }

    @Test
    public void test5() {
        String original = "Old";
        String newSetValue = "New";
        var workflow = new WorkFlow(-1, original);
        workflow.setStep(newSetValue);
        assertEquals(workflow.getStep(), newSetValue);
        assertNotEquals(original, workflow.getStep());
    }

    @Test
    public void test6() {
        File testerFile = new File("src/main/resources/WorkFlowTest.json");
        WorkFlowReader test8 = new WorkFlowReader(testerFile);
    }

    File testerFile = new File("src/main/resources/WorkFlowTest.json");
    WorkFlowReader wftReader = new WorkFlowReader(testerFile);

    @Test
    public void test7() {
        wftReader.addPost(200, "Review");
    }

    @Test
    public void test8() {
        assertEquals(200, wftReader.getId("Review"));
    }

    @Test
    public void test9() {
        wftReader.addPost(201, "Approve");
    }

    @Test
    public void test10() {
        wftReader.addPost(202, "Done");
    }

    @Test
    public void test11() {
        assertEquals(202, wftReader.getId("Done"));
    }

    @Test
    public void test12() {
        wftReader.editStep(201, "Done");

        wftReader.addPost(203, "Done");
        wftReader.editStep(203, "Approve");
    }

    @Test
    public void test13() {
        assertEquals(203, wftReader.getId("Approve"));
    }

}