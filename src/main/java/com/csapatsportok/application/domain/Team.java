package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true, nullable = false)
    @NotBlank(message = "Name may not be blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "league_id")
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    private League league;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public String toString() {
        return "Team(id=" + this.getId() + ", name=" + this.getName() + ")";
    }


}
