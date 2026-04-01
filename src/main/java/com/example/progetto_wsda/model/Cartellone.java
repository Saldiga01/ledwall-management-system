package com.example.progetto_wsda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cartelloni")
public class Cartellone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartellone")
    private Integer id;

    @Column(name = "path_cartellone")
    private String pathCartellone;

    @Column(name = "durata")
    private Integer durata;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathCartellone() {
        return pathCartellone;
    }

    public void setPathCartellone(String pathCartellone) {
        this.pathCartellone = pathCartellone;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }
}
