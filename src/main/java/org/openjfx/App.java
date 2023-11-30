package org.openjfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
public class App extends Application {
    private static GreenCardReplacementBO greenCardReplacementBO;
    private static int id;
    private static String firstName;
    private static String middleName;
    private static String lastName;
    private static LocalDate dateOfBirth;
    private static String countryOfBirth;
    private static HomeAddress homeAddress;
    private static String streetName;
    private static String apartmentNumber;
    private static String city;
    private static String state;
    private static String zipcode;
    private static String reasonForReplacement;

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


        Label greenCardReplacementForm = new Label("Green Card Replacement Request Form 2023");
        greenCardReplacementForm.setStyle("-fx-font-weight: bold; -fx-font-size: 17;");
        greenCardReplacementForm.setAlignment(Pos.CENTER);
        // Add the label to an HBox for centering
        HBox headerBox = new HBox(greenCardReplacementForm);
        headerBox.setAlignment(Pos.CENTER);
        gridPane.add(headerBox, 0, 0, 3, 1);
        // Label for first name
        Label firstNameLabel = new Label("First Name: ");
        // Text Field for first name
        TextField firstNameText = new TextField();
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameText, 1, 1);

        // Label for middle name
        Label middleNameLabel = new Label("Middle Name: ");
        // Text Field for middle name
        TextField middleNameText = new TextField();
        gridPane.add(middleNameLabel, 0,2);
        gridPane.add(middleNameText, 1, 2);

        // Label for last name
        Label lastNameLabel = new Label("Last Name: ");
        // Text Field for last name
        TextField lastNameText = new TextField();
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameText, 1, 3);

        // Label for date of birth
        Label dateOfBirthLabel = new Label("Date of Birth: ");
        // Text Field for date of birth
        DatePicker dateOfBirthPicker = new DatePicker();
        gridPane.add(dateOfBirthLabel, 0, 4);
        gridPane.add(dateOfBirthPicker, 1,4);

        dateOfBirthPicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });
        dateOfBirthPicker.setEditable(false);

        // Label for country of birth
        Label countryOfBirthLabel = new Label("Country of Birth: ");
        gridPane.add(countryOfBirthLabel, 0, 5);
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
        gridPane.add(countryOfBirthDropDown, 1, 5);

        Label streetLabel = new Label("Street Name:");
        TextField streetText = new TextField();
        gridPane.add(streetLabel, 0, 6);
        gridPane.add(streetText, 1, 6);

        Label apartmentNumberLabel = new Label("Apartment number: ");
        TextField apartmentNumberText = new TextField();
        apartmentNumberText.setMaxWidth(150);
        gridPane.add(apartmentNumberLabel,0,7);
        gridPane.add(apartmentNumberText, 1, 7);


        Label stateLabel = new Label("State: ");
        ArrayList<String> listOfStates = new ArrayList<>();
        gridPane.add(stateLabel, 0, 8);

        // Read from json file
        try {
            URL url = getClass().getResource("/us_states_and_cities/us_states_and_cities.json");
            assert url != null;
            System.out.println(url.getPath());
            JSONArray data = (JSONArray) jsonParser.parse(
                    new FileReader(url.getPath()));//path to the JSON file.

            for (Object datum : data) {
                JSONObject jsonObj = (JSONObject) datum;
                listOfStates.add((String) jsonObj.get("name"));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        ChoiceBox<String> stateDropdown = new ChoiceBox<>(FXCollections.observableArrayList(listOfStates));
        stateDropdown.setPrefWidth(150);
        gridPane.add(stateDropdown, 1, 8);

        Label cityLabel = new Label("City: ");
        gridPane.add(cityLabel, 0, 9);
        ArrayList<String> listOfCities = new ArrayList<>();
        ChoiceBox<String> cityDropdown = new ChoiceBox<>(FXCollections.observableArrayList(listOfCities));
        cityDropdown.setPrefWidth(150);
        gridPane.add(cityDropdown, 1, 9);
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

        Label zipcodeLabel = new Label("Zipcode: ");
        TextField zipcodeText = new TextField();
        zipcodeText.setMaxWidth(60);
        gridPane.add(zipcodeLabel, 0, 10);
        gridPane.add(zipcodeText, 1, 10);

        // Label for reason for replacement
        Label reasonForReplacementLabel = new Label("Reason for replacement: ");

        // Populates dropdown with an acceptable list of reasons as to why the user wants to request a new Green card.
        ArrayList<String> validReasonsForReplacement = new ArrayList<>();
        validReasonsForReplacement.add("U.S. Resident card will expire soon");
        validReasonsForReplacement.add("U.S. Resident card was stolen");
        validReasonsForReplacement.add("U.S. Resident card was lost");
        validReasonsForReplacement.add("U.S. Resident card is damaged");
        ChoiceBox<String> reasonForReplacementDropdown = new ChoiceBox<>(FXCollections.observableArrayList(validReasonsForReplacement));
        gridPane.add(reasonForReplacementLabel, 0, 11);
        gridPane.add(reasonForReplacementDropdown, 1, 11);

        final AtomicBoolean submitSuccessful = new AtomicBoolean();
        // Label for submit
        Button buttonSubmit = new Button("Submit");
        gridPane.add(buttonSubmit, 1,13);

        buttonSubmit.setOnAction(actionEvent -> {
            id += 1;
            firstName = firstNameText.getText();
            middleName = middleNameText.getText();
            lastName = lastNameText.getText();
            dateOfBirth = dateOfBirthPicker.getValue();
            countryOfBirth = countryOfBirthDropDown.getValue();
            streetName = streetText.getText();
            apartmentNumber = apartmentNumberText.getText();
            city = cityDropdown.getValue();
            state = stateDropdown.getValue();
            zipcode = zipcodeText.getText();
            homeAddress = new HomeAddress(streetName, apartmentNumber, city, state,zipcode);
            reasonForReplacement = reasonForReplacementDropdown.getValue();
            //greenCardReplacementBO = new GreenCardReplacementBO(id, firstName, middleName, lastName, dateOfBirth,countryOfBirth, homeAddress,reasonForReplacement);

            if( firstName != null && lastName != null && dateOfBirth != null && countryOfBirth != null && streetName!= null && state != null && zipcode != null && reasonForReplacement != null){
                printFormContent();
                submitSuccessful.set(true);
                launchReviewScreen(stage);

            } else{
                submitSuccessful.set(false);
                System.out.print("INVALID.......");
            }

        });

        Group root = new Group();
        root.getChildren().add(gridPane);

        var scene = new Scene(root);
        stage.setTitle("Green Card Replacement Request From");
        stage.setScene(scene);
        stage.show();

        return submitSuccessful.get();
    }
    public void launchReviewScreen(final Stage primaryStage){
        GridPane reviewScreenGridPane = new GridPane();

        // Setting size for pane
        reviewScreenGridPane.setMinSize(500, 300);

        // Setting the padding
        reviewScreenGridPane.setPadding(new Insets(15, 15, 15, 15));

        // Setting vertical and horizontal gaps between the columns
        reviewScreenGridPane.setVgap(10);
        reviewScreenGridPane.setHgap(10);

        // Setting the grid alignment
        reviewScreenGridPane.setAlignment(Pos.CENTER);
        Group root = new Group();
        root.getChildren().add(reviewScreenGridPane);

        Label reviewScreen = new Label("REVIEW!!!");
        reviewScreenGridPane.add(reviewScreen, 0,0);

        Scene reviewScene = new Scene(root);
        primaryStage.setScene(reviewScene);
        primaryStage.setTitle("REVIEW");
        primaryStage.show();
    }
    @Override
    public void start(Stage primaryStage) {
        boolean b = setUpForm(primaryStage);
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
    public static void main(String[] args) {
        launch();
    }

}