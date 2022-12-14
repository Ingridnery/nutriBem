module com.example.nutribem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires org.apache.commons.text;
    requires itextpdf;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.nutribem to javafx.fxml;
    opens com.example.nutribem.domain.entities.paciente to javafx.fxml;
    opens com.example.nutribem.domain.entities.alimento to javafx.fxml;
    opens com.example.nutribem.domain.entities.planoNutricional to javafx.fxml;
    opens com.example.nutribem.domain.entities.cardapio to javafx.fxml;
    opens com.example.nutribem.domain.entities.refeicao to javafx.fxml;
    opens com.example.nutribem.application.main to javafx.fxml;
    exports com.example.nutribem;
    exports com.example.nutribem.domain.entities.refeicao;
    exports com.example.nutribem.domain.entities.cardapio;
    exports com.example.nutribem.domain.entities.planoNutricional;
    exports com.example.nutribem.domain.entities.alimento;
    exports com.example.nutribem.domain.entities.paciente;
    exports com.example.nutribem.application.controller;
    opens com.example.nutribem.application.controller to javafx.fxml;
}