package geo;

public class CityAdapter {

    public static CityFr cityFromArray(String[] array){
        return CityFr.builder()
                .name(array[2])
                .inseeCode(array[1])
                .zipcode(array[20])
                .departmentCode(array[12])
                .population(Integer.parseInt(array[29]))
                .longitudeCentroid(array[39].isEmpty() ? null : Double.parseDouble(array[39]))
                .latitudeCentroid(array[38].isEmpty() ? null : Double.parseDouble(array[38]))
                .build();
    }
}
