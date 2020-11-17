package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name may not be blank")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("players")
    private Team team;

    public String toString() {
        return "Player(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    public String getNameAndTeam() {
        return name + " - " + (team == null ? "Free Agent" : team.getName());
    }
}
