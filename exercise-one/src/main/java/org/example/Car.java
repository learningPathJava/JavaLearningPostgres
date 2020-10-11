package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * container for car data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DefaultSchema(JavaFieldSchema.class)
public class Car implements java.io.Serializable {
    private Integer id;
    private String carName;
}
