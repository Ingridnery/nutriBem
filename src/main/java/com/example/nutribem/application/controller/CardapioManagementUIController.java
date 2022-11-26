package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
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
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.*;

public class CardapioManagementUIController {

    @FXML
    private TableView<Cardapio> tableView;
    @FXML
    private TextField txtNumeroDia;
    @FXML
    private TableColumn<Cardapio,Integer> cId;
    @FXML
    private TableColumn<Cardapio,Integer> cNumeroDia;

    private ObservableList<Cardapio> tableData;
    private List<Cardapio> cardapioList;

    private Cardapio cardapio;
    private PlanoNutricional planoNutricional;
    private final AlertMessage alert = new AlertMessage();

    private void bindColumnsToValueSources(){
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNumeroDia.setCellValueFactory(new PropertyValueFactory<>("numeroDia"));

    }
    private void bindTableViewToItemsList(){
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }
    private void loadDataAndShow(){
        tableData.clear();
        tableData.addAll(cardapioList);
    }



    public void getSelectedAndSetButton(MouseEvent mouseEvent) {
        cardapio = tableView.getSelectionModel().getSelectedItem();
    }

    public void createCardapio(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CardapioUI");
        CardapioUIController controller = (CardapioUIController) WindowLoader.getController();
        controller.setPlanoNutricional(planoNutricional);
    }

    public void updateCardapio(ActionEvent actionEvent) throws IOException {
        showCardapioInMode(UIMode.UPDATE);
    }

    public void removeCardapio(ActionEvent actionEvent) {
        if(cardapio!= null) {
            removeCardapioUseCase.delete(cardapio);
            setCardapioFromPlanoNutricional(planoNutricional);
        }
        else
            setMessageCardapio();
    }

    public void detailCardapio(ActionEvent actionEvent) throws IOException {
        showCardapioInMode(UIMode.VIEW);

    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PlanoNutricionalManagementUI");
        PlanoNutricionalManagementUIController controller = (PlanoNutricionalManagementUIController) WindowLoader.getController();
        controller.setPlanoNutricionalFromPaciente(planoNutricional.getPaciente());
    }

    private void setMessageCardapio(){
        String title = "Cardapio invalido!";
        String message = "Nenhum cardapio foi selecionado!";
        alert.showAlert(title, message, Alert.AlertType.ERROR);
    }
    public void setCardapioFromPlanoNutricional(PlanoNutricional planoNutricional){

        Objects.requireNonNull(planoNutricional,"Plano nutricional não pode ser nulo!");
        cardapioList = findCardapioUseCase.findByPlanoNutricional(planoNutricional.getId());
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        this.planoNutricional = planoNutricional;
    }
    private void showCardapioInMode(UIMode mode) throws IOException {
        if(cardapio!=null){
            WindowLoader.setRoot("CardapioUI");
            CardapioUIController controller = (CardapioUIController) WindowLoader.getController();
            controller.setCardapio(cardapio,mode);
            controller.setPlanoNutricional(planoNutricional);
        }
        else
            setMessageCardapio();
    }

    public void refeicaoUI(ActionEvent actionEvent) throws IOException {
        if(cardapio!= null){
            WindowLoader.setRoot("RefeicaoManagementUI");
            RefeicaoManagementUIController controller = (RefeicaoManagementUIController) WindowLoader.getController();
            controller.setRefeicaoFromCardapio(cardapio);
        }else
            setMessageCardapio();

     }

    public void findByNumeroDia(ActionEvent actionEvent) {
        tableData.clear();
        try{
            Integer txtSearch = Integer.valueOf(txtNumeroDia.getText());
            List<Cardapio> cardapios = findCardapioByNumeroDiaUseCase.findByNumeroDia(txtSearch);
            List<Cardapio> matchesWithSearch = cardapios.stream()
                    .filter(cardapio -> cardapio.getNumeroDia().equals(txtSearch) && cardapio.getPlanoNutricional().getId().equals(planoNutricional.getId()) )
                    .toList();
            tableData.addAll(matchesWithSearch);
        }catch (Exception e){
            alert.showAlert("Nenhum cardapio encontrado","Nenhum cardapio foi encontrado!", Alert.AlertType.INFORMATION);
        }


    }
}
