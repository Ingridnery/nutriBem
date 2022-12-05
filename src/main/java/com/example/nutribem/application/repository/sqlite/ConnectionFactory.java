package com.example.nutribem.application.repository.sqlite;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ConnectionFactory implements AutoCloseable {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static Connection createConnection() {
        try {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:database.db?foreign_keys=on");

            if (Objects.isNull(connection))
                connection = dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static PreparedStatement createPreparedStatement(String sql) {
        try {
            preparedStatement = createConnection().prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    public static Statement createStatement() {
        try {
            statement = createConnection().createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(preparedStatement))
            preparedStatement.close();

        if (Objects.nonNull(statement))
            statement.close();

        if (Objects.nonNull(connection))
            connection.close();
    }
}
