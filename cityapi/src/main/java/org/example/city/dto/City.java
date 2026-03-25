package org.example.city.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.city.validation.MaxDouble;
import org.example.city.validation.MinDouble;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of={"name", "department", "population"})
public class City {
    @NotBlank
    private String name;

    @NotNull
    @Size(min = 5, max = 5)
    private String zipcode;

    @NotNull
    @Size(min = 5, max = 5)
    private String inseeCode;

    @NotNull
    @Size(min = 2, max = 3)
    private String department;

    @Min(0)
    private int population;

    @MaxDouble(180.0)
    @MinDouble(-180.0)
    private Double longitudeCentroid;

    @MaxDouble(90.0)
    @MinDouble(-90.0)
    private Double latitudeCentroid;
}
