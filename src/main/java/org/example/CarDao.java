package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class CarDao implements Dao<Car> {

    String url = "jdbc:postgresql://localhost/sampledb";
    String user = "violeta.domnitanu";
    String password = "vio";

    @Override
    public Optional<Car> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Car> getAll() {
        return null;
    }

    @Override
    public Optional save(Car car) throws SQLException {

        String sql = "INSERT INTO "
                + "car(car_name) "
                + "VALUES(?)";

        try( Connection conn = DriverManager.getConnection(url, user, password) ) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, car.getCarName());
                stmt.executeUpdate();
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Car car, String[] params) {
        //
    }

    @Override
    public void delete(Car car) {
        //
    }
}
