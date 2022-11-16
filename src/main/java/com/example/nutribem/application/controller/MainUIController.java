package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import static com.example.nutribem.application.main.Main.findPacienteUseCase;
import static com.example.nutribem.application.main.Main.removePacienteUseCase;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class MainUIController {

    @FXML
    private TableView<Paciente> tableView;
    @FXML
    private TableColumn<Paciente,Integer> cId;
    @FXML
    private TableColumn<Paciente,String> cName;
    @FXML
    private TableColumn<Paciente, String> cCPF;
    @FXML
    private TextField txtNamePaciente;
    @FXML
    private Button btnFindPaciente;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnAlimento;

    private ObservableList<Paciente> tableData;
    private Paciente paciente;
    private final AlertMessage alert = new AlertMessage();


    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    @FXML
    public void handle(KeyEvent key){
        String txtSearch = (txtNamePaciente.getText() + key.getText()).toUpperCase();
        List<Paciente> pacienteList = findPacienteUseCase.findAll();
        List<Paciente> matchesWithSearch = pacienteList.stream()
                .filter(paciente -> paciente.getNome().toUpperCase().startsWith(txtSearch))
                .toList();
        tableData.clear();
        tableData.addAll(matchesWithSearch);
    }


    private void bindColumnsToValueSources(){
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cCPF.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCpf().getCpfFormatted()));

    }
    private void bindTableViewToItemsList(){
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }
    private void loadDataAndShow(){
        List<Paciente> pacienteList = findPacienteUseCase.findAll();
        tableData.clear();
        tableData.addAll(pacienteList);
    }

    public void createPaciente(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PacienteUI");
    }

    public void updatePaciente(ActionEvent actionEvent) throws IOException {
        showPacienteInMode(UIMode.UPDATE);
    }

    public void removePaciente(ActionEvent actionEvent) {
        if(paciente!=null){
            removePacienteUseCase.remove(paciente);
            loadDataAndShow();
        }
        else
            setMessagePaciente();

    }

    public void detailPaciente(ActionEvent actionEvent) throws IOException {
        showPacienteInMode(UIMode.VIEW);
    }

    private void setMessagePaciente(){
        String title = "Paciente invalido.";
        String message = "Nenhum paciente foi selecionado!";
        alert.showAlert(title, message, Alert.AlertType.ERROR);
    }


    private void showPacienteInMode(UIMode mode) throws IOException {
        if(paciente !=null){
            WindowLoader.setRoot("PacienteUI");
            PacienteUIController controller = (PacienteUIController) WindowLoader.getController();
            controller.setPaciente(paciente,mode);
        }
        else
            setMessagePaciente();

    }

    public void getSelectedAndSetButton(MouseEvent mouseEvent) {
        paciente= tableView.getSelectionModel().getSelectedItem();
    }

    public void alimentoUI(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AlimentoManagementUI");

    }

    public void planoNutricionalUI(ActionEvent actionEvent) throws IOException {
        if(paciente!=null){
            WindowLoader.setRoot("PlanoNutricionalManagementUI");
            PlanoNutricionalManagementUIController controller = (PlanoNutricionalManagementUIController) WindowLoader.getController();
            controller.setPlanoNutricional(paciente);
        }
        else
            setMessagePaciente();

    }


}
