package org.openjfx;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WorkFlowTest {

    @Test
    public void test8() {
        File testerFile = new File("src/main/resources/WorkFlowTester.json");
        WorkFlowReader test8 = new WorkFlowReader(testerFile);
    }

    File testerFile = new File("src/main/resources/WorkFlowTest.json");
    WorkFlowReader wftReader = new WorkFlowReader(testerFile);

    @Test
    public void test9() {
        wftReader.addPost(200, "Review");
    }

    @Test
    public void test10() {
        assertEquals(200, wftReader.getId("Review"));
    }

    @Test
    public void test11() {
        wftReader.addPost(201, "Approve");
    }

    @Test
    public void test12() {
        assertEquals(201, wftReader.getId("Approve"));
    }

    @Test
    public void test13() {
        wftReader.addPost(202, "Done");
    }

    @Test
    public void test14() {
        assertEquals(202, wftReader.getId("Done"));
    }

    @Test
    public void test15() {
        wftReader.editStep(201, "Done");
    }

    @Test
    public void test16() {
        assertEquals(201, wftReader.getId("Done"));
    }

}