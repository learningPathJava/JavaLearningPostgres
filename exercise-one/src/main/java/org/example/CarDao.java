package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CarDao implements Dao<Car> {

    Connection conn = null;

    public CarDao (Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Car> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Car> getAll() {
        return null;
    }

    @Override
    public Optional save(Car car) throws SQLException {

        String insertCarSQL = "INSERT INTO " + "car(car_name) " + "VALUES(?)";

        // try-with-resource statement will auto close the connection
        try (PreparedStatement stmt = conn.prepareStatement(insertCarSQL))
        {
            // Create a statement using connection object
            stmt.setString(1, car.getCarName());

            // Execute the query or update query
            stmt.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            System.out.println(e);
        }

        return Optional.empty();
    }

    /*
     * Use JDBC Batch Statements
     *
     * JDBC API provides addBatch() method to add SQL queries into a batch
     * and executeBatch() to send batch queries for execution.
     *
     * JDBC batch update potentially reduces the number of database round trip
     * which results in significant performance.
     */
    public Optional saveAll(List<Car> cars) throws SQLException {

        String insertCarSQL = "INSERT INTO " + "car(car_name) " + "VALUES(?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertCarSQL)) {
            for ( Car car : cars) {
                stmt.setString(1, car.getCarName());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
        return Optional.empty();
    }
}
