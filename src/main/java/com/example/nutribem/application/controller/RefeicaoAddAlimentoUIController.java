package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.nutribem.application.main.Main.*;

public class RefeicaoAddAlimentoUIController {

    @FXML
    private TableView<Alimento> tableView;
    @FXML
    private TableColumn<Alimento,Integer> cId;
    @FXML
    private TableColumn<Alimento,String> cName;
    @FXML
    private TableColumn<Alimento, String> cCheck;
    @FXML
    private TextField txtNameAlimento;
    private ObservableList<Alimento> tableData;

    private final AlertMessage alert = new AlertMessage();


    private Refeicao refeicao;

    @FXML
    private void initialize(){
        tableView.setEditable(true);
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }
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
        List<Alimento> alimentoList = findAlimentoUseCase.findAll().stream()
                        .filter(Alimento::isAtivado)
                .toList();
        tableData.clear();
        tableData.addAll(alimentoList);
    }
    public void setRefeicao(Refeicao refeicao){this.refeicao=refeicao;}


    public void handle(KeyEvent key) {
        String txtSearch = (txtNameAlimento.getText() + key.getText()).toUpperCase();
        List<Alimento> alimentoList = findAlimentoUseCase.findAll();
        List<Alimento> matchesWithSearch = alimentoList.stream()
                .filter(alimento -> alimento.getNome().toUpperCase().startsWith(txtSearch))
                .toList();
        tableData.clear();
        tableData.addAll(matchesWithSearch);
    }

    public void addAlimentos(ActionEvent actionEvent) throws IOException {
        List<Alimento> alimentoList = new ArrayList<>();
        Paciente paciente = refeicao.getCardapio().getPlanoNutricional().getPaciente();

        try {
            for(Alimento bean : tableData)
            {
                if(bean.getCheckBox().isSelected())
                {
                    if(paciente.canEat(bean))
                        alimentoList.add(bean);
                    refeicao.setAlimentos(alimentoList);
                    updateRefeicaoUseCase.update(refeicao);
                }

            }
            WindowLoader.setRoot("RefeicaoManagementUI");
            RefeicaoManagementUIController controller = (RefeicaoManagementUIController) WindowLoader.getController();
            controller.setRefeicaoFromCardapio(refeicao.getCardapio());

        }catch(Exception e){
            alert.showAlert("Error!", e.getMessage()+"\nPaciente intolerante", Alert.AlertType.ERROR);
        }

    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("RefeicaoManagementUI");
        RefeicaoManagementUIController controller = (RefeicaoManagementUIController) WindowLoader.getController();
        controller.setRefeicaoFromCardapio(refeicao.getCardapio());
    }


}
