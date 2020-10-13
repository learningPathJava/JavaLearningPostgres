package org.example.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDataSources {

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static synchronized Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost/sampledb";
            String user = "violeta.domnitanu";
            String password = "vio";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully./n" + conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        PostgreSQLDataSources app = new PostgreSQLDataSources();
        app.connect();
    }
}
