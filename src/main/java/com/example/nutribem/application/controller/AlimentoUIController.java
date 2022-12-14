package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.*;

public class AlimentoUIController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPorcao;
    @FXML
    private TextField txtCalorias;
    @FXML
    private TextField txtColesterol;
    @FXML
    private ComboBox<String> cbGluten;
    @FXML
    private TextField txtGorduraSaturada;
    @FXML
    private TextField txtProteinas;
    @FXML
    private TextField txtSodio;
    @FXML
    private TextField txtAcucar;
    @FXML
    private TextField txtLactose;
    @FXML
    private Button btnStatus;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    private Alimento alimento;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    public void initialize() {
        cbGluten.getItems().add("Sim");
        cbGluten.getItems().add("Não");

    }

    @FXML
    public void activeOrDisabled(ActionEvent actionEvent) throws IOException {
        try{
            if(alimento.isAtivado())
                activateAlimentoUseCase.deactivate(alimento);
            else
                activateAlimentoUseCase.activate(alimento);
        } catch (Exception e){
            alert.showAlert("Erro!", e.getMessage(), Alert.AlertType.ERROR);
        }

        WindowLoader.setRoot("AlimentoManagementUI");
    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AlimentoManagementUI");

    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {

        try {
            getEntityToView();
            if (alimento.getId() == null) {
                createAlimentoUseCase.insert(alimento);
            } else {
                updateAlimentoUseCase.update(alimento);
            }
            WindowLoader.setRoot("AlimentoManagementUI");

        } catch (Exception e) {
            alert.showAlert("Erro!", "Dados inválidos!", Alert.AlertType.ERROR);
        }
    }

    public void getEntityToView() {
        if (alimento == null)
            alimento = new Alimento();

        alimento.setNome(txtNome.getText());
        alimento.setPorcao(Integer.valueOf(txtPorcao.getText()));
        alimento.setCalorias(Integer.valueOf(txtCalorias.getText()));
        alimento.setColesterol(Integer.valueOf(txtColesterol.getText()));
        alimento.setGluten(cbGluten.getValue().equals("Sim"));
        alimento.setGordurasSaturadas(Double.valueOf(txtGorduraSaturada.getText()));
        alimento.setProteinas(Double.valueOf(txtProteinas.getText()));
        alimento.setSodio(Double.valueOf(txtSodio.getText()));
        alimento.setAcucar(Double.valueOf(txtAcucar.getText()));
        alimento.setLactose(Double.valueOf(txtLactose.getText()));
        alimento.setAtivado(true);
    }

    public void setEntityIntoView() {
        txtNome.setText(alimento.getNome());
        txtPorcao.setText(String.valueOf(alimento.getPorcao()));
        txtCalorias.setText(String.valueOf(alimento.getCalorias()));
        txtColesterol.setText(String.valueOf(alimento.getColesterol()));
        if (alimento.getGluten())
            cbGluten.setValue("Sim");
        else
            cbGluten.setValue("Não");
        txtGorduraSaturada.setText(String.valueOf(alimento.getGordurasSaturadas()));
        txtProteinas.setText(String.valueOf(alimento.getProteinas()));
        txtSodio.setText(String.valueOf(alimento.getSodio()));
        txtAcucar.setText(String.valueOf(alimento.getAcucar()));
        txtLactose.setText(String.valueOf(alimento.getLactose()));
        btnStatus.setText((alimento.isAtivado()) ? "Desativar" : "Ativar");
    }

    public void configureViewMode() {

        btnConfirm.setVisible(false);
        btnCancel.setText("Fechar");
        btnStatus.setVisible(false);

        txtNome.setDisable(true);
        txtPorcao.setDisable(true);
        txtCalorias.setDisable(true);
        txtColesterol.setDisable(true);
        cbGluten.setDisable(true);
        txtGorduraSaturada.setDisable(true);
        txtProteinas.setDisable(true);
        txtSodio.setDisable(true);
        txtAcucar.setDisable(true);
        txtLactose.setDisable(true);
    }

    public void setAlimento(Alimento alimento, UIMode mode) {
        Objects.requireNonNull(alimento, "Alimento não informado");

        this.alimento = alimento;
        setEntityIntoView();
        if (mode == UIMode.VIEW)
            configureViewMode();

    }
}
