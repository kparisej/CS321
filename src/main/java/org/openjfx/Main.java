package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {



    public String hardCodedApplicantString(){
        return "    Application Details:\nName: THE Great Gatsby\nDOB: March 31st 2006\nCountry Of Birth: Canada\nHome Address: 123 London Street,\nCalifornia 93142-1234\nReason: Lost in time-travel";
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Approval Screen");

        Text applicationDetails = new Text(hardCodedApplicantString());

        Label commentLabel = new Label("Comment:");
        TextField comment = new TextField();

        Button denyButton = new Button();
        denyButton.setText("Deny Application");
        denyButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Denied Application");
            }
        });

        Button approvalButton = new Button();
        approvalButton.setText("Approve Application");
        approvalButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Approved Application");
            }
        });

        Pane root = new Pane();
        Font screenFont = Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 16);

        applicationDetails.setLayoutX(75);
        applicationDetails.setLayoutY(15);
        applicationDetails.setFont(screenFont);
        root.getChildren().add(applicationDetails);

        commentLabel.setLayoutX(40);
        commentLabel.setLayoutY(200);
        commentLabel.setFont(screenFont);
        root.getChildren().add(commentLabel);

        comment.setLayoutX(200);
        comment.setLayoutY(200);
        root.getChildren().add(comment);

        denyButton.setLayoutX(75);
        denyButton.setLayoutY(300);
        root.getChildren().add(denyButton);


        approvalButton.setLayoutX(200);
        approvalButton.setLayoutY(300);
        root.getChildren().add(approvalButton);

        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}