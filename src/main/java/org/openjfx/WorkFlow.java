package org.openjfx;
import java.util.LinkedList;
import java.util.ArrayList;

public class WorkFlow { // Using singleton design pattern, only one instance of the workflow should exist
    // Singleton design patterns are used to ensure that only 1 instance will ever be created.
    // Access it by Workflow varname = Workflow.getInstance();
    // Then run methods normally i.e varName.methodName();

    public enum possibleSteps{
        REQUEST, REVIEWAL, APPROVAL, STORAGE;
        public possibleSteps nextStep(){    
            switch (this) {
                case REQUEST:
                    return REVIEWAL;
                case REVIEWAL:
                    return APPROVAL;
                case APPROVAL:
                    return STORAGE;
                case STORAGE:
                    return STORAGE; // Should not be able to go backwards given completion
                default:
                    return REQUEST;
            }
        }
    }

    public class FormInfo{
        GreenCardReplacementBO greenCardReference; // The form
        int id; // Unique ID, no form should share a unique id
        possibleSteps step; // Last step completed
    }

    private static final WorkFlow singleInstance = new WorkFlow();
    static ArrayList<Integer> usedIDs = new ArrayList<>();
    
    // Queue that contains a set of the green card form, the application ID, and the current step
    public static LinkedList<FormInfo> applicationQueue = new LinkedList<FormInfo>();

    private WorkFlow(){}

    //Assign each form a random id that is 6 digits long.
    public int generateNewID(){
        Integer id;
        do {
            id = (int)(Math.random() * 999999);
        } while (usedIDs.contains(id));
        usedIDs.add(id);
        return id;
    }

    public void addToWorkflow(GreenCardReplacementBO request){
        FormInfo newForm = new FormInfo();
        newForm.greenCardReference = request;
        newForm.id = generateNewID();
        newForm.step = possibleSteps.REQUEST;
    }

    // Update a specific application in the queue
    public void update(int applicationID){
        for (FormInfo form : applicationQueue) {
            if(form.id == applicationID){
                form.step = form.step.nextStep();
                return;
            }
        }
    }

    // Return a ID of a object with a specific stage
    public int getID(possibleSteps stage){
        for (FormInfo form : applicationQueue) {
            if(form.step == stage){
                return form.id;
            }
        }
        return -1; // Not found
    }

    // Return a form based on id
    public FormInfo getFormFromID(int id){
        for (FormInfo form : applicationQueue) {
            if(form.id == id){
                return form;
            }
        }
        return null;
    }

    // Return the first form that matches the stage wanted
    public FormInfo getFormFromStage(possibleSteps stageWanted){
        for (FormInfo form : applicationQueue) {
            if(form.step == stageWanted){
                return form;
            }
        }
        return null;
    }

    // Display the current workflow
    public void displayWorkFlow(){
        StringBuilder workFlowBuilder = new StringBuilder("The workflow contains " + applicationQueue.size() + " elements:");
        int index = 1; // Start at 1 for display
        for (FormInfo form : applicationQueue) {
            String stage;
            switch (form.step) {
                case REQUEST:
                    stage = "Request stage";
                    break;
                case REVIEWAL:
                    stage = "Reviewal stage";
                    break;
                case APPROVAL:
                    stage = "Approval stage";
                    break;
                default:
                    stage = "all stages and is completed";
                    break;
            }
            workFlowBuilder.append("\n"+index+" Greencard Request: " + form.greenCardReference + "has ID: "+form.id+" which has completed the "+stage);
            index = index + 1;
        }
    }


    // Returns the singleInstance needed for singleton
    public static WorkFlow getInstance(){
        return singleInstance;
    }

}
