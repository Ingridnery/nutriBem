package com.example.nutribem.domain.usecases.alimento;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ImportAlimentosUseCase {

    private AlimentoDAO alimentoDAO;

    public ImportAlimentosUseCase(AlimentoDAO alimentoDAO) {
        this.alimentoDAO = alimentoDAO;
    }

    public void importFrom(String filePath){
        Path myPath = Paths.get(filePath);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            HeaderColumnNameMappingStrategy<AlimentoDTO> strategy
                    = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(AlimentoDTO.class);

            CsvToBean<AlimentoDTO> csvToBean = new CsvToBeanBuilder<AlimentoDTO>(br)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<AlimentoDTO> alimentos = csvToBean.parse();

            alimentos.forEach(System.out::println);

            AlimentoDTO dto = new AlimentoDTO();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
