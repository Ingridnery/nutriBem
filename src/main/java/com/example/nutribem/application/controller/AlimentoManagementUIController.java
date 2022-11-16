package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.findAlimentoUseCase;
import static com.example.nutribem.application.main.Main.removeAlimentoUseCase;

public class AlimentoManagementUIController {

    @FXML
    private TableView<Alimento> tableView;
    @FXML
    private TableColumn<Alimento,Integer> cId;
    @FXML
    private TableColumn<Alimento,String> cName;
    private ObservableList<Alimento> tableData;
    private Alimento alimento;

    private final AlertMessage alert = new AlertMessage();



    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }
    private void bindColumnsToValueSources(){
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }
    private void bindTableViewToItemsList(){
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }
    private void loadDataAndShow(){
        List<Alimento> alimentoList = findAlimentoUseCase.findAll();
        tableData.clear();
        tableData.addAll(alimentoList);
    }
    public void getSelectedAndSetButton(MouseEvent mouseEvent) {
        alimento = tableView.getSelectionModel().getSelectedItem();

    }

    public void createAlimento(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AlimentoUI");
    }

    public void updateAlimento(ActionEvent actionEvent) throws IOException {
        showAlimentoInMode(UIMode.UPDATE);

    }

    public void removeAlimento(ActionEvent actionEvent) {
        if(alimento != null){
            removeAlimentoUseCase.remove(alimento);
            loadDataAndShow();
        }
        else
            setMessageAlimento();

    }

    public void detailAlimento(ActionEvent actionEvent) throws IOException {
        showAlimentoInMode(UIMode.VIEW);
    }

    private void setMessageAlimento(){
        String title = "Alimento invalido.";
        String message = "Nenhum alimento foi selecionado!";
        alert.showAlert(title, message, Alert.AlertType.ERROR);
    }
    private void showAlimentoInMode(UIMode mode) throws IOException {
        if(alimento !=null){
            WindowLoader.setRoot("AlimentoUI");
            AlimentoUIController controller = (AlimentoUIController) WindowLoader.getController();
            controller.setAlimento(alimento,mode);

        }
        else
            setMessageAlimento();
    }

    public void findAlimentoByName(ActionEvent actionEvent) {
    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
