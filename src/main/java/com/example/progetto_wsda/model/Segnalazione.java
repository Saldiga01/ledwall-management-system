package com.example.progetto_wsda.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "segnalazioni")
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_segnalazione")
    private Integer id;

    @Column(name = "ref_idimpianto")
    private Integer refIdimpianto;

    @Column(name = "ref_idpalinsesto")
    private Integer refIdpalinsesto;

    @Column(name = "ref_idcartellone")
    private Integer refIdcartellone;

    @Column(name = "durata")
    private Integer durata;

    @Column(name = "timestamps")
    private Timestamp timestamps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefIdimpianto() {
        return refIdimpianto;
    }

    public void setRefIdimpianto(Integer refIdimpianto) {
        this.refIdimpianto = refIdimpianto;
    }

    public Integer getRefIdpalinsesto() {
        return refIdpalinsesto;
    }

    public void setRefIdpalinsesto(Integer refIdpalinsesto) {
        this.refIdpalinsesto = refIdpalinsesto;
    }

    public Integer getRefIdcartellone() {
        return refIdcartellone;
    }

    public void setRefIdcartellone(Integer refIdcartellone) {
        this.refIdcartellone = refIdcartellone;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public Timestamp getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Timestamp timestamps) {
        this.timestamps = timestamps;
    }
}
