package org.example.city.controller;

import jakarta.validation.Valid;
import org.example.city.dto.CityDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/city")
public class CityController {

    @PostMapping
    public CityDto addCity(@Valid CityDto city){
        // TODO : persist data
        return city;
    }

    @GetMapping("search/byDept/{codeDept}")
    public List<CityDto> searchByDept(String codeDept){
        return List.of();
    }
}
