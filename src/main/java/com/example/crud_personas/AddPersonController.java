package com.example.crud_personas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

public class AddPersonController {
    @FXML
    public Label title;
    @FXML
    public TextField nameInput;
    @FXML
    public TextField surnamesInput;
    @FXML
    public Slider ageSlider;
    @FXML
    public Button addButton;
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

        String message = "";

        if (Objects.equals(addButton.getText(), "Añadir")) {
            // Add the person
            PeopleApp.addPerson(nameInput.getText(), surnamesInput.getText(), (int) ageSlider.getValue());
            message = "Persona añadida correctamente";

            // Empty the inputs
            nameInput.clear();
            surnamesInput.clear();
        } else {
            // Edit the person
            PeopleApp.editPerson(nameInput.getText(), surnamesInput.getText(), (int) ageSlider.getValue());

            // Close the window
            ((Stage) addButton.getScene().getWindow()).close();

            message = "Persona editada correctamente";
        }

        // Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void ageSliderChange() {
        ageSlider.setValue(Math.round(ageSlider.getValue()));
        ageLabel.setText(String.valueOf((int) ageSlider.getValue()));
    }
}
