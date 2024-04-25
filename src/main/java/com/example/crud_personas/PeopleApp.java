package com.example.crud_personas;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class PeopleApp extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleApp.class.getResource("People_view.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 460);
        PeopleController controller = fxmlLoader.getController(); // Get the controller
        stage.setTitle("Personas");
        stage.setScene(scene);

        // Configure the table columns
        controller.configureTableColumns();

        stage.show();
    }

    public static void addPerson(String name, String surnames, int age) {
        // Add a person to the table
        TableView tablaPersonas = (TableView) scene.lookup("#tablaPersonas");

        // Create a new person
        Person persona = new Person(name, surnames, age);

        // Get the list of people
        ObservableList<Person> listaPersonas = tablaPersonas.getItems();

        // Add the new person to the list
        listaPersonas.add(persona);
    }

    public static void editPerson(String name, String surnames, int age) {
        // Edit a person in the table
        TableView tablaPersonas = (TableView) scene.lookup("#tablaPersonas");

        // Get the selected person
        Person selectedPerson = (Person) tablaPersonas.getSelectionModel().getSelectedItem();

        // Update the person
        selectedPerson.setName(name);
        selectedPerson.setSurname(surnames);
        selectedPerson.setAge(age);

        // Refresh the table
        tablaPersonas.refresh();
    }

    public static void main(String[] args) {
        launch();
    }
}