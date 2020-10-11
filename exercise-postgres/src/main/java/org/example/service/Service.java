package org.example.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Service <T, C> {
    List<T> getAll(C c) throws SQLException;
    Optional<Integer> insert(C c, List<T> t) throws SQLException;
}
