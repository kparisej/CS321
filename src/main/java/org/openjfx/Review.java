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
import java.time.LocalDate;
import java.util.ArrayList;

public class Review extends Application {
    public static void main(String[] args) {
        launch(args);
    }

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
        DatePicker datePicker = new DatePicker();
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

        grid.add(new Label("Date of Birth:"), 0, 3);
        grid.add(datePicker, 1, 3);

        grid.add(new Label("Country Of Birth:"), 0, 4);
        grid.add(countryOfBirthField, 1, 4);

        grid.add(new Label("Home Address:"), 0, 5);
        grid.add(streetField, 1, 5);

        grid.add(new Label("Reason for Replacement:"), 0, 6);
        grid.add(reasonForReplacementArea, 1, 6);

        // Buttons for submit and cancel
        Button btnSave = new Button("Save Changes");
        Button btnCancel = new Button("Cancel");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnSave, btnCancel);
        grid.add(hbBtn, 1, 8);

        // Set the scene
        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Review Screen");
        primaryStage.show();
    }
}
