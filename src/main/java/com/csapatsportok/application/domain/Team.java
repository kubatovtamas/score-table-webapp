package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("teams")
    private League league;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public String toString() {
        return "Team(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
