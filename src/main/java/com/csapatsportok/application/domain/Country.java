package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("country")
    private List<League> leagues;

    public String toString() {
        return "Country(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
