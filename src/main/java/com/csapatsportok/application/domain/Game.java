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
    private Long homeTeamId;
    private Long awayTeamId;
    private int numHomeGoals;
    private int numAwayGoals;
    private LocalDateTime date;
}