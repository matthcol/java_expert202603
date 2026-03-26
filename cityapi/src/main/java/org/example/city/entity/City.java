package org.example.city.entity;

import jakarta.persistence.*;
import lombok.*;

// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
// JPA
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String zipcode;
    private String inseeCode;
    private Integer population;
    private Double longitudeCentroid;
    private Double latitudeCentroid;

    @ManyToOne
    private Department department;
}
