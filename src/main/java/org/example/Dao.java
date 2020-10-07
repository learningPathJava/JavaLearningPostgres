package org.example;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * DAO pattern
 * CRUD
 */
public interface Dao<T> {
    Optional<T> get(int id);
    Collection<T> getAll();
    Optional save(T t) throws SQLException;
    void update(T t, String[] params);
    void delete(T t);
}
