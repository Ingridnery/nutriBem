package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.cardapio.CardapioDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteCardapioDAO implements CardapioDAO {

    private final SQLitePlanoNutricionalDAO planoNutricionalDAO = new SQLitePlanoNutricionalDAO();

    @Override
    public List<Cardapio> findByNumeroDia(Integer numeroDia) {
        String sql = "SELECT * FROM Cardapio WHERE dia = ?";
        List<Cardapio> cardapios = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, numeroDia);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                cardapios.add(resultsetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardapios;
    }

    @Override
    public List<Cardapio> findByPlanoNutricional(Integer idPlano) {
        String sql = "SELECT * FROM Cardapio WHERE planoNutricional = ?";
        List<Cardapio> cardapios = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, idPlano);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                cardapios.add(resultsetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardapios;
    }

    @Override
    public Integer create(Cardapio cardapio) {
        String sql = "INSERT INTO Cardapio(planoNutricional, dia) VALUES (?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, cardapio.getPlanoNutricional().getId());
            stmt.setInt(2, cardapio.getNumeroDia());
            stmt.execute();

            return stmt.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Cardapio> findOne(Integer id) {
        String sql = "SELECT * FROM Cardapio WHERE id = ?";
        Cardapio cardapio = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                cardapio = resultsetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(cardapio);
    }

    @Override
    public List<Cardapio> findAll() {
        String sql = "SELECT * FROM Cardapio";
        List<Cardapio> cardapios = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                cardapios.add(resultsetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardapios;
    }

    @Override
    public boolean update(Cardapio cardapio) {
        String sql = "UPDATE Cardapio SET dia = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, cardapio.getNumeroDia());
            stmt.setInt(2, cardapio.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM Cardapio WHERE id = ?";

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
    public boolean delete(Cardapio cardapio) {
        return deleteByKey(cardapio.getId());
    }

    private Cardapio resultsetToEntity(ResultSet rs) throws SQLException {
        Cardapio cardapio = new Cardapio(
                rs.getInt("dia"),
                planoNutricionalDAO.findOne(rs.getInt("planoNutricional")).get()
        );
        cardapio.setId(rs.getInt("id"));

        return cardapio;
    }
}
