package com.csapatsportok.application.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Player player;

    @OneToOne
    private Team team;

    @OneToOne
    private Game game;
}