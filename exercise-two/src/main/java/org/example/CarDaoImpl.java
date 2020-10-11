package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarDaoImpl implements CarDao {

    Connection conn;

    public CarDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(Car car) throws SQLException {

        String insertCarSQL = "INSERT INTO " + "car(car_name) " + "VALUES(?)";

        // try-with-resource statement will auto close the connection
        try (PreparedStatement stmt = conn.prepareStatement(insertCarSQL)) {
            // Create a statement using connection object
            stmt.setString(1, car.getCarName());

            // Execute the query or update query
            stmt.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            System.out.println(e);
        }

        return true;
    }
}
