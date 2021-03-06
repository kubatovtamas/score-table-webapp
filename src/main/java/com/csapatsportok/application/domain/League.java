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
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true, nullable = false)
    @NotBlank(message = "Name may not be blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Country country;

    @OneToMany(mappedBy = "league", cascade = javax.persistence.CascadeType.REMOVE)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private List<Team> teams;

    public String toString() {
        return "League(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
