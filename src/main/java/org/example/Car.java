package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * container for car data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Integer id;
    private String carName;
}
