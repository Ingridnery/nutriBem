module com.example.nutribem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;

    opens com.example.nutribem to javafx.fxml;
    opens com.example.nutribem.domain.entities.paciente to javafx.fxml;
    exports com.example.nutribem;
    exports com.example.nutribem.domain.entities.paciente;
    exports com.example.nutribem.application.controller;
    opens com.example.nutribem.application.controller to javafx.fxml;
}