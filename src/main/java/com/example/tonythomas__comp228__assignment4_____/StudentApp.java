package com.example.tonythomas__comp228__assignment4_____;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentApp extends Application {

    //  UI Components is being defined here//
    private TextField fullNameField, addressField, cityField, provinceField, postalCodeField, phoneField, emailField;
    private RadioButton compSciRadio, businessRadio;
    private ComboBox<String> coursesComboBox;
    private ListView<String> coursesListView;
    private CheckBox sportsCheckBox, artsCheckBox, volunteeringCheckBox;
    private TextArea displayTextArea;

    @Override
    public void start(Stage primaryStage) {
        //  the UI components Initialized here//
        initializeComponents();

        // Layout Setup: Use BorderPane as the main layout//
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create and set the form section //
        GridPane formPane = createFormPane();
        root.setTop(formPane);

        // Create and set the left section //
        VBox majorCoursesBox = createMajorCoursesBox();
        root.setLeft(majorCoursesBox);

        // Create and set the right section//
        VBox additionalInfoBox = createAdditionalInfoBox();
        root.setRight(additionalInfoBox);

        // button is added to display the student information//
        Button displayButton = new Button("Display Student Information");
        displayButton.setOnAction(e -> displayStudentInfo());
        root.setBottom(displayButton);

        // Set the Scene and Show Stage//
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Enter student information");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Step 3.1: Initialize all UI components//
    private void initializeComponents() {
        fullNameField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        provinceField = new TextField();
        postalCodeField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();

        compSciRadio = new RadioButton("Computer Science");
        businessRadio = new RadioButton("Business");

        ToggleGroup majorGroup = new ToggleGroup();
        compSciRadio.setToggleGroup(majorGroup);
        businessRadio.setToggleGroup(majorGroup);

        coursesComboBox = new ComboBox<>();
        coursesListView = new ListView<>();

        sportsCheckBox = new CheckBox("Sports");
        artsCheckBox = new CheckBox("Arts");
        volunteeringCheckBox = new CheckBox("Volunteering");

        displayTextArea = new TextArea();
        displayTextArea.setEditable(false);

        // Event handlers for major selection and course addition
        compSciRadio.setOnAction(e -> loadCourses("Computer Science"));
        businessRadio.setOnAction(e -> loadCourses("Business"));
        coursesComboBox.setOnAction(e -> addCourseToList());
    }

    // Step 3.2: Create the form section for text fields
    private GridPane createFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Full Name:"), 0, 0);
        gridPane.add(fullNameField, 1, 0);
        gridPane.add(new Label("Address:"), 0, 1);
        gridPane.add(addressField, 1, 1);
        gridPane.add(new Label("City:"), 0, 2);
        gridPane.add(cityField, 1, 2);
        gridPane.add(new Label("Province:"), 0, 3);
        gridPane.add(provinceField, 1, 3);
        gridPane.add(new Label("Postal Code:"), 0, 4);
        gridPane.add(postalCodeField, 1, 4);
        gridPane.add(new Label("Phone Number:"), 0, 5);
        gridPane.add(phoneField, 1, 5);
        gridPane.add(new Label("Email:"), 0, 6);
        gridPane.add(emailField, 1, 6);

        return gridPane;
    }

    // Create section for major and course selection//
    private VBox createMajorCoursesBox() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(new Label("Select Major:"), compSciRadio, businessRadio, new Label("Select Course:"), coursesComboBox, coursesListView);
        return vbox;
    }

    // Create section for additional info and display area//
    private VBox createAdditionalInfoBox() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(new Label("Additional Information:"), sportsCheckBox, artsCheckBox, volunteeringCheckBox, new Label("Student Information Display:"), displayTextArea);
        return vbox;
    }

    //  Load courses based on the selected major//
    private void loadCourses(String major) {
        coursesComboBox.getItems().clear();
        if (major.equals("Computer Science")) {
            coursesComboBox.getItems().addAll("Database", "Linux", "Sub Systems", "Operating Systems");
        } else if (major.equals("Business")) {
            coursesComboBox.getItems().addAll("Accounting", "Marketing", "Finance", "Management");
        }
    }


    //  Add selected course to the list view //
    private void addCourseToList() {
        String selectedCourse = coursesComboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null && !coursesListView.getItems().contains(selectedCourse)) {
            coursesListView.getItems().add(selectedCourse);
        }
    }

    //  Display all student information in the text area//
    private void displayStudentInfo() {
        StringBuilder info = new StringBuilder();

        info.append("Full Name: ").append(fullNameField.getText()).append("\n");
        info.append("Address: ").append(addressField.getText()).append("\n");
        info.append("City: ").append(cityField.getText()).append("\n");
        info.append("Province: ").append(provinceField.getText()).append("\n");
        info.append("Postal Code: ").append(postalCodeField.getText()).append("\n");
        info.append("Phone: ").append(phoneField.getText()).append("\n");
        info.append("Email: ").append(emailField.getText()).append("\n");

        info.append("Major: ").append(compSciRadio.isSelected() ? "Computer Science" : "Business").append("\n");
        info.append("Courses: ").append(String.join(", ", coursesListView.getItems())).append("\n");

        info.append("Additional Info: ");
        if (sportsCheckBox.isSelected()) info.append("Sports ");
        if (artsCheckBox.isSelected()) info.append("Arts ");
        if (volunteeringCheckBox.isSelected()) info.append("Volunteering ");
        info.append("\n");

        displayTextArea.setText(info.toString());
    }
}

//Program ends//