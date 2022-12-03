package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.usecases.nutricionista.NutricionistaDAO;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteNutricionistaDAO implements NutricionistaDAO {
    @Override
    public String create(Nutricionista nutricionista) {
        String sql = "INSERT INTO Nutricionista VALUES (?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, nutricionista.getNomeUsuario());
            stmt.setString(2, nutricionista.getSenha());
            stmt.execute();
            stmt.close();

            createDicasSenha(nutricionista.getNomeUsuario(), nutricionista.getDicas());

            return nutricionista.getNomeUsuario();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Nutricionista> findByUserName(String userName) {
        String sql = "SELECT * FROM Nutricionista WHERE nome = ?";
        Nutricionista nutricionista = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                nutricionista = new Nutricionista(
                        resultSet.getString("nome"),
                        resultSet.getString("senha")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(nutricionista);
    }

    @Override
    public boolean update(Nutricionista nutricionista) {
        String sql = "UPDATE Nutricionista SET senha = ? WHERE nome = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, nutricionista.getSenha());
            stmt.setString(2, nutricionista.getNomeUsuario());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> getDicasSenhaFromUserName(String userName) {
        String sql = "SELECT dica FROM Dicas WHERE nutricionista = ?";

        ArrayList<String> dicas = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dicas.add(rs.getString("dica"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (dicas.isEmpty())
            throw new EntityNotFoundException("Usuario não encontrado ou não possui dicas!");

        return dicas;
    }

    @Override
    public String getSenhaFromUserName(String username) {
        Nutricionista nutricionista = findByUserName(username).orElseThrow(()->
                new EntityNotFoundException("Usuario não encontrado!")
        );
        return nutricionista.getSenha();
    }

    private void createDicasSenha(String nutricionista, List<String> dicas) throws SQLException {
        String sql = "INSERT INTO Dicas VALUES (?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {

            dicas.forEach(dica -> {
                try {
                    stmt.setString(1, nutricionista);
                    stmt.setString(2, dica);
                    stmt.addBatch();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            stmt.executeBatch();
        }
    }
}
