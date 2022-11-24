package com.example.nutribem.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public void buildIfMissing() {
        if (!Files.exists(Paths.get("database.db")))
            buildTables();
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createNutricionista());
            statement.addBatch(createDicas());

            statement.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createNutricionista() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Nutricionista (\n");
        builder.append("nome TEXT PRIMARY KEY,\n");
        builder.append("senha TEXT NOT NULL\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createDicas() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Dicas(\n");
        builder.append("nutricionista TEXT,\n");
        builder.append("dica TEXT,\n");
        builder.append("PRIMARY KEY (nutricionista, dica),\n");
        builder.append("FOREIGN KEY (nutricionista) REFERENCES Nutricionista(nome)\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }
}