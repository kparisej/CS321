package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

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
        TextField aptNumberField = new TextField();
        TextField cityField = new TextField();
        TextField stateField = new TextField();
        TextField zipcodeField = new TextField();
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

        grid.add(new Label("Street:"), 0, 6);
        grid.add(streetField, 1, 6);

        grid.add(new Label("Apartment #:"), 0, 7);
        grid.add(aptNumberField, 1, 7);

        grid.add(new Label("City:"), 0, 8);
        grid.add(cityField, 1, 8);

        grid.add(new Label("State:"), 0, 9);
        grid.add(stateField, 1, 9);

        grid.add(new Label("ZipCode"), 0, 10);
        grid.add(zipcodeField, 1, 10);

        grid.add(new Label("Reason for Replacement:"), 0, 11);
        grid.add(reasonForReplacementArea, 1, 11);

        // Buttons for submit and cancel
        Button pull = new Button("Pull Next Form");
        Button btnSave = new Button("Save Changes");
        Button btnCancel = new Button("Cancel");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnCancel, pull, btnSave);
        btnSave.setDisable(true);
        grid.add(hbBtn, 1, 12);

        // Cancel button
        btnCancel.setOnAction(e -> {
            firstNameField.setText("");
            middleNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            datePicker.setText("");
            countryOfBirthField.setText("");
            streetField.setText("");
            aptNumberField.setText("");
            cityField.setText("");
            stateField.setText("");
            zipcodeField.setText("");
            reasonForReplacementArea.setText("");
            try {
                launchHomeApp(primaryStage);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        // button for pulling next review item from WF
        pull.setOnAction(e -> {
            File file1 = new File("src/main/resources/GreenCardReplacementForms.json");
            File file2 = new File("src/main/resources/GreenCardReplacementWorkFlow.json");
            FormReader f = new FormReader(file1);
            WorkFlowReader w = new WorkFlowReader(file2);
            int id = w.getId("Review");
            if (id == -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No Review items in workTable");

                alert.getButtonTypes().setAll(ButtonType.OK);

                alert.showAndWait();
            } else {
                GreenCardReplacementBO x;
                x = f.getForm(id);

                firstNameField.setText(x.getFirstName());
                middleNameField.setText(x.getMiddleName());
                lastNameField.setText(x.getLastName());
                emailField.setText(x.getEmail());
                datePicker.setText(x.getDateOfBirth());
                countryOfBirthField.setText(x.getCountryOfBirth());
                streetField.setText(x.getHomeAddress().getStreetName());
                aptNumberField.setText(x.getHomeAddress().getApartmentNumber());
                cityField.setText(x.getHomeAddress().getCity());
                stateField.setText(x.getHomeAddress().getState());
                zipcodeField.setText(x.getHomeAddress().getZipCode());
                reasonForReplacementArea.setText(x.getReasonForReplacement());
                btnSave.setDisable(false);

            }
        });

        // button for saving changes
        btnSave.setOnAction(e -> {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Save");
            confirmationAlert.setHeaderText("Save Changes");
            confirmationAlert.setContentText("Are you sure you want to save your changes to this form?");

            // Set the button types.
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                File file2 = new File("src/main/resources/GreenCardReplacementWorkFlow.json");
                WorkFlowReader w = new WorkFlowReader(file2);
                int id = w.getId("Review");

                HomeAddress h = new HomeAddress(streetField.getText(), aptNumberField.getText(), cityField.getText(),
                        stateField.getText(), zipcodeField.getText());
                FormReader.editForm(id, firstNameField.getText(), middleNameField.getText(), lastNameField.getText(),
                        emailField.getText(), datePicker.getText(), countryOfBirthField.getText(), h,
                        reasonForReplacementArea.getText());
                WorkFlowReader.editStep(id, "Approve");
                firstNameField.setText("");
                middleNameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
                datePicker.setText("");
                countryOfBirthField.setText("");
                streetField.setText("");
                aptNumberField.setText("");
                cityField.setText("");
                stateField.setText("");
                zipcodeField.setText("");
                reasonForReplacementArea.setText("");
                btnSave.setDisable(true);
            }
        });

        // Set the scene
        Scene scene = new Scene(grid, 725, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Review Screen");
        primaryStage.show();
    }
    public void launchHomeApp(Stage primaryStage) throws FileNotFoundException {
        HomeApp homeApp = new HomeApp();
        homeApp.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}