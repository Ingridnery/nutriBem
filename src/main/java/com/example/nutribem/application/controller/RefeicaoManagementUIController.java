package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.entities.refeicao.RefeicaoCategoria;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.*;

public class RefeicaoManagementUIController {
    @FXML
    private TableView<Refeicao> tableView;
    @FXML
    private TableColumn<Refeicao,Integer> cId;
    @FXML
    private TableColumn<Refeicao, RefeicaoCategoria> cCategoria;

    @FXML
    private TextField txtCategoriaRefeicao;

    private ObservableList<Refeicao> tableData;
    private Refeicao refeicao;
    private Cardapio cardapio;
    private List<Refeicao> refeicaoList;
    private final AlertMessage alert = new AlertMessage();

    private void bindColumnsToValueSources(){
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

    }
    private void bindTableViewToItemsList(){
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }
    private void loadDataAndShow(){

        tableData.clear();
        tableData.addAll(refeicaoList);
    }
    public void getSelectedAndSetButton(MouseEvent mouseEvent) {
        refeicao = tableView.getSelectionModel().getSelectedItem();
    }

    public void createRefeicao(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("RefeicaoUI");
        RefeicaoUIController controller = (RefeicaoUIController)  WindowLoader.getController();
        controller.setCardapio(cardapio);
    }

    public void updateRefeicao(ActionEvent actionEvent) throws IOException {
        showRefeicaoInMode(UIMode.UPDATE);
    }

    public void removeRefeicao(ActionEvent actionEvent) {
        if(refeicao!= null) {
            removeRefeicaoUseCase.remove(refeicao);
            loadDataAndShow();
        }
        else
            setMessageRefeicao();
    }

    public void detailRefeicao(ActionEvent actionEvent) throws IOException {
        showRefeicaoInMode(UIMode.VIEW);
    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CardapioManagementUI");
        CardapioManagementUIController controller = (CardapioManagementUIController) WindowLoader.getController();
        controller.setCardapioFromPlanoNutricional(cardapio.getPlanoNutricional());
    }



    private void showRefeicaoInMode(UIMode mode) throws IOException {
        if(refeicao!=null){
            WindowLoader.setRoot("RefeicaoUI");
            RefeicaoUIController controller = (RefeicaoUIController) WindowLoader.getController();
            controller.setRefeicao(refeicao,mode);
            controller.setCardapio(cardapio);
        }
        else
            setMessageRefeicao();


    }

    private void setMessageRefeicao() {
        String title = "Refeição inválida!";
        String message = "Nenhuma refeição foi selecionada!";
        alert.showAlert(title, message, Alert.AlertType.ERROR);
    }


    public void setRefeicaoFromCardapio(Cardapio cardapio){

        Objects.requireNonNull(cardapio,"Paciente não pode ser nulo!");
        refeicaoList = findRefeicaoUseCase.findByCardapio(cardapio);
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        this.cardapio=cardapio;
    }

    public void handle(KeyEvent key) {

        String txtSearch = txtCategoriaRefeicao.getText();
        List<Refeicao> refeicoes = findRefeicaoUseCase.findAll();
        List<Refeicao> matchesWithSearch = refeicoes.stream()
                .filter(refeicao -> refeicao.getCategoria().toString().startsWith(txtSearch) &&
                        refeicao.getCardapio().getId().equals(cardapio.getId()))
                .toList();
        tableData.clear();
        tableData.addAll(matchesWithSearch);

    }

    public void addAlimento(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("RefeicaoAlimentoUI");

    }
}
