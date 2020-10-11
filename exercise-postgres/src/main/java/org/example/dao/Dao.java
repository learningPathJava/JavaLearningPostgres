package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T, C>{
    Optional<T> get(int id);
    List<T> getAll(C c) throws SQLException;
    Optional<Integer> insert(List<T> t);
    boolean update(List<T> t);
    boolean delete(List<T> t);
}
