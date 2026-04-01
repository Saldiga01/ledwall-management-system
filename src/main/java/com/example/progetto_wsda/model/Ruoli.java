package com.example.progetto_wsda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ruoli")
public class Ruoli {
    @Id
    @Column(name = "id_ruolo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
