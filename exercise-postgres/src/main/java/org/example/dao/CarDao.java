package org.example.dao;

import org.example.entity.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAll();
    boolean add(Car car) throws Exception;
}
