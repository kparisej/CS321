package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Properties;

// For Email
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Approval extends Application {

    public static GreenCardReplacementBO currentForm = null;
    private static final Text displayForm = new Text("No Form Loaded"); // Make displayform hotswapable by making it a class variable not a local/smaller scope variable
    private static final Text errorText = new Text();

    public GreenCardReplacementBO getNewForm(){ // Need a reference to the file every time if you want to constantly check for new approval values
        File infoJSON = new File("src/main/resources/GreenCardReplacementWorkFlow.json");
        File formsJSON = new File("src/main/resources/GreenCardReplacementForms.json");
        FormReader forms = new FormReader(formsJSON);
        WorkFlowReader workflow = new WorkFlowReader(infoJSON);
        if(currentForm != null){
            WorkFlowReader.editStep(currentForm.getId(), "Done");
        }
        return forms.getForm(workflow.getId("Approve"));
    }
    public void sendEmail(String message, String emailRecip){
        String address = "dawdsdaswaws@gmail.com"; // Obv burner account
        String password = "cway xbwp kipm jjnr";
        Properties emailProperties = new Properties();
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.ssl.enable", "true");
        emailProperties.put("mail.smtp.host", "smtp.gmail.com");
        emailProperties.put("mail.smtp.port", "465");
        var callback = new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(address, password);
            }
        };
        var emailSession = Session.getInstance(emailProperties, callback);
        Message email = new MimeMessage(emailSession);
        try {
            email.setFrom(new InternetAddress("dawdsdaswaws@gmail.com"));
            email.setRecipient(Message.RecipientType.TO, new InternetAddress(emailRecip));
            email.setText(message);
            email.setSubject("CS321 Group 31 Green Card Renewal");
            email.setSentDate(new Date());
            Transport.send(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkQueueForNew(){
        Timer checkFrequently = new Timer();
        checkFrequently.schedule(new TimerTask() {
            @Override
            public void run(){
                currentForm = getNewForm();
                if(currentForm != null){
                    displayForm.setText(formToString(currentForm));
                    checkFrequently.cancel();
                }
            }
        },0, 2000); //0 = now, 2000 ms = every 2 seconds
    }

    public void onButton(boolean wasApproved, String comment){
        if(currentForm != null){ //Do this next sprint
            String emailString = "";
            if(wasApproved){
                emailString = "Congratulations!\nYour application was approved for Green Card Renewal";
            }
            else{
                emailString = "Your application was denied for Green Card Renewal";
            }
            if(comment != null){
                emailString = emailString + "\nThe approver left this comment: " + comment;
            }
            final String message = emailString;
            new Thread(() -> sendEmail(message, currentForm.getEmail())).start(); // Done in an async way to prevent stalling.
        }
        errorText.setText(""); // New form no errors... yet
        currentForm = getNewForm();
        if(currentForm == null){
            checkQueueForNew();
        }
        displayForm.setText(formToString(currentForm));
    }

    public String formToString(GreenCardReplacementBO form){
        StringBuilder applicationDetails = new StringBuilder();
        if(form != null){
            applicationDetails.append("Name: ").append(form.getFirstName()).append(" ").append(form.getMiddleName()).append(" ").append(form.getLastName());
            applicationDetails.append("\nEmail: ").append(form.getEmail());
            applicationDetails.append("\nCountry of Birth: ").append(form.getCountryOfBirth());
            applicationDetails.append("\nDate of Birth: ").append(form.getDateOfBirth());
            applicationDetails.append("\nHome Address: ").append(form.getHomeAddress().getApartmentNumber()).append(" ").append(form.getHomeAddress().getStreetName()).append(", ").append(form.getHomeAddress().getCity()).append(" ").append(form.getHomeAddress().getState()).append(", ").append(form.getHomeAddress().getZipCode());
            applicationDetails.append("\nReason for Replacement: ").append(form.getReasonForReplacement());
        }
        else{
            applicationDetails.append("No forms on the approval stage");
        }
        return applicationDetails.toString();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Approval Screen");
        Label title = new Label("Application Details:");
        Label checklistTitle = new Label("Checklist:");
        currentForm = getNewForm();
        displayForm.setText(formToString(currentForm));
        Label commentLabel = new Label("Comment:");
        TextField comment = new TextField();

        CheckBox checkBox1 = new CheckBox("Information provided matches records known");
        CheckBox checkBox2 = new CheckBox("Lives in the United States of America or a controlled territory");
        CheckBox checkBox3 = new CheckBox("Still holds a Green Card or is eligible to renew it");
        CheckBox checkBox4 = new CheckBox("Has not commited any felonies within the past five years");

        CheckBox sendComment = new CheckBox("Send this comment to the applicant");

        Button denyButton = new Button();
        denyButton.setText("Deny Application");
        denyButton.setOnAction(event -> {
            if (currentForm != null) {
                String commentToSend = null;
                if (sendComment.isSelected()) {
                    commentToSend = comment.getText();
                }
                onButton(false, commentToSend);
            }
        });

        Button approvalButton = new Button();
        approvalButton.setText("Approve Application");
        approvalButton.setOnAction(event -> {
            if(checkBox1.isSelected() && checkBox2.isSelected() && checkBox3.isSelected() && checkBox4.isSelected() && currentForm != null){
                String commentToSend = null;
                if(sendComment.isSelected()){
                    commentToSend = comment.getText();
                }
                onButton(true, commentToSend);
            }
            else{
                errorText.setText("The checklist must be filled out when trying to approve someone");
            }
        });

        Button returnHome = new Button();
        returnHome.setText("Return To Homescreen");
        returnHome.setOnAction(event -> {
            try {
                launchHomeApp(primaryStage);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        GridPane root = new GridPane();
        root.setMinSize(800, 800);
        root.setVgap(5);
        root.setHgap(5);

        HBox buttons = new HBox(8);
        buttons.getChildren().addAll(denyButton,approvalButton);
        Font screenFont = Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 16);
        Font headerFont = Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 24);
        commentLabel.setFont(headerFont);
        title.setFont(headerFont);
        checklistTitle.setFont(headerFont);
        displayForm.setFont(screenFont);
        checkBox1.setFont(screenFont);
        checkBox2.setFont(screenFont);
        checkBox3.setFont(screenFont);
        checkBox4.setFont(screenFont);
        errorText.setFont(screenFont);
        root.add(title,0,0);
        root.add(displayForm,1,5);

        root.add(checklistTitle, 0, 25);
        root.add(checkBox1, 1, 27);
        root.add(checkBox2, 1, 28);
        root.add(checkBox3, 1, 29);
        root.add(checkBox4, 1, 30);

        root.add(commentLabel, 0, 50);
        root.add(comment,1,50);
        root.add(sendComment, 1,52);

        root.add(buttons,1,75);
        root.add(returnHome, 1, 76);
        root.add(errorText, 1, 80);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

        if(currentForm == null){
            checkQueueForNew();
        }
    }

    private void launchHomeApp(Stage primaryStage) throws FileNotFoundException{
        HomeApp homeApp = new HomeApp();
        homeApp.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}