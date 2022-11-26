package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.entities.refeicao.RefeicaoCategoria;
import com.example.nutribem.domain.usecases.alimento.FindAlimentoUseCase;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import com.example.nutribem.domain.usecases.utils.TextFieldFormater;
import com.example.nutribem.domain.usecases.valoresNutricionais.ValoresNutricionais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.nutribem.application.main.Main.*;

public class RefeicaoUIController {

    @FXML
    private TableView<Alimento> addedAlimentosTableView;

    @FXML
    private TableView<Alimento> allAlimentosTableView;

    @FXML
    private Button btnAddToRefeicao;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConfirm;

    @FXML
    private TableColumn<Alimento, Integer> cAddedId;

    @FXML
    private TableColumn<Alimento, String> cAddedName;

    @FXML
    private TableColumn<Alimento, Integer> cAllId;

    @FXML
    private TableColumn<Alimento, String> cAllName;

    @FXML
    private ComboBox<RefeicaoCategoria> cbCategoria;

    @FXML
    private Label txtAcucar;

    @FXML
    private TextField txtBusca;

    @FXML
    private Label txtCalorias;

    @FXML
    private Label txtGluten;

    @FXML
    private Label txtGorduras;

    @FXML
    private TextField txtHorario;

    @FXML
    private Label txtLactose;

    @FXML
    private Label txtSodio;
    @FXML
    private Button btnRemover;
    @FXML
    private Label lLupa;

    private Refeicao refeicao;
    private Cardapio cardapio;
    private List<Alimento> alimentoList = new ArrayList<>();
    private List<Alimento> alimentosAdded = new ArrayList<>();
    private final AlertMessage alert = new AlertMessage();
    private ObservableList<Alimento> allAlimentosData;
    private ObservableList<Alimento> addedAlimentosData;
    private ValoresNutricionais totalValoresNutricionais;
    private UIMode mode;

    @FXML
    void addToRefeicao(ActionEvent event) {
        Alimento alimento = allAlimentosTableView.getSelectionModel().getSelectedItem();
        if(alimento == null){
            alert.showAlert("Alimento inválido", "Nenhum alimento foi selecionado", Alert.AlertType.ERROR);
            return;
        }
        alimentosAdded.add(alimento);
        addedAlimentosData.add(alimento);
        allAlimentosData.remove(alimento);

        ValoresNutricionais valoresNutricionais = alimento.calculateValoresNutricionais();
        totalValoresNutricionais.somar(valoresNutricionais);
        updateValoresNutricionais();
    }

    @FXML
    public void removeFromRefeicao(ActionEvent actionEvent) {
        Alimento alimento = addedAlimentosTableView.getSelectionModel().getSelectedItem();
        if(alimento == null){
            alert.showAlert("Alimento inválido", "Nenhum alimento foi selecionado", Alert.AlertType.ERROR);
            return;
        }
        alimentosAdded.remove(alimento);
        allAlimentosData.add(alimento);
        addedAlimentosData.remove(alimento);

        ValoresNutricionais valoresNutricionais = alimento.calculateValoresNutricionais();
        totalValoresNutricionais.subtrair(valoresNutricionais);
        updateValoresNutricionais();
    }

    private void updateValoresNutricionais(){
        txtCalorias.setText(totalValoresNutricionais.getCalorias() + " kcal");
        txtGluten.setText(totalValoresNutricionais.getGluten() ? "SIM" : "NÃO");
        txtLactose.setText(totalValoresNutricionais.getLactose() + " g");
        txtAcucar.setText(totalValoresNutricionais.getAcucar() + " g");
        txtSodio.setText(totalValoresNutricionais.getSodio() + " g");
        txtGorduras.setText(totalValoresNutricionais.getGordurasSaturadas() + " g");
    }

    private void bindColumnsToValueSources(){
        cAllId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cAllName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cAddedId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cAddedName.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }
    private void bindTableViewToItemsList(){
        allAlimentosData = FXCollections.observableArrayList();
        addedAlimentosData = FXCollections.observableArrayList();
        allAlimentosTableView.setItems(allAlimentosData);
        addedAlimentosTableView.setItems(addedAlimentosData);
    }
    private void loadDataAndShow(){
        alimentoList = findAlimentoUseCase.findAll();
        allAlimentosData.clear();
        allAlimentosData.addAll(alimentoList);
    }

    @FXML
    private void initialize(){
        cbCategoria.getItems().setAll(RefeicaoCategoria.values());
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        totalValoresNutricionais = new ValoresNutricionais();
        updateValoresNutricionais();
    }

    @FXML
    void backScene(ActionEvent event) {
        try{
            WindowLoader.setRoot("RefeicaoManagementUI");
            RefeicaoManagementUIController controller = (RefeicaoManagementUIController) WindowLoader.getController();
            controller.setRefeicaoFromCardapio(cardapio);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void saveOrUpdate(ActionEvent event) throws IOException {
        getEntityToView();
        if(refeicao.getId()==null){
            createRefeicaoUseCase.insert(refeicao);
        }
        else{
            updateRefeicaoUseCase.update(refeicao);
        }
        WindowLoader.setRoot("RefeicaoManagementUI");
        RefeicaoManagementUIController controller = (RefeicaoManagementUIController) WindowLoader.getController();
        controller.setRefeicaoFromCardapio(refeicao.getCardapio());

    }
    private void getEntityToView(){
        if(refeicao == null)
            refeicao = new Refeicao();
        refeicao.setHorario(LocalTime.parse(txtHorario.getText()));
        refeicao.setCategoria(cbCategoria.getValue());
        refeicao.setAlimentos(alimentosAdded);
    }

    @FXML
    void txtHorarioKeyReleased(KeyEvent event) {
        TextFieldFormater textFieldFormater = new TextFieldFormater();
        textFieldFormater.setMask("##:##");
        textFieldFormater.setCaracteresValidos("0123456789");
        textFieldFormater.setTf(txtHorario);
        textFieldFormater.formatter();
    }

    public void setRefeicao(Refeicao refeicao,UIMode mode){
        this.refeicao = refeicao;
        this.mode = mode;
        setEntityToView();
        if(mode == UIMode.VIEW)
            configureViewMode();


    }

    public void setCardapio(Cardapio cardapio){
        this.cardapio = cardapio;
        cbCategoria.getItems().setAll(RefeicaoCategoria.values());

    }

    private void setEntityToView(){
        txtHorario.setText(refeicao.getHorario().toString());
        cbCategoria.setValue(refeicao.getCategoria());
        List<Alimento> alimentosRefeicao = refeicao.getAlimentos();
        allAlimentosData.clear();


        for (Alimento alimento: alimentosRefeicao) {
            alimentosAdded.add(alimento);
            ValoresNutricionais valoresNutricionais = alimento.calculateValoresNutricionais();
            totalValoresNutricionais.somar(valoresNutricionais);
            updateValoresNutricionais();
            if(mode == UIMode.UPDATE)
                alimentoList.remove(alimento);
        }

        if(mode == UIMode.UPDATE){
            addedAlimentosData.clear();
            addedAlimentosData.addAll(alimentosRefeicao);
            allAlimentosData.addAll(alimentoList);

        }
        else{
            allAlimentosData.addAll(alimentosRefeicao);
            allAlimentosTableView.setEditable(false);
        }

    }
    private void configureViewMode(){
        txtBusca.setVisible(false);
        btnAddToRefeicao.setVisible(false);
        addedAlimentosTableView.setVisible(false);
        txtHorario.setDisable(true);
        cbCategoria.setDisable(true);
        btnRemover.setVisible(false);
        lLupa.setVisible(false);
        btnConfirm.setVisible(false);
        btnCancel.setText("Fechar");

    }

}
