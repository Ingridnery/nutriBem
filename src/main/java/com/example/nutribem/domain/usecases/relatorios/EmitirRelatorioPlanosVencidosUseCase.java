package com.example.nutribem.domain.usecases.relatorios;

import com.example.nutribem.domain.usecases.planoNutricional.PlanoNutricionalDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;


public class EmitirRelatorioPlanosVencidosUseCase {
    private final PlanoNutricionalDAO dao;

    public EmitirRelatorioPlanosVencidosUseCase(PlanoNutricionalDAO dao) {
        this.dao = dao;
    }

    public Boolean emitir() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("resources/relatorios/PlanosVencidos.pdf "));
        try {
            HeaderRelatorio.header(document);

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL);
            Font fontSubtitle = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.NORMAL);
            Paragraph paragraphSubtitle = new Paragraph("\nPlanos nutricionais vencidos", fontSubtitle);
            paragraphSubtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphSubtitle);

            dao.findAll().forEach(plano -> {
                if (plano.getDataFim().isBefore(LocalDate.now())) {

                    Paragraph paragraph = new Paragraph("Paciente: " + plano.getPaciente().getNome() + ": Telefone: "
                            + plano.getPaciente().getTelefone() + ", Email: " + plano.getPaciente().getEmail()
                            + ", Plano Nutricional: " + plano.getNome() + ", venceu em " + plano.getDataFim(),font);
                    try {
                        document.add(paragraph);
                    } catch (DocumentException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return true;
        }catch (Exception e){
            return false;
        }finally {
            document.close();
        }

    }
}
