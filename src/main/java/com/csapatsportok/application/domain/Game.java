package com.csapatsportok.application.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"away_team_id" , "home_team_id"})})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @NotNull
    private Team homeTeam;

    @OneToOne
    @NotNull
    private Team awayTeam;

    @NotNull
    private int numHomeGoals;

    @NotNull
    private int numAwayGoals;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public String getResult() {
        return numHomeGoals + "-" + numAwayGoals;
    }

    public String getTeamsAndResult() {
        return homeTeam.getName() + "-" + awayTeam.getName() + ": " + getResult();
    }
}