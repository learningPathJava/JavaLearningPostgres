package org.example.service;

import java.sql.SQLException;
import java.util.List;

public interface Service <T, C> {
    List<T> getAll(C c) throws SQLException;
}
