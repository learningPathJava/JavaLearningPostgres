package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcConnection {

    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection() {

        String url = "jdbc:postgresql://localhost:5432/sampledb";
        String user = "violeta.domnitanu";
        String password = "vio";

        try {
            connection = Optional.ofNullable(
                    DriverManager.getConnection(url, user, password));
        } catch (SQLException ex) {
            //
        }

        return connection;
    }
}
