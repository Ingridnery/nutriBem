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
            statement.addBatch(createPaciente());

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

    private String createPaciente() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Paciente(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("nome TEXT NOT NULL,\n");
        builder.append("cpf TEXT NOT NULL UNIQUE,\n");
        builder.append("nascimento TEXT NOT NULL,\n");
        builder.append("sexo TEXT,\n");
        builder.append("email TEXT,\n");
        builder.append("telefone TEXT,\n");
        builder.append("circunferencia INTEGER NOT NULL,\n");
        builder.append("altura INTEGER NOT NULL,\n");
        builder.append("peso REAL NOT NULL,\n");
        builder.append("historico TEXT,\n");
        builder.append("intolerancia_lactose TEXT NOT NULL,\n");
        builder.append("alergias TEXT,\n");
        builder.append("observacoes TEXT,\n");
        builder.append("objetivos TEXT,\n");
        builder.append("intolerancia_gluten INTEGER NOT NULL,\n");
        builder.append("diabetes INTEGER NOT NULL,\n");
        builder.append("ativado INTEGER DEFAULT 1,\n");
        builder.append("CONSTRAINT sexo_ck CHECK (sexo IN ('M', 'm', 'F', 'f')),\n");
        builder.append("CONSTRAINT lactose_ck CHECK (intolerancia_lactose IN ('Apto', 'Intolerante', 'Restrito')),\n");
        builder.append("CONSTRAINT gluten_ck CHECK (intolerancia_gluten IN (0, 1)),\n");
        builder.append("CONSTRAINT diabetes_ck CHECK (diabetes IN (0, 1)),\n");
        builder.append("CONSTRAINT ativado_ck CHECK (ativado IN (0, 1))\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }
}