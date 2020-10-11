package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTestTwo {

    public static void main(String[] args) throws IOException {
        String file = "./resources/cars.csv";
        testInsertDao(file);
    }

    private static void testInsertDao(String file) throws IOException {

        PostgreSQLDataSources app = new PostgreSQLDataSources();

        System.out.println("dataSource = " + app.connect());
        CarDao carDao = new CarDaoImpl(app.connect());
        Files.readAllLines(Paths.get(file)).parallelStream().forEach(e -> {
            String[] values = e.split(";");
            try {
                carDao.add(new Car(null, values[0]));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });

    }
}
