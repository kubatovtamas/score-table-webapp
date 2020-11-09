package com.csapatsportok.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Team homeTeam;
    @OneToOne
    private Team awayTeam;
    private int numHomeGoals;
    private int numAwayGoals;
    private LocalDateTime date;
}