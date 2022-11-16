package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.*;

public class PlanoNutricionalManagementUIController {
    @FXML
    private TableView<PlanoNutricional> tableView;
    @FXML
    private TableColumn<PlanoNutricional,Integer> cId;
    @FXML
    private TableColumn<PlanoNutricional,String> cName;

    private ObservableList<PlanoNutricional> tableData;
    private PlanoNutricional planoNutricional;
    private Paciente paciente;
    private List<PlanoNutricional> planoNutricionalList;
    private final AlertMessage alert = new AlertMessage();


    private void bindColumnsToValueSources(){
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }
    private void bindTableViewToItemsList(){
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }
    private void loadDataAndShow(){

        tableData.clear();
        tableData.addAll(planoNutricionalList);
    }
    public void findPlanoByName(ActionEvent actionEvent) {
    }

    public void getSelectedAndSetButton(MouseEvent mouseEvent) {
        planoNutricional=tableView.getSelectionModel().getSelectedItem();
    }

    public void createPlanoNutricional(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PlanoNutricionalUI");
        PlanoNutricionalUIController controller = (PlanoNutricionalUIController) WindowLoader.getController();
        controller.setPaciente(paciente);
    }

    public void updatePlanoNutricional(ActionEvent actionEvent) throws IOException {
        showPlanoNutricionalInMode(UIMode.UPDATE);
    }

    public void removePlanoNutricional(ActionEvent actionEvent) {
        if(planoNutricional!= null) {
            removePlanoNutricionalUseCase.remove(planoNutricional);
            loadDataAndShow();
        }
        else
            setMessagePlanoNutricional();
    }

    public void detailPlanoNutricional(ActionEvent actionEvent) throws IOException {
        showPlanoNutricionalInMode(UIMode.VIEW);
    }
    private void setMessagePlanoNutricional(){
        String title = "Plano nutricional invalido!";
        String message = "Nenhum plano nutricional foi selecionado!";
        alert.showAlert(title, message, Alert.AlertType.ERROR);
    }
    private void showPlanoNutricionalInMode(UIMode mode) throws IOException {
        if(planoNutricional!=null){
            WindowLoader.setRoot("PlanoNutricionalUI");
            PlanoNutricionalUIController controller = (PlanoNutricionalUIController) WindowLoader.getController();
            controller.setPlanoNutricional(planoNutricional,mode);
        }
        else
            setMessagePlanoNutricional();


    }
    public void setPlanoNutricionalFromPaciente(Paciente paciente){

        Objects.requireNonNull(paciente,"Paciente não pode ser nulo!");
        planoNutricionalList= findPlanoNutricionalUseCase.findByPaciente(paciente.getId());
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        this.paciente=paciente;
    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void cardapioUI(ActionEvent actionEvent) {
    }
}
