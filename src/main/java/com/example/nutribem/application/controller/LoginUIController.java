package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.nutribem.application.main.Main.loginUseCase;
import static com.example.nutribem.application.main.Main.recuperaSenhaUseCase;

public class LoginUIController {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtSenha;

    private final AlertMessage alert = new AlertMessage();

    public void login(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            alert.showAlert("Erro!", "Dados invalidos!", Alert.AlertType.ERROR);
            return;
        }

        try {
            loginUseCase.login(txtUserName.getText(), txtSenha.getText());
            WindowLoader.setRoot("MainUI");
        } catch (Exception e) {
            alert.showAlert("Erro!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void findDicas(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty()) {
            alert.showAlert("Erro!", "Nome de usuario não preenchido", Alert.AlertType.ERROR);
            return;
        }
        try {
            ArrayList<String> dicasSenha = recuperaSenhaUseCase.dicasSenha(txtUserName.getText());
            alert.showAlert("Dicas de senha", dicasSenha.get(0) + " " + dicasSenha.get(1) + " " + dicasSenha.get(2), Alert.AlertType.INFORMATION);
        } catch (EntityNotFoundException e) {
            alert.showAlert("Erro!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
