package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("players")
    private Team team;

    public String toString() {
        return "Player(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
