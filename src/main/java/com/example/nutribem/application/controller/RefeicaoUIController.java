package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.entities.refeicao.RefeicaoCategoria;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import com.example.nutribem.domain.usecases.utils.TextFieldFormater;
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
import java.util.Objects;

import static com.example.nutribem.application.main.Main.*;

public class RefeicaoUIController {

    @FXML
    private TableView<Alimento> tableView;
    @FXML
    private TableColumn<Alimento,Integer> cId;
    @FXML
    private TableColumn<Alimento,String> cCheck;
    @FXML
    private TableColumn<Alimento,String> cName;
    @FXML
    private TextField txtHorario;
    @FXML
    private ComboBox<RefeicaoCategoria> cbCategoria;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lbInformation;

    private Refeicao refeicao;
    private Cardapio cardapio;
    private final AlertMessage alert = new AlertMessage();
    private ObservableList<Alimento> tableData;






    private void bindColumnsToValueSources(){
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cCheck.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
    }
    private void bindTableViewToItemsList(){
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }
    private void loadDataAndShow(){
        List<Alimento> alimentoList = refeicao.getAlimentos();
        tableData.clear();
        tableData.addAll(alimentoList);
    }


    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("RefeicaoManagementUI");
        RefeicaoManagementUIController controller = (RefeicaoManagementUIController) WindowLoader.getController();
        controller.setRefeicaoFromCardapio(cardapio);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {

        try{
            getEntityToView();
            if(refeicao.getId() == null)
                createRefeicaoUseCase.insert(refeicao);
            else
                updateRefeicaoUseCase.update(refeicao);
            WindowLoader.setRoot("RefeicaoManagementUI");
            RefeicaoManagementUIController controller = (RefeicaoManagementUIController) WindowLoader.getController();
            controller.setRefeicaoFromCardapio(cardapio);

        }catch (Exception e){
            alert.showAlert("Erro!", "Dados inválidos", Alert.AlertType.ERROR);
        }

    }

    public void getEntityToView(){

        if(refeicao==null)
            refeicao = new Refeicao();
        refeicao.setCategoria(cbCategoria.getValue());
        refeicao.setHorario(LocalTime.parse(txtHorario.getText()));
        refeicao.setCardapio(cardapio);

        List<Alimento> alimentoList = new ArrayList<>();

        for(Alimento bean : tableData)
        {
            if(bean.getCheckBox().isSelected())
            {
                alimentoList.add(bean);
            }
        }
        refeicao.setAlimentos(alimentoList);

    }
    public void setEntityIntoView(){
        txtHorario.setText(refeicao.getHorario().toString());
        cbCategoria.setValue(refeicao.getCategoria());
    }

    public void setRefeicao(Refeicao refeicao, UIMode mode){
        Objects.requireNonNull(refeicao,"Refeição não pode ser nulo!");

        this.refeicao=refeicao;
        initialize();
        setEntityIntoView();
        if(mode == UIMode.VIEW)
            configureViewMode();
    }
    public void setCardapio(Cardapio cardapio){
        this.cardapio=cardapio;
        cbCategoria.getItems().setAll(RefeicaoCategoria.values());
    }
    private void configureViewMode(){
        btnConfirm.setVisible(false);
        btnCancel.setText("Fechar");

        txtHorario.setDisable(true);
        cbCategoria.setDisable(true);

        tableView.setEditable(false);
        cCheck.setVisible(false);
        initialize();

    }
    private void initialize(){
        lbInformation.setVisible(false);
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    public void txtHorarioKeyReleased(KeyEvent keyEvent) {
        TextFieldFormater textFieldFormater = new TextFieldFormater();
        textFieldFormater.setMask("##:##");
        textFieldFormater.setCaracteresValidos("0123456789");
        textFieldFormater.setTf(txtHorario);
        textFieldFormater.formatter();
    }
}
