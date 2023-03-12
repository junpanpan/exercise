package com.exercise.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "TOURNAMENTS")
public class Tournament {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotEmpty(message = "name can't be empty.")
    private String name;
    @NotNull
    @Min(100)
    private int prize;
    @NotNull
    private String currency;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Player> players;

    public Tournament() {
    }

    public Tournament(String name, int prize, String currency) {
        this.name = name;
        this.prize = prize;
        this.currency = currency;
    }

    public Tournament(String name, int prize) {
        this(name, prize, "EUR");
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonIgnore
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                    "id=" + this.id +
                    ", name=" + this.name +
                    ", prize=" + this.prize +
                    ", currency= " + this.currency +
                    "}";
    }
}
