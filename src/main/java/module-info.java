module com.example.nutribem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nutribem to javafx.fxml;
    exports com.example.nutribem;
}