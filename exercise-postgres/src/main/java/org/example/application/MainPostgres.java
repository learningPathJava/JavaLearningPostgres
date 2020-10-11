package org.example.application;

import org.example.PostgreSQLDataSources;
import org.example.entity.Car;
import org.example.service.CarServiceImpl;
import org.example.service.Service;

import java.sql.Connection;
import java.sql.SQLException;

public class MainPostgres {
    public static void main(String[] args) throws SQLException {

        PostgreSQLDataSources app = new PostgreSQLDataSources();;
        Connection conn = app.connect();

        Service<Car, Connection> carService = new CarServiceImpl();
        carService.getAll(conn).forEach(System.out::println);
    }
}
