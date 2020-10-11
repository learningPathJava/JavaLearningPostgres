package org.example.dao;

import org.example.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements Dao<Car, Connection> {

    @Override
    public Optional<Car> get(Connection conn, int id) {
        return Optional.empty();
    }

    @Override
    public List<Car> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM car";
        try (Statement stmt = conn.createStatement()) {
            List<Car> carList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                carList.add(new Car(rs.getInt(1), rs.getString(2)));
            }
            return carList;
        }
    }

    @Override
    public Optional<Integer> insert(Connection conn, List<Car> carList) throws SQLException {
        String insertCarSQL = "INSERT INTO " + "car(car_name) " + "VALUES(?)";

        conn.setAutoCommit(false);
        try (PreparedStatement stmt = conn.prepareStatement(insertCarSQL)) {
            for ( Car car : carList) {
                stmt.setString(1, car.getCarName());
                stmt.addBatch();
            }

            // Execute Batch
            stmt.executeBatch();
            conn.commit();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Connection conn, List<Car> t) {
        return false;
    }

    @Override
    public boolean delete(Connection conn, List<Car> t) {
        return false;
    }
}
