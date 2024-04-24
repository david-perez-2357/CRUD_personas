package com.example.crud_personas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class AddPersonController {
    @FXML
    private TextField nameInput;
    @FXML
    private TextField surnamesInput;
    @FXML
    private Slider ageSlider;
    @FXML
    private Button addButton;
    @FXML
    private Label ageLabel;

    @FXML
    private void addPersonButton() {
        // Do validations
        if (nameInput.getText().isEmpty()) {
            nameInput.setStyle("-fx-border-color: red;");
            return;
        }

        if (surnamesInput.getText().isEmpty()) {
            surnamesInput.setStyle("-fx-border-color: red;");
            return;
        }

        // Add the person
        PeopleApp.addPerson(nameInput.getText(), surnamesInput.getText(), (int) ageSlider.getValue());

        // Empty the inputs
        nameInput.clear();
        surnamesInput.clear();
    }

    @FXML
    private void ageSliderChange() {
        ageSlider.setValue(Math.round(ageSlider.getValue()));
        ageLabel.setText(String.valueOf((int) ageSlider.getValue()));
    }
}
