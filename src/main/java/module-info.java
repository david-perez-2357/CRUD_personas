module com.example.crud_personas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.crud_personas to javafx.fxml;
    exports com.example.crud_personas;
}