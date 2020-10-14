package org.example.application;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult.State;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.PCollection;
import org.example.domain.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class TestInsertWithApache {

    private static final Logger __logger = Logger.getLogger("MainApache");

    public static void main(String[] args) throws IOException {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline pipeline = Pipeline.create(options);

        String file = "./resources/cars.csv";

        // extract data from file
        Collection<Car> carList = csvToList(file);

        // read data and save it into PCollection<Car>
        PCollection<Car> data = pipeline
                .apply(Create.of(carList).withCoder(SerializableCoder.of(Car.class)));

        // Inserting data into PostgreSQL
        //PDone insertData =
                data
                .apply(JdbcIO.<Car>write()
                        // Data Source Configuration for PostgreSQL
                        .withDataSourceConfiguration(JdbcIO.DataSourceConfiguration
                                // Data Source Configuration for PostgreSQL
                                .create("org.postgresql.Driver", "jdbc:postgresql://localhost/sampledb")
                                .withUsername("***")
                                .withPassword("***"))
                        // Statement
                        .withStatement("insert into car " +
                                "(car_name, car_mpg, car_cylinders, car_displacement, car_horsepower, car_weight, car_acceleration, car_model, car_origin) " +
                                "values(?,?,?,?,?,?,?,?,?)")
                        .withPreparedStatementSetter(new JdbcIO.PreparedStatementSetter<Car>() {
                            private static final long serialVersionUID = 1L;

                            public void setParameters(Car element, PreparedStatement query) throws SQLException {
                                query.setString(1, element.getCarName());
                                query.setDouble(2, element.getCarMPG());
                                query.setInt(3, element.getCarCylinders());
                                query.setDouble(4, element.getCarDisplacement());
                                query.setDouble(5, element.getCarHorsepower());
                                query.setDouble(6, element.getCarWeight());
                                query.setDouble(7, element.getCarAcceleration());
                                query.setInt(8, element.getCarModel());
                                query.setString(9, element.getCarOrigin());
                            }
                        })
                );

        // Checking pipeline status
        State pipelineStatus = pipeline.run().waitUntilFinish();
        __logger.info("Pipeline Status: " + pipelineStatus);
        __logger.info("Data Inserted Successfully");
    }

    private static List<Car> csvToList(String file) throws IOException {
        List<Car> carList = new ArrayList<>();
        Files.readAllLines(Paths.get(file)).forEach(e -> {
            String[] columnValue = e.split(";");
            carList.add(new Car(columnValue));
        });
        return carList;
    }
}
