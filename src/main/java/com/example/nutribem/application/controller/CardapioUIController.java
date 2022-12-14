package com.example.nutribem.application.controller;

import com.example.nutribem.WindowLoader;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.AlertMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

import static com.example.nutribem.application.main.Main.createCardapioUseCase;
import static com.example.nutribem.application.main.Main.updateCardapioUseCase;

public class CardapioUIController {

    @FXML
    private TextField txtNumeroDia;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    private Cardapio cardapio;
    private PlanoNutricional planoNutricional;
    private final AlertMessage alertMessage = new AlertMessage();

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CardapioManagementUI");
        CardapioManagementUIController controller = (CardapioManagementUIController) WindowLoader.getController();
        controller.setCardapioFromPlanoNutricional(planoNutricional);

    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {

        try {
            getEntityToView();
            if (cardapio.getId() == null)
                createCardapioUseCase.insert(cardapio);
            else
                updateCardapioUseCase.update(cardapio);
            WindowLoader.setRoot("CardapioManagementUI");
            CardapioManagementUIController controller = (CardapioManagementUIController) WindowLoader.getController();
            controller.setCardapioFromPlanoNutricional(planoNutricional);

        } catch (Exception e) {
            alertMessage.showAlert("Erro!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void getEntityToView() {
        if (cardapio == null)
            cardapio = new Cardapio();

        try {
            cardapio.setNumeroDia(Integer.valueOf(txtNumeroDia.getText()));
            cardapio.setPlanoNutricional(planoNutricional);
        } catch (Exception e) {
            throw new IllegalArgumentException("Dia inv??lido");
        }
    }

    public void setEntityIntoView() {
        txtNumeroDia.setText(String.valueOf(cardapio.getNumeroDia()));
    }

    public void setCardapio(Cardapio cardapio, UIMode mode) {
        Objects.requireNonNull(cardapio, "Cardapio n??o pode ser nulo!");

        this.cardapio = cardapio;
        setEntityIntoView();
        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnConfirm.setVisible(false);
        btnCancel.setText("Fechar");
        txtNumeroDia.setDisable(true);
    }

    public void setPlanoNutricional(PlanoNutricional planoNutricional) {
        this.planoNutricional = planoNutricional;
    }
}
