package com.example.progetto_wsda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "impianti")
public class Impianto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_impianto")
    private Integer id;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "latitudine")
    private Double latitudine;

    @Column(name = "longitudine")
    private Double longitudine;

    @Column(name = "path_palinsesto")
    private String pathPalinsesto;

    @Column(name = "stato")
    private Integer stato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }

    public String getPathPalinsesto() {
        return pathPalinsesto;
    }

    public void setPathPalinsesto(String pathPalinsesto) {
        this.pathPalinsesto = pathPalinsesto;
    }

    public Integer getStato() {
        return stato;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }
}