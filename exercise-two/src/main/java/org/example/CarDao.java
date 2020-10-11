package org.example;

public interface CarDao {
    /**
     * @param car the customer to be added.
     * @return true if customer is successfully added, false if customer already exists.
     * @throws Exception if any error occurs.
     */
    boolean add(Car car) throws Exception;
}
