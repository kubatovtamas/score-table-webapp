package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Player player;

    @OneToOne
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Team team;

    @OneToOne
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Game game;
}
