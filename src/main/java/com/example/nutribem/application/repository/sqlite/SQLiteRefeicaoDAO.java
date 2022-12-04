package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.entities.refeicao.RefeicaoCategoria;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteRefeicaoDAO implements RefeicaoDAO {

    private final SQLiteCardapioDAO cardapioDAO = new SQLiteCardapioDAO();
    private final SQLiteAlimentoDAO alimentoDAO = new SQLiteAlimentoDAO();

    @Override
    public List<Refeicao> findByCardapio(Integer cardapio) {
        String sql = "SELECT * FROM Refeicao WHERE cardapio = ?";
        List<Refeicao> refeicoes = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, cardapio);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Refeicao refeicao = resultsetToEntity(resultSet);
                refeicao.setAlimentos(findAlimentosFromRefeicao(refeicao));
                refeicoes.add(refeicao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return refeicoes;
    }

    @Override
    public boolean isInAnyRefeicao(Alimento alimento) {
        String sql = "SELECT * FROM AlimentoRefeicao WHERE alimento = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, alimento.getId());
            ResultSet resultSet = stmt.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Alimento> findAlimentosFromRefeicao(Refeicao refeicao) {
        String sql = "SELECT * FROM Alimento a JOIN AlimentoRefeicao af ON a.id = af.alimento WHERE af.refeicao = ?";
        List<Alimento> alimentos = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, refeicao.getId());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                alimentos.add(alimentoDAO.resultsetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alimentos;
    }

    @Override
    public Integer create(Refeicao refeicao) {
        String sql = "INSERT INTO Refeicao (cardapio, horario, categoria) VALUES (?, ? , ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, refeicao.getCardapio().getId());
            stmt.setString(2, refeicao.getHorario().toString());
            stmt.setString(3, refeicao.getCategoria().toString());
            stmt.execute();

            return stmt.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Refeicao> findOne(Integer id) {
        String sql = "SELECT * FROM Refeicao WHERE id = ?";
        Refeicao refeicao = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                refeicao = resultsetToEntity(resultSet);
                refeicao.setAlimentos(findAlimentosFromRefeicao(refeicao));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(refeicao);
    }

    @Override
    public List<Refeicao> findAll() {
        String sql = "SELECT * FROM Refeicao";
        List<Refeicao> refeicoes = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Refeicao refeicao = resultsetToEntity(resultSet);
                refeicao.setAlimentos(findAlimentosFromRefeicao(refeicao));
                refeicoes.add(refeicao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return refeicoes;
    }

    @Override
    public boolean update(Refeicao refeicao) {
        String sql = "UPDATE Refeicao SET horario = ?, categoria = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, refeicao.getHorario().toString());
            stmt.setString(2, refeicao.getCategoria().toString());
            stmt.setInt(3, refeicao.getId());
            stmt.execute();

            /* UPDATE ALIMENTOS IN REFEICAO */

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM Refeicao WHERE id = ?";

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
    public boolean delete(Refeicao refeicao) {
        return deleteByKey(refeicao.getId());
    }

    private Refeicao resultsetToEntity(ResultSet rs) throws SQLException {
        Refeicao refeicao = new Refeicao(
                LocalTime.parse(rs.getString("horario")),
                RefeicaoCategoria.ofString(rs.getString("categoria")),
                cardapioDAO.findOne(rs.getInt("cardapio")).get()
        );

        refeicao.setId(rs.getInt("id"));
        return refeicao;
    }
}
