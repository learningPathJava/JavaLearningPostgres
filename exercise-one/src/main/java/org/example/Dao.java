package org.example;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
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
    Optional saveAll(List<T> t) throws SQLException;
}
