package com.example.progetto_wsda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "palinsesti")
public class Palinsesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palinsesto")
    private Integer idPalinsesto;

    @Transient
    private String nomePalinsesto;

    @Column(name = "path_Palinsesto")
    private String pathPalinsesto;

    // Getter e Setter
    public Integer getIdPalinsesto() {
        return idPalinsesto;
    }

    public void setIdPalinsesto(Integer idPalinsesto) {
        this.idPalinsesto = idPalinsesto;
    }

    public String getPathPalinsesto() {
        return pathPalinsesto;
    }

    public void setPathPalinsesto(String pathPalinsesto) {
        this.pathPalinsesto = pathPalinsesto;
    }

    public String getNomePalinsesto() {
        return nomePalinsesto;
    }

    public void setNomePalinsesto(String nomePalinsesto) {
        this.nomePalinsesto = nomePalinsesto;
    }
}
