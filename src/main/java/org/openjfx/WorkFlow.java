package org.openjfx;

import java.util.LinkedList;

class FormInfo{
    int id;
    String step;
}

public class WorkFlow {

    // Queue that contains a set of the green card form, the application ID, and the string of the current step
    public LinkedList<FormInfo> applicationQueue;

    public WorkFlow(){
        applicationQueue = new LinkedList<FormInfo>();
    }

    // Update a specific application in the queue
    public void update(int applicationID){

    }

    public int getID(String review){
        return -1;
    }

    public void displayWorkFlow(){

    }

}
