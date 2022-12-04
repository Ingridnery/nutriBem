package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.createPlanoNutricionalUseCase;
import static com.example.nutribem.application.main.Main.updatePlanoNutricionalUseCase;

public class PlanoNutricionalUIController {

    @FXML
    private TextField txtName;
    @FXML
    private DatePicker dpStartDate;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;

    private PlanoNutricional planoNutricional;
    private Paciente paciente;
    private AlertMessage alert = new AlertMessage();

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PlanoNutricionalManagementUI");

        PlanoNutricionalManagementUIController controller = (PlanoNutricionalManagementUIController) WindowLoader.getController();
        controller.setPlanoNutricionalFromPaciente(paciente);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        try {
            getEntityToView();
            if (planoNutricional.getId() == null)
                createPlanoNutricionalUseCase.insert(planoNutricional);
            else
                updatePlanoNutricionalUseCase.update(planoNutricional);

            WindowLoader.setRoot("PlanoNutricionalManagementUI");
            PlanoNutricionalManagementUIController controller = (PlanoNutricionalManagementUIController) WindowLoader.getController();
            controller.setPlanoNutricionalFromPaciente(paciente);
        } catch (Exception e) {
            alert.showAlert("Erro!", "Dados inválidos!", Alert.AlertType.ERROR);
        }
    }

    public void getEntityToView() {
        if (planoNutricional == null)
            planoNutricional = new PlanoNutricional();

        planoNutricional.setNome(txtName.getText());
        planoNutricional.setDataInicio(dpStartDate.getValue());
        planoNutricional.setDataFim(dpEndDate.getValue());
        planoNutricional.setPaciente(paciente);
    }

    public void setEntityIntoView() {
        txtName.setText(planoNutricional.getNome());
        dpStartDate.setValue(planoNutricional.getDataInicio());
        dpEndDate.setValue(planoNutricional.getDataFim());
    }

    public void setPlanoNutricional(PlanoNutricional planoNutricional, UIMode mode) {
        Objects.requireNonNull(planoNutricional, "Plano nutricional não pode ser nulo!");

        this.planoNutricional = planoNutricional;
        setEntityIntoView();
        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    private void configureViewMode() {
        btnConfirm.setVisible(false);
        btnCancel.setText("Fechar");

        txtName.setDisable(true);
        dpStartDate.setDisable(true);
        dpEndDate.setDisable(true);
    }
}
