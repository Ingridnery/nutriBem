package com.example.nutribem.domain.usecases.relatorios;

import com.example.nutribem.domain.usecases.paciente.PacienteDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class EmitirRelatorioContatosUseCase {
    private final PacienteDAO dao;

    public EmitirRelatorioContatosUseCase(PacienteDAO dao) {
        this.dao = dao;
    }

    public Boolean emitir() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("resources/relatorios/ContatosPacientes.pdf "));
        try {
            HeaderRelatorio.header(document);
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL);
            Font fontSubtitle = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.NORMAL);
            Paragraph paragraphSubtitle = new Paragraph("\nPacientes cadastrados", fontSubtitle);
            paragraphSubtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphSubtitle);


            dao.findAll().forEach(paciente -> {
                Paragraph paragraph = new Paragraph("\n" + paciente.getNome() + ": Telefone: " + paciente.getTelefone() + ", Email: " + paciente.getEmail(), font);
                try {
                    document.add(paragraph);
                } catch (DocumentException e) {
                    throw new RuntimeException(e);
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
