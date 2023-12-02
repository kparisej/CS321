package org.openjfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

public class HomeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        GridPane gridPane = new GridPane();

        // Setting size for pane
        gridPane.setMinSize(500, 300);

        // Setting the padding
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        // Setting vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label home = new Label("WELCOME");
        home.setStyle("-fx-font-weight: bold; -fx-font-size: 24;");
        home.setAlignment(Pos.CENTER);
        // Add the label to an HBox for centering
        HBox headerBox = new HBox(home);
        headerBox.setAlignment(Pos.CENTER);
        gridPane.add(headerBox, 16,2);

        //creating the image object
        URL dataEntryImageUrl = getClass().getResource("/data_entry_form_button_icon.png");
        assert dataEntryImageUrl != null;
        InputStream dataEntryImageFileStream = new FileInputStream(dataEntryImageUrl.getFile());
        Image dataEntryIconImage = new Image(dataEntryImageFileStream);
        //Creating the image view
        ImageView dataEntryImageView = new ImageView();
        //Setting image to the image view
        dataEntryImageView.setImage(dataEntryIconImage);
        dataEntryImageView.setPreserveRatio(true);
        dataEntryImageView.setFitWidth(56);

        // Button to launch Data Entry Form
        Button dataEntryButton = new Button("Launch Form");
        gridPane.add(dataEntryButton,16 ,4);
        dataEntryButton.setGraphic(dataEntryImageView);
        dataEntryButton.setOnAction(actionEvent -> launchDataEntryScreen(primaryStage));

        //creating the image object
        URL reviewImageUrl = getClass().getResource("/review_form_button_icon.png");
        assert reviewImageUrl != null;
        InputStream reviewImageFileStream = new FileInputStream(reviewImageUrl.getFile());
        Image reviewImage = new Image(reviewImageFileStream);
        //Creating the image view
        ImageView reviewImageView = new ImageView();
        //Setting image to the image view
        reviewImageView.setImage(reviewImage);
        reviewImageView.setPreserveRatio(true);
        reviewImageView.setFitWidth(56);

        // Button to launch review screen
        Button reviewButton = new Button("Review Form");
        gridPane.add(reviewButton, 16,6);
        reviewButton.setGraphic(reviewImageView);
        reviewButton.setOnAction(actionEvent -> launchReviewScreen(primaryStage));

        // Creating the Approval Image object
        URL approvalImageUrl = getClass().getResource("/approve_form_button_icon.png");
        assert approvalImageUrl != null;
        InputStream approvalImageFileStream = new FileInputStream(approvalImageUrl.getFile());
        Image approvalImage = new Image(approvalImageFileStream);

        // Creating image view for approval image icon
        ImageView approvalImageView  = new ImageView();
        approvalImageView.setImage(approvalImage);
        approvalImageView.setPreserveRatio(true);
        approvalImageView.setFitWidth(50);

        // Button to launch approval screen
        Button approvalButton = new Button("Approve Form");
        gridPane.add(approvalButton, 16, 8);
        approvalButton.setGraphic(approvalImageView);
        approvalButton.setOnAction(actionEvent -> {
            try {
                launchApprovalScreen(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().add(gridPane);

        var scene = new Scene(root);
        primaryStage.setTitle("U.S. Citizenship & Immigration Services");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void launchDataEntryScreen(Stage primaryStage) {
        DataEntryApp dataEntry = new DataEntryApp();
        dataEntry.start(primaryStage);
    }
    private void launchReviewScreen(Stage primaryStage) {
        Review review = new Review();
        review.start(primaryStage);
    }
    private void launchApprovalScreen(Stage primaryStage) {
        Approval approval = new Approval();
        approval.start(primaryStage);
    }
}
