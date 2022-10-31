module com.example.nutribem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;

    opens com.example.nutribem to javafx.fxml;
    exports com.example.nutribem;
}