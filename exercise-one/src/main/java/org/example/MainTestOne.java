package org.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainTestOne {

    static PostgreSQLDataSources app = new PostgreSQLDataSources();
    static Connection conn = null;

    public static void main(String[] args) throws IOException, SQLException {
        conn = app.connect();
        String file = "./resources/cars.csv";

        testInsertMethod1(file);
        testInsertMethod2(file);
        testInsertDao(file);
        testInsertAllDao(file);

        // Using Apache Beam
        /*
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline pipeline       = Pipeline.create(options);
        testInsertApacheBeam(pipeline, file);

         */
    }

    private static void testInsertMethod1(String file) throws SQLException, IOException {

        String sql = "INSERT INTO "
                + "car(car_name) "
                + "VALUES(?)";

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

    private static void testInsertMethod2(String file) throws IOException, SQLException {

        String sql = "INSERT INTO "
                + "car(car_name) "
                + "VALUES(?)";

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

    private static void testInsertDao(String file) throws IOException {
        Dao<Car> carDao = new CarDao(conn);
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

    private static void testInsertAllDao(String file) throws IOException, SQLException {
        List<Car> allCars = new ArrayList<>();
        Files.readAllLines(Paths.get(file)).stream().forEach(e -> {
            String[] columnValue = e.split(";");
            allCars.add(new Car(null, columnValue[0]));
        });
        allCars.forEach(System.out::println);
        Dao<Car> carDao = new CarDao(conn);
        carDao.saveAll(allCars);
    }

    private static void testInsertApacheBeam(Pipeline pipeline, String file) {
        pipeline
                .apply("Read CSV file", TextIO.read().from(file))
                .apply(Regex.split(";"))
                .apply(TextIO.write().to("src/main/resources/output.txt"));

        pipeline.run().waitUntilFinish();
    }

}
