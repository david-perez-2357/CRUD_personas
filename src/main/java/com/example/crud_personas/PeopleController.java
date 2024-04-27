package com.example.crud_personas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class PeopleController {
    @FXML
    private TextField searchInput;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    public TableView<Person> peopleTable;

    @FXML
    public TableColumn<Person, String> columnName;

    @FXML
    public TableColumn<Person, String> columnSurname;

    @FXML
    public TableColumn<Person, Integer> columnAge;


    private ObservableList<Person> peopleRemoved = FXCollections.observableArrayList();


    public void configureTableColumns() {
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnSurname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        columnAge.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
    }

    @FXML
    private void showAddPersonModal() {
        try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPerson.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Añadir persona");

            // Crear la escena y establecerla en el nuevo escenario
            Scene scene = new Scene(fxmlLoader.load(), 250, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void deletePerson() {
        // Get the selected person
        Person selectedPerson = peopleTable.getSelectionModel().getSelectedItem();

        // Remove the selected person from the table
        peopleTable.getItems().remove(selectedPerson);

        // Disable the delete and edit buttons
        deleteButton.setDisable(true);
        editButton.setDisable(true);

        // Show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Persona eliminada correctamente");
        alert.showAndWait();
    }

    @FXML
    private void editPerson() {
        // Get the selected person
        Person selectedPerson = peopleTable.getSelectionModel().getSelectedItem();

        try {
            // Load the FXML file of the new window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPerson.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Editar persona");

            // Create the scene and set it in the new stage
            Scene scene = new Scene(fxmlLoader.load(), 250, 400);

            // Get the controller
            AddPersonController controller = fxmlLoader.getController();

            // Set the selected person data in the inputs
            controller.nameInput.setText(selectedPerson.getName());
            controller.surnamesInput.setText(selectedPerson.getSurname());
            controller.ageSlider.setValue(selectedPerson.getAge());

            controller.title.setText("Editar persona");
            controller.addButton.setText("Editar");

            controller.ageSliderChange();

            // Set the selected person in the controller
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void selectPerson() {
        // Get the selected person
        Person selectedPerson = peopleTable.getSelectionModel().getSelectedItem();

        // Check if the selected person is null
        if (selectedPerson == null) {
            // Disable the delete and edit buttons
            deleteButton.setDisable(true);
            editButton.setDisable(true);
            return;
        }

        // Enable the delete and edit buttons
        deleteButton.setDisable(false);
        editButton.setDisable(false);
    }

    @FXML
    private void searchByName() {
        // Get the search input
        String search = searchInput.getText().toLowerCase();

        // Filter the items based on the search criteria
        ObservableList<Person> filteredList = FXCollections.observableArrayList();

        // Add the removed people back to the list
        ObservableList<Person> peopleList = peopleTable.getItems();
        peopleList.addAll(peopleRemoved);
        peopleRemoved.clear();

        for (Person person : peopleTable.getItems()) {
            // Check if the name contains the search input
            if (person.getName().toLowerCase().contains(search)) {
                filteredList.add(person);
            }else {
                peopleRemoved.add(person);
            }
        }

        // Clear the current items and add the filtered ones
        peopleTable.getItems().clear();
        peopleTable.getItems().addAll(filteredList);
    }
}