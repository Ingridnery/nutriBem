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
            statement.addBatch(createPlanoNutricional());
            statement.addBatch(createCardapio());
            statement.addBatch(createRefeicao());
            statement.addBatch(createAlimento());
            statement.addBatch(createAlimentoRefeicao());

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
        builder.append("CONSTRAINT paciente_sexo_ck CHECK (sexo IN ('M', 'm', 'F', 'f')),\n");
        builder.append("CONSTRAINT paciente_lactose_ck CHECK (intolerancia_lactose IN ('Apto', 'Intolerante', 'Restrito')),\n");
        builder.append("CONSTRAINT paciente_gluten_ck CHECK (intolerancia_gluten IN (0, 1)),\n");
        builder.append("CONSTRAINT paciente_diabetes_ck CHECK (diabetes IN (0, 1)),\n");
        builder.append("CONSTRAINT paciente_ativado_ck CHECK (ativado IN (0, 1))\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createPlanoNutricional() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE PlanoNutricional(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("paciente INTEGER NOT NULL,\n");
        builder.append("nome TEXT NOT NULL,\n");
        builder.append("inicio TEXT NOT NULL,\n");
        builder.append("fim TEXT NOT NULL,\n");
        builder.append("FOREIGN KEY (paciente) REFERENCES Paciente (id)\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createCardapio() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Cardapio(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("planoNutricional INTEGER NOT NULL,\n");
        builder.append("dia INTEGER NOT NULL,\n");
        builder.append("FOREIGN KEY (planoNutricional) REFERENCES PlanoNutricional (id),\n");
        builder.append("CONSTRAINT cardapio_dia_ck CHECK (dia > 0)\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createRefeicao() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Refeicao(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("cardapio INTEGER NOT NULL,\n");
        builder.append("horario TEXT NOT NULL,\n");
        builder.append("categoria TEXT NOT NULL,\n");
        builder.append("FOREIGN KEY (cardapio) REFERENCES Cardapio (id),\n");
        builder.append("CONSTRAINT refeicao_categoria_ck CHECK (categoria IN ('Café da manhã', 'Lanche da manhã', 'Almoço', 'Lanche da tarde', 'Jantar', 'Ceia'))\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createAlimento() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Alimento(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("nome TEXT NOT NULL UNIQUE,\n");
        builder.append("porcao INTEGER NOT NULL,\n");
        builder.append("calorias INTEGER NOT NULL,\n");
        builder.append("colesterol INTEGER NOT NULL,\n");
        builder.append("gluten INTEGER NOT NULL,\n");
        builder.append("gorduras_saturadas REAL NOT NULL,\n");
        builder.append("sodio REAL NOT NULL,\n");
        builder.append("acucar REAL NOT NULL,\n");
        builder.append("lactose REAL NOT NULL,\n");
        builder.append("ativado INTEGER DEFAULT 1,\n");
        builder.append("CONSTRAINT alimento_gluten_ck CHECK (gluten IN (0, 1)),\n");
        builder.append("CONSTRAINT alimento_ativado_ck CHECK (ativado IN (0, 1))\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createAlimentoRefeicao() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE AlimentoRefeicao(\n");
        builder.append("alimento INTEGER,\n");
        builder.append("refeicao INTEGER,\n");
        builder.append("PRIMARY KEY (alimento, refeicao),\n");
        builder.append("FOREIGN KEY (alimento) REFERENCES Alimento(id),\n");
        builder.append("FOREIGN KEY (refeicao) REFERENCES Refeicao(id)\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }
}