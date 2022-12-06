package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.alimento.AlimentoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteAlimentoDAO implements AlimentoDAO {
    @Override
    public Optional<Alimento> findByName(String name) {
        String sql = "SELECT * FROM Alimento WHERE nome = ?";
        Alimento alimento = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                alimento = resultsetToEntity(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(alimento);
    }

    @Override
    public Integer create(Alimento alimento) {
        String sql = "INSERT INTO Alimento (nome, porcao, calorias, colesterol, gluten, gorduras_saturadas, sodio, acucar, lactose, proteinas) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, alimento.getNome());
            stmt.setInt(2, alimento.getPorcao());
            stmt.setInt(3, alimento.getCalorias());
            stmt.setInt(4, alimento.getColesterol());
            stmt.setBoolean(5, alimento.getGluten());
            stmt.setDouble(6, alimento.getGordurasSaturadas());
            stmt.setDouble(7, alimento.getSodio());
            stmt.setDouble(8, alimento.getAcucar());
            stmt.setDouble(9, alimento.getLactose());
            stmt.setDouble(10, alimento.getProteinas());
            stmt.execute();

            return stmt.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Alimento> findOne(Integer id) {
        String sql = "SELECT * FROM Alimento WHERE id = ?";
        Alimento alimento = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                alimento = resultsetToEntity(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(alimento);
    }

    @Override
    public List<Alimento> findAll() {
        String sql = "SELECT * FROM Alimento";
        List<Alimento> alimentos = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                alimentos.add(resultsetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alimentos;
    }

    @Override
    public boolean update(Alimento alimento) {
        String sql = "UPDATE Alimento SET " +
                "nome = ?, " +
                "porcao = ?, " +
                "calorias = ?, " +
                "colesterol = ?, " +
                "gluten = ?, " +
                "gorduras_saturadas = ?, " +
                "sodio = ?, " +
                "acucar = ?, " +
                "lactose = ?, " +
                "ativado = ? " +
                "proteinas = ?" +
                "WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, alimento.getNome());
            stmt.setInt(2, alimento.getPorcao());
            stmt.setInt(3, alimento.getCalorias());
            stmt.setInt(4, alimento.getColesterol());
            stmt.setBoolean(5, alimento.getGluten());
            stmt.setDouble(6, alimento.getGordurasSaturadas());
            stmt.setDouble(7, alimento.getSodio());
            stmt.setDouble(8, alimento.getAcucar());
            stmt.setDouble(9, alimento.getLactose());
            stmt.setBoolean(10, alimento.isAtivado());
            stmt.setDouble(11, alimento.getProteinas());
            stmt.setInt(12, alimento.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM Alimento WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Alimento alimento) {
        return deleteByKey(alimento.getId());
    }

    protected Alimento resultsetToEntity(ResultSet rs) throws SQLException {
        Alimento alimento = new Alimento(
                rs.getString("nome"),
                rs.getInt("porcao"),
                rs.getInt("calorias"),
                rs.getInt("colesterol"),
                rs.getBoolean("gluten"),
                rs.getDouble("gorduras_saturadas"),
                rs.getDouble("proteinas"),
                rs.getDouble("sodio"),
                rs.getDouble("acucar"),
                rs.getDouble("lactose")
        );
        alimento.setId(rs.getInt("id"));
        alimento.setAtivado(rs.getBoolean("ativado"));

        return alimento;
    }
}
