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

        System.out.println("Start");

        String file = "src/main/resources/cars.csv";
        testInsertMethod1(file);
        testInsertMethod2(file);
        testInsertDao(file);
    }

    private static void testInsertMethod1(String file) throws SQLException, IOException {
        //create connection for a server installed in localhost, with a user "root" with no password

        String url = "jdbc:postgresql://localhost/sampledb";
        String user = "violeta.domnitanu";
        String password = "vio";

        String sql = "INSERT INTO "
                + "car(car_name) "
                + "VALUES(?)";

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
    }
    private static void testInsertMethod2(String file) throws IOException, SQLException {
        //create connection for a server installed in localhost, with a user "root" with no password

        String url = "jdbc:postgresql://localhost/sampledb";
        String user = "violeta.domnitanu";
        String password = "vio";

        String sql = "INSERT INTO "
                + "car(car_name) "
                + "VALUES(?)";

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
    }
    private static void testInsertDao(String file) throws IOException {
        Dao<Car> carDao = new CarDao();
        Files.readAllLines(Paths.get(file)).parallelStream().forEach(e -> {
            String[] values = e.split(";");
            System.out.println(values[0]);
            try {
                carDao.save(new Car(null, values[0]));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }


}
