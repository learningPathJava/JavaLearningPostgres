package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T, C>{
    Optional<T> get(C c, int id);
    List<T> getAll(C c) throws SQLException;
    Optional<Integer> insert(C c, List<T> t) throws SQLException;
    boolean update(C c, List<T> t) throws SQLException;
    boolean delete(C c, List<T> t) throws SQLException;
}
