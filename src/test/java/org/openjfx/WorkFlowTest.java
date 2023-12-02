//package org.openjfx;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.util.LinkedList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class WorkFlowTest {
//    private WorkFlow work = new WorkFlow();
//
//    @BeforeEach
//    void setUp() {
//        FormInfo form1 = new FormInfo();
//        form1.id = 1;
//        form1.step = "Approval";
//        work.applicationQueue.add(form1);
//    }
//
//    @AfterEach
//    void tearDown(){
//        assertNull(this.work);
//    }
//
//
//    @Test
//    public void WorkFlowTester(){
//        // Make sure the queues are instantiated
//        assertTrue(work.applicationQueue instanceof LinkedList<?>);
//    }
//
//    @Test
//    public void updateTest(){
//        FormInfo before = work.applicationQueue.get(0);
//        work.update(0);
//        FormInfo after = work.applicationQueue.get(1);
//        assertNotEquals(before,after);
//    }
//
//    @Test
//    public void getIDTest(){
//        FormInfo test = work.applicationQueue.get(0);
//        assertEquals(work.getID("Approval"), test.id);
//    }
//
//    @Test
//    public void displayWorkFlowTest() throws FileNotFoundException {
//        FileOutputStream newOut = new FileOutputStream("Out.txt");
//        System.setOut(new PrintStream(newOut));
//        work.displayWorkFlow();
//        String correctResult = "";
//        assertEquals(correctResult, newOut.toString());
//    }
//
//}