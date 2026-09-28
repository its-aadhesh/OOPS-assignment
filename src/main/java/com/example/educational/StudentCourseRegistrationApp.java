package com.example.educational;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * StudentCourseRegistrationApp
 * 
 * An educational JavaFX application demonstrating:
 * 1. Dropdown Bar (ComboBox): Select Academic Department / Stream
 * 2. Checkboxes (CheckBox): Choose Supplementary Learning Resources / Extra Materials
 * 3. Radio Buttons (RadioButton + ToggleGroup): Select Study Mode (Online, Hybrid, In-Person)
 * 4. List (ListView): Select Elective / Core Courses based on Department
 */
public class StudentCourseRegistrationApp extends Application {

    // Data store mapping departments to course offerings
    private final Map<String, ObservableList<String>> departmentCourses = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EduPortal - Student Course Registration & Enrollment");

        // Initialize sample educational course data
        initData();

        // -------------------------------------------------------------
        // Header Section
        // -------------------------------------------------------------
        Label titleLabel = new Label("🎓 Student Course Enrollment Portal");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1A365D;");

        Label subtitleLabel = new Label("Configure your academic semester choices, delivery mode, and study materials.");
        subtitleLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #4A5568;");

        VBox headerBox = new VBox(6, titleLabel, subtitleLabel);
        headerBox.setAlignment(Pos.CENTER_LEFT);
        headerBox.setPadding(new Insets(0, 0, 10, 0));

        // -------------------------------------------------------------
        // 1. Dropdown Bar (ComboBox) - Department Selection
        // -------------------------------------------------------------
        Label deptLabel = new Label("1. Select Academic Department (Dropdown):");
        deptLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2D3748;");

        ComboBox<String> departmentDropdown = new ComboBox<>();
        departmentDropdown.getItems().addAll(
                "Computer Science & Engineering",
                "Mathematics & Statistics",
                "Physics & Applied Sciences",
                "Humanities & Social Sciences",
                "Business Administration"
        );
        departmentDropdown.setValue("Computer Science & Engineering");
        departmentDropdown.setMaxWidth(Double.MAX_VALUE);

        VBox deptBox = new VBox(6, deptLabel, departmentDropdown);

        // -------------------------------------------------------------
        // 4. List (ListView) - Available Courses
        // -------------------------------------------------------------
        Label courseListLabel = new Label("2. Available Elective Courses (ListView):");
        courseListLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2D3748;");

        ListView<String> courseListView = new ListView<>();
        courseListView.setItems(departmentCourses.get(departmentDropdown.getValue()));
        courseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        courseListView.getSelectionModel().selectFirst();
        courseListView.setPrefHeight(130);

        // Update list items dynamically when dropdown selection changes
        departmentDropdown.setOnAction(e -> {
            String selectedDept = departmentDropdown.getValue();
            ObservableList<String> courses = departmentCourses.getOrDefault(
                    selectedDept,
                    FXCollections.observableArrayList("No courses listed")
            );
            courseListView.setItems(courses);
            courseListView.getSelectionModel().selectFirst();
        });

        VBox courseBox = new VBox(6, courseListLabel, courseListView);

        // -------------------------------------------------------------
        // 3. Radio Buttons - Study Mode
        // -------------------------------------------------------------
        Label modeLabel = new Label("3. Preferred Learning Mode (Radio Buttons):");
        modeLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2D3748;");

        ToggleGroup modeToggleGroup = new ToggleGroup();

        RadioButton rbInPerson = new RadioButton("In-Person (On-Campus Lectures & Labs)");
        rbInPerson.setToggleGroup(modeToggleGroup);
        rbInPerson.setSelected(true);

        RadioButton rbHybrid = new RadioButton("Hybrid (Blended Online + Campus Sessions)");
        rbHybrid.setToggleGroup(modeToggleGroup);

        RadioButton rbOnline = new RadioButton("100% Asynchronous Online (Remote)");
        rbOnline.setToggleGroup(modeToggleGroup);

        VBox radioBox = new VBox(8, modeLabel, rbInPerson, rbHybrid, rbOnline);

        // -------------------------------------------------------------
        // 2. Checkboxes - Supplementary Educational Resources
        // -------------------------------------------------------------
        Label resourcesLabel = new Label("4. Supplementary Resources & Add-ons (Checkboxes):");
        resourcesLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2D3748;");

        CheckBox cbLibrary = new CheckBox("Access to Digital Academic Library & Research Papers");
        cbLibrary.setSelected(true);

        CheckBox cbTutoring = new CheckBox("1-on-1 Peer Tutoring & Mentorship Sessions");
        CheckBox cbLabKits = new CheckBox("Hardware/Virtual Cloud Lab Subscription Kit");
        CheckBox cbCertificate = new CheckBox("Hardcopy Verified Completion Certificate");

        VBox checkboxBox = new VBox(8, resourcesLabel, cbLibrary, cbTutoring, cbLabKits, cbCertificate);

        // -------------------------------------------------------------
        // Buttons & Summary Box
        // -------------------------------------------------------------
        Button submitBtn = new Button("Confirm Enrollment");
        submitBtn.setStyle("-fx-background-color: #2B6CB0; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 18; -fx-background-radius: 6;");

        Button resetBtn = new Button("Reset Form");
        resetBtn.setStyle("-fx-background-color: #E2E8F0; -fx-text-fill: #2D3748; -fx-padding: 8 18; -fx-background-radius: 6;");

        HBox actionButtons = new HBox(12, submitBtn, resetBtn);
        actionButtons.setAlignment(Pos.CENTER_LEFT);

        TextArea summaryArea = new TextArea();
        summaryArea.setEditable(false);
        summaryArea.setPrefRowCount(7);
        summaryArea.setPromptText("Your selected enrollment details will appear here upon confirmation...");
        summaryArea.setStyle("-fx-font-family: 'Monospaced'; -fx-font-size: 12px;");

        // Submit action
        submitBtn.setOnAction(e -> {
            String dept = departmentDropdown.getValue();
            String selectedCourse = courseListView.getSelectionModel().getSelectedItem();
            if (selectedCourse == null) {
                selectedCourse = "None selected";
            }

            RadioButton selectedRadio = (RadioButton) modeToggleGroup.getSelectedToggle();
            String mode = (selectedRadio != null) ? selectedRadio.getText() : "None";

            StringBuilder resources = new StringBuilder();
            if (cbLibrary.isSelected()) resources.append("\n   • Digital Academic Library");
            if (cbTutoring.isSelected()) resources.append("\n   • 1-on-1 Peer Mentorship");
            if (cbLabKits.isSelected()) resources.append("\n   • Cloud/Virtual Lab Access");
            if (cbCertificate.isSelected()) resources.append("\n   • Verified Certificate");
            if (resources.length() == 0) resources.append("\n   • None selected");

            String summary = "====================================================\n"
                    + "          ENROLLMENT CONFIRMATION SUMMARY           \n"
                    + "====================================================\n"
                    + "Department      : " + dept + "\n"
                    + "Course Selected : " + selectedCourse + "\n"
                    + "Study Mode      : " + mode + "\n"
                    + "Add-on Services :" + resources + "\n"
                    + "Status          : Successfully Registered ✅\n"
                    + "====================================================";

            summaryArea.setText(summary);
        });

        // Reset action
        resetBtn.setOnAction(e -> {
            departmentDropdown.setValue("Computer Science & Engineering");
            courseListView.setItems(departmentCourses.get("Computer Science & Engineering"));
            courseListView.getSelectionModel().selectFirst();
            rbInPerson.setSelected(true);
            cbLibrary.setSelected(true);
            cbTutoring.setSelected(false);
            cbLabKits.setSelected(false);
            cbCertificate.setSelected(false);
            summaryArea.clear();
        });

        // -------------------------------------------------------------
        // Layout & Assembly
        // -------------------------------------------------------------
        VBox leftColumn = new VBox(18, deptBox, courseBox);
        VBox rightColumn = new VBox(18, radioBox, checkboxBox);

        leftColumn.setPrefWidth(340);
        rightColumn.setPrefWidth(340);

        HBox formGrid = new HBox(25, leftColumn, rightColumn);

        VBox root = new VBox(16, headerBox, new Separator(), formGrid, actionButtons, summaryArea);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #F7FAFC;");

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 740, 680);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    private void initData() {
        departmentCourses.put("Computer Science & Engineering", FXCollections.observableArrayList(
                "CS101 - Object-Oriented Programming (Java)",
                "CS201 - Data Structures & Algorithms",
                "CS305 - Database Management Systems",
                "CS410 - Artificial Intelligence & Machine Learning",
                "CS450 - Cloud Computing & DevOps"
        ));

        departmentCourses.put("Mathematics & Statistics", FXCollections.observableArrayList(
                "MATH102 - Discrete Mathematics",
                "MATH204 - Linear Algebra & Matrix Theory",
                "STAT210 - Probability & Statistical Inference",
                "MATH315 - Numerical Methods & Optimization"
        ));

        departmentCourses.put("Physics & Applied Sciences", FXCollections.observableArrayList(
                "PHYS101 - Classical Mechanics",
                "PHYS205 - Electromagnetism & Optics",
                "PHYS301 - Quantum Mechanics & Relativity",
                "PHYS402 - Semiconductor Physics"
        ));

        departmentCourses.put("Humanities & Social Sciences", FXCollections.observableArrayList(
                "HUM101 - Professional Communication & Ethics",
                "HUM202 - Philosophy of Technology",
                "HUM305 - Psychology of Learning"
        ));

        departmentCourses.put("Business Administration", FXCollections.observableArrayList(
                "BUS101 - Principles of Management",
                "BUS203 - Financial Accounting & Reporting",
                "BUS310 - Marketing Strategy & Analytics"
        ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
