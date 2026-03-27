package org.example.city.repository;

import org.example.city.dto.CityStatByDept;
import org.example.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("""
            SELECT
                new org.example.city.dto.CityStatByDept(
                    d.code,
                    d.name,
                    COUNT(c),
                    COALESCE(SUM(c.population), 0)
                )
            FROM City c JOIN c.department d
            GROUP BY d.code, d.name
            """)
    List<CityStatByDept> statCityByDepartment();
}
