package org.openjfx;

import java.io.File;

public class tester {

    public static void main(String[] arg) {
        File testerFile = new File("src/main/resources/WorkFlowTester.json");
        WorkFlowReader wftReader = new WorkFlowReader(testerFile);
        wftReader.addPost(200, "Review");

    }
}