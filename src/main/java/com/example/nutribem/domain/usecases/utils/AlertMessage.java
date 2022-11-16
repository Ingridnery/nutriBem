package com.example.nutribem.domain.usecases.utils;

public class AlertMessage {



    public void showAlert(String title, String message, javafx.scene.control.Alert.AlertType type){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
