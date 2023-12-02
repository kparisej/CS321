package org.openjfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * JavaFX App
 */
public class DataEntryApp extends Application {
    private static int id;
    private static String firstName;
    private static String middleName;
    private static String lastName;
    private static String email;
    private static String dateOfBirth;
    private static String countryOfBirth;
    private static HomeAddress homeAddress;
    private static String streetName;
    private static String apartmentNumber;
    private static String city;
    private static String state;
    private static String zipcode;
    private static String reasonForReplacement;
    private String invalidInputMessage;

    public boolean setUpForm(final Stage stage){
        // Creating a Grid Pane
        GridPane gridPane = new GridPane();

        // Setting size for pane
        gridPane.setMinSize(500, 300);

        // Setting the padding
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        // Setting vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Setting the grid alignment
        gridPane.setAlignment(Pos.CENTER);


        Label greenCardReplacementForm = new Label("Green Card Replacement Request Form");
        greenCardReplacementForm.setStyle("-fx-font-weight: bold; -fx-font-size: 17;");
        greenCardReplacementForm.setAlignment(Pos.CENTER);
        // Add the label to an HBox for centering
        HBox headerBox = new HBox(greenCardReplacementForm);
        headerBox.setAlignment(Pos.CENTER);
        gridPane.add(headerBox, 0, 0, 3, 1);
        // Label for first name
        Label firstNameLabel = new Label("First Name: *");
        // Text Field for first name
        TextField firstNameText = new TextField();
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameText, 1, 1);

        // Label for middle name
        Label middleNameLabel = new Label("Middle Name: (Optional)");
        // Text Field for middle name
        TextField middleNameText = new TextField();
        gridPane.add(middleNameLabel, 0,2);
        gridPane.add(middleNameText, 1, 2);

        // Label for last name
        Label lastNameLabel = new Label("Last Name: *");
        // Text Field for last name
        TextField lastNameText = new TextField();
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameText, 1, 3);

        // Label for email
        Label emailLabel = new Label("E-mail: *");
        // Text Field for email
        TextField emailText = new TextField();
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailText, 1, 4);

        // Label for date of birth
        Label dateOfBirthLabel = new Label("Date of Birth: *");
        // Text Field for date of birth
        DatePicker dateOfBirthPicker = new DatePicker();
        gridPane.add(dateOfBirthLabel, 0, 5);
        gridPane.add(dateOfBirthPicker, 1,5);

        dateOfBirthPicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });
        dateOfBirthPicker.setEditable(false);

        // Label for country of birth
        Label countryOfBirthLabel = new Label("Country of Birth: *");
        gridPane.add(countryOfBirthLabel, 0, 6);
        // Populates dropdown with list of country names for user to select.
        JSONParser jsonParser = new JSONParser();
        ArrayList<String> listOfCountries = new ArrayList<>();
        try {
            URL url = getClass().getResource("/countries/en/countries.json");
            assert url != null;
            System.out.println(url.getPath());
            JSONArray data = (JSONArray) jsonParser.parse(
                    new FileReader(url.getPath()));//path to the JSON file.

            for (Object datum : data) {
                JSONObject jsonObj = (JSONObject) datum;
                listOfCountries.add((String) jsonObj.get("name"));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        ChoiceBox<String> countryOfBirthDropDown = new ChoiceBox<>(FXCollections.observableArrayList(listOfCountries));
        gridPane.add(countryOfBirthDropDown, 1, 6);

        Label streetLabel = new Label("Street Name: *");
        TextField streetText = new TextField();
        gridPane.add(streetLabel, 0, 7);
        gridPane.add(streetText, 1, 7);

        Label apartmentNumberLabel = new Label("Apartment number: (Optional)");
        TextField apartmentNumberText = new TextField();
        apartmentNumberText.setMaxWidth(150);
        gridPane.add(apartmentNumberLabel,0,8);
        gridPane.add(apartmentNumberText, 1, 8);


        Label stateLabel = new Label("State: *");
        ArrayList<String> listOfStates = new ArrayList<>();
        gridPane.add(stateLabel, 0, 9);

        // Read from json file
        try {
            URL url = getClass().getResource("/us_states_and_cities/us_states_and_cities.json");
            assert url != null;
            System.out.println(url.getPath());
            JSONArray data = (JSONArray) jsonParser.parse(
                    new FileReader(url.getPath()));     //path to the JSON file.

            for (Object datum : data) {
                JSONObject jsonObj = (JSONObject) datum;
                listOfStates.add((String) jsonObj.get("name"));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        ChoiceBox<String> stateDropdown = new ChoiceBox<>(FXCollections.observableArrayList(listOfStates));
        stateDropdown.setPrefWidth(150);
        gridPane.add(stateDropdown, 1, 9);

        Label cityLabel = new Label("City: *");
        gridPane.add(cityLabel, 0, 10);
        ArrayList<String> listOfCities = new ArrayList<>();
        ChoiceBox<String> cityDropdown = new ChoiceBox<>(FXCollections.observableArrayList(listOfCities));
        cityDropdown.setPrefWidth(150);
        gridPane.add(cityDropdown, 1, 10);
        cityDropdown.setOnMouseClicked(actionEvent -> {
            cityDropdown.requestFocus();
            cityDropdown.setItems(FXCollections.observableArrayList(listOfCities));
        });

        stateDropdown.setOnAction(event-> {
            listOfCities.clear();
            System.out.println("selected: " + stateDropdown.getValue());
            try {
                URL url = getClass().getResource("/us_states_and_cities/us_states_and_cities.json");
                assert url != null;
                JSONArray jsonData = (JSONArray) jsonParser.parse(new FileReader(url.getPath()));//path to the JSON file.

                for (Object jsonObj : jsonData) {
                    JSONObject obj = (JSONObject) jsonObj;
                    JSONArray objCityArray = (JSONArray) obj.get("cities");
                    if (obj.get("name").equals(stateDropdown.getValue())) {
                        System.out.println("cities: " + obj.get("cities"));
                        for (Object city : objCityArray) {
                            listOfCities.add(city.toString());
                        }
                    }
                }
                cityDropdown.setItems(FXCollections.observableArrayList(listOfCities));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        });

        Label zipcodeLabel = new Label("Zipcode: *");
        TextField zipcodeText = new TextField();
        zipcodeText.setMaxWidth(60);
        gridPane.add(zipcodeLabel, 0, 11);
        gridPane.add(zipcodeText, 1, 11);

        // Label for reason for replacement
        Label reasonForReplacementLabel = new Label("Reason for replacement: *");

        // Populates dropdown with an acceptable list of reasons as to why the user wants to request a new Green card.
        ArrayList<String> validReasonsForReplacement = new ArrayList<>();
        validReasonsForReplacement.add("U.S. Resident card will expire soon");
        validReasonsForReplacement.add("U.S. Resident card was stolen");
        validReasonsForReplacement.add("U.S. Resident card was lost");
        validReasonsForReplacement.add("U.S. Resident card is damaged");
        ChoiceBox<String> reasonForReplacementDropdown = new ChoiceBox<>(FXCollections.observableArrayList(validReasonsForReplacement));
        gridPane.add(reasonForReplacementLabel, 0, 12);
        gridPane.add(reasonForReplacementDropdown, 1, 12);

        final AtomicBoolean submitSuccessful = new AtomicBoolean();
        // Label for submit
        Button buttonSubmit = new Button("Submit");
        gridPane.add(buttonSubmit, 1,14);

        buttonSubmit.setOnAction(actionEvent -> {
            id += 1;
            firstName = firstNameText.getText();
            middleName = middleNameText.getText();
            lastName = lastNameText.getText();
            email = emailText.getText();
            dateOfBirth = String.valueOf(dateOfBirthPicker.getValue());
            countryOfBirth = countryOfBirthDropDown.getValue();
            streetName = streetText.getText();
            apartmentNumber = apartmentNumberText.getText();
            city = cityDropdown.getValue();
            state = stateDropdown.getValue();
            zipcode = zipcodeText.getText();
            homeAddress = new HomeAddress(streetName, apartmentNumber, city, state,zipcode);
            reasonForReplacement = reasonForReplacementDropdown.getValue();
            invalidInputMessage = "";

            if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || dateOfBirth == null || countryOfBirth == null || streetName.isEmpty() || state == null || city == null || zipcode.isEmpty() || reasonForReplacement == null){
                submitSuccessful.set(false);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid Submission: ");
                alert.setContentText("Please make sure all fields with an (*) indicator is complete.");
                alert.showAndWait();
                System.out.print("INVALID.......");

            }
            else {
                if (!isEmailValid(email)){
                    invalidInputMessage += "- Invalid e-mail address\n";
                    System.out.println("INVALID EMAIL ADDRESS!!!");
                }

                if (!isZipcodeValid(zipcode)){
                    invalidInputMessage += "- Invalid zipcode\n";
                    System.out.println("INVALID ZIPCODE");
                }
                if (invalidInputMessage.length() > 0){
                    submitSuccessful.set(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Personal information was entered incorrectly.");
                    alert.setContentText("Please go back and fix the following fields...\n" + invalidInputMessage);
                    alert.showAndWait();
                }
                else {
                    FormReader.addForm(firstName, middleName, lastName, email, dateOfBirth, countryOfBirth, homeAddress, reasonForReplacement);
                    printFormContent();
                    submitSuccessful.set(true);

                    try {
                        launchDoneScreen(stage);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Group root = new Group();
        root.getChildren().add(gridPane);

        var scene = new Scene(root);
        stage.setTitle("U.S. Citizenship & Immigration Services");
        stage.setScene(scene);
        stage.show();

        return submitSuccessful.get();
    }
    public void launchDoneScreen(Stage primaryStage) throws FileNotFoundException {
        GridPane doneScreenGridPane = new GridPane();
        doneScreenGridPane.setMinSize(500.0, 300.0);
        doneScreenGridPane.setPadding(new Insets(15.0, 15.0, 15.0, 15.0));
        doneScreenGridPane.setVgap(10.0);
        doneScreenGridPane.setHgap(10.0);
        doneScreenGridPane.setAlignment(Pos.CENTER);

        Label greenCardReplacementForm = new Label("Thank you for your submission.");
        greenCardReplacementForm.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        doneScreenGridPane.add(greenCardReplacementForm, 0, 3);
        GridPane.setHalignment(greenCardReplacementForm, HPos.CENTER);

        Button doneButton = new Button("Done");
        doneScreenGridPane.add(doneButton,0,5);
        GridPane.setHalignment(doneButton, HPos.CENTER);

        doneButton.setOnAction(actionEvent -> {
            try {
                launchHomeApp(primaryStage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        //creating the image object
        URL url = getClass().getResource("/form_submitted_done_icon.png");
        assert url != null;
        InputStream stream = new FileInputStream(url.getFile());
        Image image = new Image(stream);
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        //Setting the image view parameters
        imageView.setX(225);
        imageView.setY(50);
        imageView.setFitWidth(50);

        BorderPane pane = new BorderPane();
        pane.setPrefSize(10, 10);
        pane.setCenter(imageView);

        Group root = new Group();
        root.getChildren().add(doneScreenGridPane);
        root.getChildren().add(imageView);

        Scene reviewScene = new Scene(root);
        primaryStage.setScene(reviewScene);
        primaryStage.setTitle("Application Submitted");
        primaryStage.show();

    }
    @Override
    public void start(Stage primaryStage) {
        if (setUpForm(primaryStage)){
            primaryStage.close();
        }
    }
    public boolean isEmailValid(String email){
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        //Matching the given phone number with regular expression
        return email.matches(regex);
    }
    public boolean isZipcodeValid(String zipcode){
        String regex = "^\\d{5}(?:-\\d{4})?$";
        // Matching the given zipcode with regular expression
        return zipcode.matches(regex);
    }
    public void printFormContent(){
        System.out.println("ID: " + id);
        System.out.println("First name: " + firstName);
        System.out.println("Middle name: " + middleName);
        System.out.println("Last name: " + lastName);
        System.out.println("Date of birth: " + dateOfBirth);
        System.out.println("country of birth: " + countryOfBirth);
        System.out.println("==Home Address==");
        System.out.println("\tStreet name: " + streetName);
        System.out.println("\tApartment number: " + apartmentNumber);
        System.out.println("\tCity: " + city);
        System.out.println("\tState: " + state);
        System.out.println("\tZipcode: " + zipcode);
        System.out.println("Reason For Replacement: " + reasonForReplacement);
    }
    public void launchHomeApp(Stage primaryStage) throws FileNotFoundException {
        HomeApp homeApp = new HomeApp();
        homeApp.start(primaryStage);
    }
    public static void main(String[] args) {
        launch();
    }

}