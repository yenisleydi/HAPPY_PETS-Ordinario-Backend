package com.example.HAPPY_PETS_Ordinario_Backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mascota")
@Data
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long id;

    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(length = 50)
    private String raza;

    @Column(length = 3)
    private int edad;

    private double peso;

    @Column(length = 10)
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "id_duenio", referencedColumnName = "id_duenio")
    private Duenio duenio;
}
