package com.example.nutribem.domain.usecases.relatorios;

import com.itextpdf.text.*;

public class HeaderRelatorio {

    public static void header(Document document) throws DocumentException {
        document.open();
        Font fontTittle = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.NORMAL);
        Paragraph tittle = new Paragraph("Nutribem", fontTittle);
        tittle.setAlignment(Element.ALIGN_CENTER);
        document.add(tittle);
    }
}
