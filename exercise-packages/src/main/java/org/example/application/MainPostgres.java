package org.example.application;

import org.example.infrastructure.PostgreSQLDataSources;
import org.example.entity.Car;
import org.example.service.CarServiceImpl;
import org.example.service.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainPostgres {

    private static final Logger __logger = Logger.getLogger("MainPostgres");

    public static void main(String[] args) throws SQLException, IOException {

        Connection conn = PostgreSQLDataSources.connect();

        Service<Car, Connection> carService = new CarServiceImpl();
        List<Car> carList = null;

        // add cars to DB
        carList = csvToList("./resources/cars.csv");
        __logger.info(carList::toString);
        carService.insert(conn,carList);

        // list car from DB
        carList = carService.getAll(conn);
        __logger.info(carList::toString);
    }

    private static List<Car> csvToList(String file) throws IOException {
        List<Car> carList = new ArrayList<>();
        Files.readAllLines(Paths.get(file)).forEach(e -> {
            String[] columnValue = e.split(";");
            carList.add(new Car(null, columnValue[0]));
        });
        return carList;
    }
}
