package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.paciente.IntoleranciaLactose;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.paciente.Sexo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    @FXML
    public void initialize(){
        cbGluten.getItems().add("Sim");
        cbGluten.getItems().add("Não");

    }
    @FXML
    public void activeOrDesactive(ActionEvent actionEvent) throws IOException {
        alimento.setAtivado(!alimento.getAtivado());
        //updateAlimentoUseCase.update(alimento);
        WindowLoader.setRoot("AlimentoManagementUI");
    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AlimentoManagementUI");

    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {

        getEntityToView();

        if(alimento.getId() == null){
            createAlimentoUseCase.insert(alimento);
        }
        else
            updateAlimentoUseCase.update(alimento);
        WindowLoader.setRoot("AlimentoManagementUI");
    }

    public void getEntityToView(){
        if(alimento == null)
            alimento = new Alimento();

        alimento.setNome(txtNome.getText());


        alimento.setPorcao(Integer.valueOf(txtPorcao.getText()));
        alimento.setCalorias(Integer.valueOf(txtCalorias.getText()));
        alimento.setColesterol(Integer.valueOf(txtColesterol.getText()));
        alimento.setGluten(cbGluten.getValue().equals("Sim"));
        alimento.setGordurasSaturadas(Double.valueOf(txtGorduraSaturada.getText()));
        alimento.setSodio(Double.valueOf(txtSodio.getText()));
        alimento.setAcucar(Double.valueOf(txtAcucar.getText()));
        alimento.setLactose(Double.valueOf(txtLactose.getText()));

    }

    public void setEntityIntoView(){
        txtNome.setText(alimento.getNome());
        txtPorcao.setText(String.valueOf(alimento.getPorcao()));
        txtCalorias.setText(String.valueOf(alimento.getCalorias()));
        txtColesterol.setText(String.valueOf(alimento.getColesterol()));
        if(alimento.getGluten())
            cbGluten.setValue("Sim");
        else
            cbGluten.setValue("Não");
        txtGorduraSaturada.setText(String.valueOf(alimento.getGordurasSaturadas()));
        txtSodio.setText(String.valueOf(alimento.getSodio()));
        txtAcucar.setText(String.valueOf(alimento.getAcucar()));
        txtLactose.setText(String.valueOf(alimento.getLactose()));
    }

    public void configureViewMode(){
        btnConfirm.setVisible(false);
        btnCancel.setText("Fechar");
        if(alimento.getAtivado())
            btnStatus.setText("Desativar");
        else
            btnStatus.setText("Ativar");

        txtNome.setDisable(true);
        txtPorcao.setDisable(true);
        txtCalorias.setDisable(true);
        txtColesterol.setDisable(true);
        cbGluten.setDisable(true);
        txtGorduraSaturada.setDisable(true);
        txtSodio.setDisable(true);
        txtAcucar.setDisable(true);
        txtLactose.setDisable(true);
    }

    public void setAlimento(Alimento alimento, UIMode mode){
        Objects.requireNonNull(alimento,"Alimento não informado");

        this.alimento=alimento;
        setEntityIntoView();
        if(mode == UIMode.VIEW)
            configureViewMode();




    }


}
