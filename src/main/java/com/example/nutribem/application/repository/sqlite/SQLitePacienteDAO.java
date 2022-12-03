package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.paciente.CPF;
import com.example.nutribem.domain.entities.paciente.IntoleranciaLactose;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.paciente.Sexo;
import com.example.nutribem.domain.usecases.paciente.PacienteDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLitePacienteDAO implements PacienteDAO {
    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        String sql = "SELECT * FROM Paciente WHERE cpf = ?";
        Paciente paciente = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                paciente = resultsetToEntity(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(paciente);
    }

    @Override
    public List<Paciente> findByNome(String nome) {
        String sql = "SELECT * FROM Paciente WHERE nome = ?;";
        List<Paciente> pacientes = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                pacientes.add(resultsetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    @Override
    public Integer create(Paciente paciente) {
        String sql = "INSERT INTO Paciente (" +
                "nome, " +
                "cpf, " +
                "nascimento, " +
                "sexo, " +
                "email, " +
                "telefone, " +
                "circunferencia, " +
                "altura, " +
                "peso, " +
                "historico, " +
                "intolerancia_lactose," +
                "alergias," +
                "observacoes, " +
                "objetivos, " +
                "intolerancia_gluten, " +
                "diabetes" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf().getNumber());
            stmt.setString(3, paciente.getDataNascimento().toString());
            stmt.setString(4, paciente.getSexo().toString());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getTelefone());
            stmt.setInt(7, paciente.getCircunferencia());
            stmt.setInt(8, paciente.getAltura());
            stmt.setDouble(9, paciente.getPeso());
            stmt.setString(10, paciente.getHistoricoClinicoGeral());
            stmt.setString(11, paciente.getIntoleranciaLactose().toString());
            stmt.setString(12, paciente.getAlergias());
            stmt.setString(13, paciente.getObservacoesGerais());
            stmt.setString(14, paciente.getObjetivos());
            stmt.setBoolean(15, paciente.getIntoleranciaGluten());
            stmt.setBoolean(16, paciente.getDiabetes());

            stmt.execute();
            return stmt.getGeneratedKeys().getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Paciente> findOne(Integer key) {
        String sql = "SELECT * FROM Paciente WHERE id = ?";
        Paciente paciente = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                paciente = resultsetToEntity(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(paciente);
    }

    @Override
    public List<Paciente> findAll() {
        String sql = "SELECT * FROM Paciente;";
        List<Paciente> pacientes = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                pacientes.add(resultsetToEntity(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    @Override
    public boolean update(Paciente paciente) {
        String sql = "UPDATE Paciente SET " +
                "nome = ?, " +
                "cpf = ?, " +
                "nascimento = ?, " +
                "sexo = ?, " +
                "email = ?, " +
                "telefone = ?, " +
                "circunferencia = ?, " +
                "altura = ?, " +
                "peso = ?, " +
                "historico = ?, " +
                "intolerancia_lactose = ?," +
                "alergias = ?," +
                "observacoes = ?, " +
                "objetivos = ?, " +
                "intolerancia_gluten = ?, " +
                "diabetes = ?, " +
                "ativado = ?" +
                "WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf().getNumber());
            stmt.setString(3, paciente.getDataNascimento().toString());
            stmt.setString(4, paciente.getSexo().toString());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getTelefone());
            stmt.setInt(7, paciente.getCircunferencia());
            stmt.setInt(8, paciente.getAltura());
            stmt.setDouble(9, paciente.getPeso());
            stmt.setString(10, paciente.getHistoricoClinicoGeral());
            stmt.setString(11, paciente.getIntoleranciaLactose().toString());
            stmt.setString(12, paciente.getAlergias());
            stmt.setString(13, paciente.getObservacoesGerais());
            stmt.setString(14, paciente.getObjetivos());
            stmt.setBoolean(15, paciente.getIntoleranciaGluten());
            stmt.setBoolean(16, paciente.getDiabetes());
            stmt.setBoolean(17, paciente.getAtivado());
            stmt.setInt(18, paciente.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM Paciente WHERE id = ?";

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
    public boolean delete(Paciente paciente) {
        deleteByKey(paciente.getId());
        return false;
    }

    private Paciente resultsetToEntity(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente(
                rs.getInt("circunferencia"),
                rs.getInt("altura"),
                rs.getString("nome"),
                CPF.valueOf(rs.getString("cpf")),
                rs.getString("email"),
                rs.getString("telefone"),
                rs.getString("historico"),
                IntoleranciaLactose.ofString(rs.getString("intolerancia_lactose")),
                rs.getBoolean("intolerancia_gluten"),
                rs.getBoolean("diabetes"),
                rs.getString("alergias"),
                rs.getString("observacoes"),
                rs.getString("objetivos"),
                LocalDate.parse(rs.getString("nascimento")),
                rs.getDouble("peso"),
                Sexo.ofString(rs.getString("sexo")),
                rs.getBoolean("ativado")
        );
        paciente.setId(rs.getInt("id"));

        return paciente;
    }
}
