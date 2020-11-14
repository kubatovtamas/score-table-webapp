package com.csapatsportok.application.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team homeTeam;

    @OneToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team awayTeam;

    private int numHomeGoals;
    private int numAwayGoals;
    private Date date;

    public String getResult() {
        return numHomeGoals + "-" + numAwayGoals;
    }

    public String getTeams() {
        return homeTeam.getName() + "-" + awayTeam.getName();
    }
}