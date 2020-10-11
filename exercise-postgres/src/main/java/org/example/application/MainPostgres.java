package org.example.application;

import org.example.PostgreSQLDataSources;
import org.example.entity.Car;
import org.example.service.CarServiceImpl;
import org.example.service.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class MainPostgres {

    private static final Logger __logger = Logger.getLogger("MainPostgres");

    public static void main(String[] args) throws SQLException {

        PostgreSQLDataSources app = new PostgreSQLDataSources();
        Connection conn = app.connect();

        Service<Car, Connection> carService = new CarServiceImpl();
        List<Car> carList = carService.getAll(conn);
        __logger.info(carList::toString);
    }
}
