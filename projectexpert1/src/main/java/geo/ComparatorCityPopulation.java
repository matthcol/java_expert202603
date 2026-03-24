package geo;

import java.util.Comparator;

public class ComparatorCityPopulation implements Comparator<CityFr> {
    @Override
    public int compare(CityFr c1, CityFr c2) {
        return c1.getPopulation() - c2.getPopulation();
    }
}
