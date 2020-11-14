package com.csapatsportok.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Data

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true, nullable = false)
    @NotBlank(message = "Name may not be blank")
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnoreProperties("country")
//    @Cascade({CascadeType.ALL})
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<League> leagues;

    public String toString() {
        return "Country(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
