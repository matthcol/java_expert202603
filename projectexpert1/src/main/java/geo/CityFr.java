package geo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"name", "departmentCode", "population"})
public class CityFr {
    private String name;
    private String inseeCode;
    private String zipcode;
    private String departmentCode;
    private int population;
    private Double longitudeCentroid;
    private Double latitudeCentroid;
}
