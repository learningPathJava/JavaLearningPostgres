package org.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import java.io.Serializable;

/**
 * container for car data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DefaultSchema(JavaFieldSchema.class)
public class Car implements Serializable {
    private Integer id;
    private String carName;
    private Double carMPG;
    private Integer carCylinders;
    private Double carDisplacement;
    private Double carHorsepower;
    private Double carWeight;
    private Double carAcceleration;
    private Integer carModel;
    private String carOrigin;

    @Builder
    public Car(String[] array) {
        this.carName = array[0];
        this.carMPG = Double.valueOf(array[1]);
        this.carCylinders = Integer.valueOf(array[2]);
        this.carDisplacement = Double.valueOf(array[3]);
        this.carHorsepower = Double.valueOf(array[4]);
        this.carWeight = Double.valueOf(array[5]);
        this.carAcceleration = Double.valueOf(array[6]);
        this.carModel = Integer.valueOf(array[7]);
        this.carOrigin = String.valueOf(array[8]);
    }
}
