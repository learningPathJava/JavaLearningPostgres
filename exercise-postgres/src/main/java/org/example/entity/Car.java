package org.example.entity;

import lombok.AllArgsConstructor;
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
}
