package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    List<Opinion> opiniones = new ArrayList<>();

    public void addOpinion(Opinion o){
        o.setPelicula(this);
        this.opiniones.add(o);
    }

}