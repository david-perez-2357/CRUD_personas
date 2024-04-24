package com.example.crud_personas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;

public class PeopleController {
    @FXML
    private Button addButton;

    @FXML
    public TableView<Person> tablaPersonas;

    @FXML
    public TableColumn<Person, String> columnName;

    @FXML
    public TableColumn<Person, String> columnSurname;

    @FXML
    public TableColumn<Person, Integer> columnAge;


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

}