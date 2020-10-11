package org.example.dao;

import org.example.entity.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements Dao<Car, Connection> {

    @Override
    public Optional<Car> get(int id) {
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
    public Optional<Integer> insert(List<Car> t) {
        return Optional.empty();
    }

    @Override
    public boolean update(List<Car> t) {
        return false;
    }

    @Override
    public boolean delete(List<Car> t) {
        return false;
    }
}
