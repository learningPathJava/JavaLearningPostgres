package org.example.service;

import org.example.dao.CarDaoImpl;
import org.example.dao.Dao;
import org.example.entity.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl implements Service<Car, Connection> {

    Dao<Car, Connection> car = new CarDaoImpl();

    @Override
    public List<Car> getAll(Connection conn) throws SQLException {
        return car.getAll(conn);
    }
}
