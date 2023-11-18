package org.openjfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class Review extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add form fields
        TextField firstNameField = new TextField();
        TextField middleNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        TextField datePicker = new TextField();
        TextField countryOfBirthField = new TextField();
        // Assuming HomeAddress has fields like street, city, etc.
        TextField streetField = new TextField();
        // ... other address fields
        TextArea reasonForReplacementArea = new TextArea();

        // Add labels and fields to the grid
        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);

        grid.add(new Label("Middle Name:"), 0, 1);
        grid.add(middleNameField, 1, 1);

        grid.add(new Label("Last Name:"), 0, 2);
        grid.add(lastNameField, 1, 2);

        grid.add(new Label("Email:"), 0, 3);
        grid.add(emailField, 1, 3);

        grid.add(new Label("Date of Birth:"), 0, 4);
        grid.add(datePicker, 1, 4);

        grid.add(new Label("Country Of Birth:"), 0, 5);
        grid.add(countryOfBirthField, 1, 5);

        grid.add(new Label("Home Address:"), 0, 6);
        grid.add(streetField, 1, 6);

        grid.add(new Label("Reason for Replacement:"), 0, 7);
        grid.add(reasonForReplacementArea, 1, 7);

        // Buttons for submit and cancel
        Button pull = new Button("Pull Next Form");
        Button btnSave = new Button("Save Changes");
        Button btnCancel = new Button("Cancel");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(pull, btnSave, btnCancel);
        grid.add(hbBtn, 1, 8);

        // Add an event handler to the button
        pull.setOnAction(e -> {
            File file2 = new File("C:/Users/Dell/Documents/GitHub/CS321/src/main/java/org/openjfx/info.json");
            File file1 = new File("C:/Users/Dell/Documents/GitHub/CS321/src/main/java/org/openjfx/forms.json");
            FormReader f = new FormReader(file1);
            WorkFlowReader w = new WorkFlowReader(file2);
            int id = w.getId("Review");
            GreenCardReplacementBO x = new GreenCardReplacementBO();
            x = f.getForm(id);
            firstNameField.setText(x.getFirstName());
            middleNameField.setText(x.getMiddleName());
            lastNameField.setText(x.getLastName());
            emailField.setText(x.getEmail());
            datePicker.setText(x.getDateOfBirth());
            countryOfBirthField.setText(x.getCountryOfBirth());
            streetField.setText(x.getHomeAddress().getStreetName() + " " + x.getHomeAddress().getApartmentNumber() + " "
                    + x.getHomeAddress().getCity() + " " + x.getHomeAddress().getState() + " "
                    + x.getHomeAddress().getZipCode());
            reasonForReplacementArea.setText(x.getReasonForReplacement());

            // Define the action to perform when the button is pressed
            // You can put your code here to do something when the button is pressed
        });

        // Set the scene
        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Review Screen");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
