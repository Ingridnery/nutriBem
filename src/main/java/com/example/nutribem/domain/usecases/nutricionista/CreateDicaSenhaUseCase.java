package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.usecases.utils.Notification;

import java.util.ArrayList;
import java.util.List;

public class CreateDicaSenhaUseCase {
    private NutricionistaDAO nutricionistaDAO;
    private static final int MAXLENGTH = 255;

    public boolean insert(Nutricionista nutricionista, ArrayList<String> dicas){

       isValidLength(dicas);
       nutricionista.setDicas(dicas);

        return nutricionistaDAO.update(nutricionista);
    }

    public void isValidLength(ArrayList<String> dicaSenha){
        Notification notification = new Notification();

        for (int i = 0; i < dicaSenha.size(); i++) {
            if(dicaSenha.get(i).length()> MAXLENGTH){
                notification.addError("A dica " + dicaSenha+" possui mais de 255 caracteres!");
            }
        }
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
    }


}
