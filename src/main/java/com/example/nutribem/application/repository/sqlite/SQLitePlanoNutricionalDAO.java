package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.planoNutricional.PlanoNutricionalDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLitePlanoNutricionalDAO implements PlanoNutricionalDAO {

    private final SQLitePacienteDAO pacienteDAO = new SQLitePacienteDAO();

    @Override
    public Optional<PlanoNutricional> findByName(String name) {
        String sql = "SELECT * FROM PlanoNutricional WHERE nome = ?";
        PlanoNutricional plano = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                plano = resultsetToEntity(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(plano);
    }

    @Override
    public List<PlanoNutricional> findByIdPaciente(Integer id) {
        String sql = "SELECT * FROM PlanoNutricional WHERE paciente = ?";

        List<PlanoNutricional> planos = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                planos.add(resultsetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planos;
    }

    @Override
    public Integer create(PlanoNutricional plano) {
        String sql = "INSERT INTO PlanoNutricional (nome, paciente, inicio, fim) VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setInt(2, plano.getPaciente().getId());
            stmt.setString(3, plano.getDataInicio().toString());
            stmt.setString(4, plano.getDataFim().toString());
            stmt.execute();

            return stmt.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<PlanoNutricional> findOne(Integer id) {
        String sql = "SELECT * FROM PlanoNutricional WHERE id = ?";
        PlanoNutricional plano = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                plano = resultsetToEntity(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(plano);
    }

    @Override
    public List<PlanoNutricional> findAll() {
        String sql = "SELECT * FROM PlanoNutricional";

        List<PlanoNutricional> planos = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                planos.add(resultsetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planos;
    }

    @Override
    public boolean update(PlanoNutricional plano) {
        String sql = "UPDATE PlanoNutricional SET nome = ?, inicio = ?, fim = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setString(2, plano.getDataInicio().toString());
            stmt.setString(3, plano.getDataFim().toString());
            stmt.setInt(4, plano.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM PlanoNutricional WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(PlanoNutricional plano) {
        return deleteByKey(plano.getId());
    }

    private PlanoNutricional resultsetToEntity(ResultSet resultSet) throws SQLException {
        PlanoNutricional plano = new PlanoNutricional(
                resultSet.getString("nome"),
                LocalDate.parse(resultSet.getString("inicio")),
                LocalDate.parse(resultSet.getString("fim")),
                pacienteDAO.findOne(resultSet.getInt("paciente")).get()
        );
        plano.setId(resultSet.getInt("id"));

        return plano;
    }
}
