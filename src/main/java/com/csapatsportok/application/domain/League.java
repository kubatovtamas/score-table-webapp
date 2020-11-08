package com.csapatsportok.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "league")
    private List<Team> teams;
}
