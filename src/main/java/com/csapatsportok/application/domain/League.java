package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("leagues")
    private Country country;

    @OneToMany(mappedBy = "league")
    @JsonIgnoreProperties("league")
    private List<Team> teams;

    public String toString() {
        return "League(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
