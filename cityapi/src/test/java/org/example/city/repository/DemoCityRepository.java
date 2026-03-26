package org.example.city.repository;

import jakarta.persistence.EntityManager;
import org.example.city.entity.City;
import org.example.city.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql
public class DemoCityRepository {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EntityManager entityManager;  // cache hibernate

    @Test
    void demo(){
        var dept = Department.builder()
                .code("64")
                .name("Pyrénées Atlantiques")
                .build();
        departmentRepository.saveAndFlush(dept);

        var city = City.builder()
                .name("Pau")
                .department(dept)
                .build();
        cityRepository.save(city); // object into cache Hibernate (flag: toBeSaved)
        cityRepository.flush(); // synchro cache : SQL : INSERT

        entityManager.clear(); // clear cache Hibernate

        var cities = cityRepository.findAll(); // SQL : SELECT
        System.out.println(cities);

        cities.forEach(
                c -> c.setPopulation(10_000) // Hibernate: flag = updated in cache not in database
        );
        cityRepository.flush(); // SQL : UPDATE
    }
}
