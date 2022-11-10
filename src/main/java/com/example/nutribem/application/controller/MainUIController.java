package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.paciente.Paciente;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import static com.example.nutribem.application.main.Main.findPacienteUseCase;
import static com.example.nutribem.application.main.Main.removePacienteUseCase;

import java.io.IOException;
import java.util.List;


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
    private Button btnFind;
    @FXML
    private Button btnRemove;

    private ObservableList<Paciente> tableData;
    private Paciente paciente;


    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
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
    public void findPacienteByName(ActionEvent actionEvent) {

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
    }

    public void detailPaciente(ActionEvent actionEvent) throws IOException {
        showPacienteInMode(UIMode.VIEW);
    }

    private void showPacienteInMode(UIMode mode) throws IOException {
        if(paciente !=null){
            WindowLoader.setRoot("PacienteUI");
            PacienteUIController controller = (PacienteUIController) WindowLoader.getController();
            controller.setPaciente(paciente,mode);
        }

    }

    public void getSelectedAndSetButton(MouseEvent mouseEvent) {
        paciente= tableView.getSelectionModel().getSelectedItem();
    }
}
