package com.csapatsportok.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "country")
    private List<League> leagues;

    public Country() {

    }

    public Country(String name) {
        this.name = name;
    }
}
