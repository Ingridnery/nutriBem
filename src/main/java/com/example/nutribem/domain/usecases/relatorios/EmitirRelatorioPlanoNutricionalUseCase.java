package com.example.nutribem.domain.usecases.relatorios;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.cardapio.CardapioDAO;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;
import com.example.nutribem.domain.usecases.valoresNutricionais.CalculateValoresNutricionaisUseCase;
import com.example.nutribem.domain.usecases.valoresNutricionais.ValoresNutricionais;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;


public class EmitirRelatorioPlanoNutricionalUseCase {

    private final CardapioDAO cardapioDAO;
    private final RefeicaoDAO refeicaoDAO;

    private static final DecimalFormat decfor = new DecimalFormat("0.00");


    public EmitirRelatorioPlanoNutricionalUseCase(CardapioDAO cardapioDAO, RefeicaoDAO refeicaoDAO) {
        this.cardapioDAO = cardapioDAO;
        this.refeicaoDAO = refeicaoDAO;
    }

    public Boolean emitir(PlanoNutricional planoNutricional, CalculateValoresNutricionaisUseCase calculateValoresNutricionaisUseCase) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("resources/relatorios/Plano Nutricional " + planoNutricional.getPaciente().getNome() + " " + LocalDate.now() + ".pdf"));

        try {
            HeaderRelatorio.header(document);
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL);


            Paciente paciente = planoNutricional.getPaciente();
            Paragraph paragraphPaciente = new Paragraph("\n" + paciente.getNome() + " CPF: " + paciente.getCpf().getCpfFormatted() + " Email: " + paciente.getEmail(), font);
            paragraphPaciente.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphPaciente);
            Paragraph paragraphPlano = new Paragraph("\nPlano nutricional " + planoNutricional.getNome(), font);
            document.add(paragraphPlano);

            cardapioDAO.findAll().forEach(cardapio -> {
                if (cardapio.getPlanoNutricional().getId().equals(planoNutricional.getId())) {
                    Paragraph paragraphCardapio = new Paragraph("Cardapio " + cardapio.getId() + " Dia: " + cardapio.getNumeroDia(), font);

                    refeicaoDAO.findByCardapio(cardapio.getId()).forEach(refeicao -> {
                        Paragraph paragraphRefeicao = new Paragraph("Refei????o " + refeicao.getId() + " Categoria: " + refeicao.getCategoria() + " Hor??rio: " + refeicao.getHorario(), font);
                        Paragraph paragraph = new Paragraph("Alimentos da refei????o:", font);

                        try {
                            document.add(paragraphCardapio);
                            document.add(paragraphRefeicao);
                            document.add(paragraph);
                        } catch (DocumentException e) {
                            throw new RuntimeException(e);
                        }
                        refeicao.getAlimentos().forEach(alimento -> {

                            Paragraph paragraphAlimento = new Paragraph("   " + alimento.getNome() + "\n        Por????o: "
                                    + decfor.format(alimento.getPorcao()) + " gramas\n        Calorias: " + decfor.format(alimento.getCalorias())
                                    + " kcal\n        A????car: " + decfor.format(alimento.getAcucar()) + " gramas\n        S??dio: " + decfor.format(alimento.getSodio())
                                    + " gramas\n        Gorduras saturadas: " + decfor.format(alimento.getGordurasSaturadas()) + " gramas\n        Colesterol: " + decfor.format(alimento.getColesterol())
                                    + " gramas\n        Lactose: " + decfor.format(alimento.getLactose()) + " gramas\n        Gl??ten: " + (!alimento.getGluten() ? "N??o" : "Sim") + "\n        Prote??nas: " + decfor.format(alimento.getProteinas()) + " gramas", font);
                            try {
                                document.add(paragraphAlimento);
                            } catch (DocumentException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        ValoresNutricionais valoresNutricionais = calculateValoresNutricionaisUseCase.from(refeicao);


                        Paragraph paragraphValoresNutricionais = new Paragraph("\nValores nutricionais da refei????o: \n    A????car: " + decfor.format(valoresNutricionais.getAcucar()) + "  gramas \n    Calorias: " +
                                decfor.format(valoresNutricionais.getCalorias()) + " kcal\n    Colesterol: " + decfor.format(valoresNutricionais.getColesterol()) + " gramas \n    Gorduras saturadas:" + decfor.format(valoresNutricionais.getGordurasSaturadas()) + " gramas\n    S??dio: "
                                + decfor.format(valoresNutricionais.getSodio()) + " gramas\n    Lactose: " + decfor.format(valoresNutricionais.getLactose()) + " gramas\n    Gl??ten: " + (!valoresNutricionais.getGluten() ? "N??o" : "Sim") + "\n    Prote??nas: " + decfor.format(valoresNutricionais.getProteinas()) + " gramas", font);
                        try {
                            document.add(paragraphValoresNutricionais);
                        } catch (DocumentException e) {
                            throw new RuntimeException(e);
                        }

                    });

                }
            });
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            document.close();
        }

    }


}
