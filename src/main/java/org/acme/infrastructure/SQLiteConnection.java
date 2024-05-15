package org.acme.infrastructure;

import jakarta.enterprise.context.ApplicationScoped;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class SQLiteConnection {

    private static final String URL = "jdbc:sqlite:resources//DBSqlite/teste.db";

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
        }
    }
}
