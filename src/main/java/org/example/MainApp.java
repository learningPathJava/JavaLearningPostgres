package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainApp {

    public static void main(String[] args) throws IOException, SQLException {

        System.out.println("Hello");

        // resources/test.txt
        String file = "src/main/resources/cars.csv";
        testInsert(file);
    }

    static void testInsert(String file) throws IOException, SQLException {
        //create connection for a server installed in localhost, with a user "root" with no password

        String url = "jdbc:postgresql://localhost/sampledb";
        String user = "violeta.domnitanu";
        String password = "vio";

        String sql = "INSERT INTO "
                + "car(car_name) "
                + "VALUES(?)";

        /*
        try( Connection conn = DriverManager.getConnection(url, user, password) ) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                for (String line : Files.readAllLines(Paths.get(file))) {
                    // split line
                    String[] values = line.split(";");
                    System.out.println(values[0]);
                    stmt.setString(1, values[0]);
                    stmt.executeUpdate();
                }
            }
        }

         */

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                Files.readAllLines(Paths.get(file)).stream().forEach(e -> {
                    String[] values = e.split(";");
                    System.out.println(values[0]);
                    try {
                        stmt.setString(1, values[0]);
                        stmt.executeUpdate();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
            }
        }


        /*
        Dao<Car> carDao = new CarDao();
        Files.readAllLines(Paths.get(file)).stream().forEach(e -> {
            String[] values = e.split(";");
            System.out.println(values[0]);
            try {
                carDao.save(new Car(null, values[0]));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        */
    }
}
