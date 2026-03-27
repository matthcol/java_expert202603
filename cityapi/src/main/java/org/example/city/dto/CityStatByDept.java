package org.example.city.dto;

public record CityStatByDept(
        String code,
        String name,
        long nbCity,
        long totalPopulation
) {
}
