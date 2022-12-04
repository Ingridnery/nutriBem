package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.paciente.CPF;
import com.example.nutribem.domain.entities.paciente.IntoleranciaLactose;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.paciente.Sexo;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import com.example.nutribem.domain.usecases.utils.TextFieldFormater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.createPacienteUseCase;
import static com.example.nutribem.application.main.Main.updatePacienteUseCase;

public class PacienteUIController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtAltura;
    @FXML
    private DatePicker dtDataNasc;
    @FXML
    private TextField txtCircunferencia;
    @FXML
    private ComboBox<Sexo> cbSexo;
    @FXML
    private ComboBox<IntoleranciaLactose> cbLactose;
    @FXML
    private ComboBox<String> cbDiabetes;
    @FXML
    private TextArea txtAlergias;
    @FXML
    private TextArea txtObjetivos;
    @FXML
    private TextArea txtObservacoes;
    @FXML
    private TextArea txtHistorico;
    @FXML
    private ComboBox<String> cbGluten;
    @FXML
    private Button btnStatus;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;

    private Paciente paciente;
    private AlertMessage alert = new AlertMessage();

    @FXML
    public void initialize() {
        cbSexo.getItems().setAll(Sexo.values());
        cbLactose.getItems().setAll(IntoleranciaLactose.values());
        cbGluten.getItems().add("Sim");
        cbGluten.getItems().add("Não");
        cbDiabetes.getItems().add("Sim");
        cbDiabetes.getItems().add("Não");
        btnStatus.setVisible(false);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        try {
            getEntityToView();
            if (paciente.getId() == null)
                createPacienteUseCase.insert(paciente);
            else
                updatePacienteUseCase.update(paciente);
            WindowLoader.setRoot("MainUI");
        } catch (Exception e) {
            alert.showAlert("Erro!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void activeOrDisabled(ActionEvent actionEvent) throws IOException {
        paciente.setAtivado(!paciente.getAtivado());
        updatePacienteUseCase.update(paciente);
        WindowLoader.setRoot("MainUI");
    }

    public void getEntityToView() {
        if (paciente == null)
            paciente = new Paciente();

        paciente.setNome(txtNome.getText());
        paciente.setCpf(CPF.valueOf(txtCPF.getText()));
        paciente.setAlergias(txtAlergias.getText());
        paciente.setAltura(Integer.valueOf(txtAltura.getText()));
        paciente.setEmail(txtEmail.getText());
        paciente.setCircunferencia(Integer.valueOf(txtCircunferencia.getText()));
        paciente.setHistoricoClinicoGeral(txtHistorico.getText());
        paciente.setPeso(Double.valueOf(txtPeso.getText()));
        paciente.setObjetivos(txtObjetivos.getText());
        paciente.setTelefone(txtTelefone.getText());
        paciente.setDiabetes(cbDiabetes.getValue().equals("Sim"));
        paciente.setIntoleranciaGluten(cbGluten.getValue().equals("Sim"));
        paciente.setIntoleranciaLactose(cbLactose.getValue());
        paciente.setSexo(cbSexo.getValue());
        paciente.setObservacoesGerais(txtObservacoes.getText());
        paciente.setDataNascimento(dtDataNasc.getValue());
        paciente.setAtivado(true);
    }

    public void setEntityIntoView() {
        txtNome.setText(paciente.getNome());
        txtCPF.setText(paciente.getCpf().getCpfFormatted());
        txtAlergias.setText(paciente.getAlergias());
        txtAltura.setText(String.valueOf(paciente.getAltura()));
        txtEmail.setText(paciente.getEmail());
        txtCircunferencia.setText(String.valueOf(paciente.getCircunferencia()));
        txtHistorico.setText(paciente.getHistoricoClinicoGeral());
        txtObjetivos.setText(paciente.getObjetivos());
        txtPeso.setText(String.valueOf(paciente.getPeso()));
        txtTelefone.setText(paciente.getTelefone());
        txtObservacoes.setText(paciente.getObservacoesGerais());
        btnStatus.setVisible(true);

        if (paciente.getDiabetes())
            cbDiabetes.setValue("Sim");
        else
            cbDiabetes.setValue("Não");

        if (paciente.getIntoleranciaGluten())
            cbGluten.setValue("Sim");
        else
            cbGluten.setValue("Não");

        if (paciente.getAtivado())
            btnStatus.setText("Desativar");
        else
            btnStatus.setText("Ativar");

        cbLactose.setValue(paciente.getIntoleranciaLactose());
        cbSexo.setValue(paciente.getSexo());
        dtDataNasc.setValue(paciente.getDataNascimento());
    }

    public void setPaciente(Paciente paciente, UIMode mode) {
        Objects.requireNonNull(paciente, "Paciente não pode ser nulo!");

        this.paciente = paciente;
        setEntityIntoView();
        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnConfirm.setVisible(false);
        btnCancel.setText("Fechar");
        btnStatus.setVisible(false);

        if (paciente.getAtivado())
            btnStatus.setText("Desativar");
        else
            btnStatus.setText("Ativar");

        txtNome.setDisable(true);
        txtCPF.setDisable(true);
        txtAlergias.setDisable(true);
        txtAltura.setDisable(true);
        txtEmail.setDisable(true);
        txtCircunferencia.setDisable(true);
        txtHistorico.setDisable(true);
        txtObjetivos.setDisable(true);
        txtPeso.setDisable(true);
        txtTelefone.setDisable(true);
        cbDiabetes.setDisable(true);
        cbGluten.setDisable(true);
        cbLactose.setDisable(true);
        cbSexo.setDisable(true);
        dtDataNasc.setDisable(true);
        txtObservacoes.setDisable(true);
    }

    public void txtTelefoneKeyReleased(KeyEvent keyEvent) throws ParseException {
        TextFieldFormater textFieldFormater = new TextFieldFormater();
        textFieldFormater.setMask("(##)#####-####");
        textFieldFormater.setCaracteresValidos("0123456789");
        textFieldFormater.setTf(txtTelefone);
        textFieldFormater.formatter();
    }

    public void txtCpfKeyReleased(KeyEvent keyEvent) {
        TextFieldFormater textFieldFormater = new TextFieldFormater();
        textFieldFormater.setMask("###.###.###-##");
        textFieldFormater.setCaracteresValidos("0123456789");
        textFieldFormater.setTf(txtCPF);
        textFieldFormater.formatter();
    }
}
