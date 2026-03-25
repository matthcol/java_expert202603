package geo;

import lombok.*;
import util.annotations.Min;
import util.annotations.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"name", "departmentCode", "population"})
public class CityFr {
    @NotBlank
    private String name;

    @NotBlank
    private String inseeCode;

    @NotBlank
    private String zipcode;

    @NotBlank
    private String departmentCode;

    @Min(0)
    private int population;

    private Double longitudeCentroid;
    private Double latitudeCentroid;
}
