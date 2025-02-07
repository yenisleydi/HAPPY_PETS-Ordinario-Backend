package com.example.HAPPY_PETS_Ordinario_Backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "duenio")
@Data
public class Duenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_duenio")
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 15)
    private String telefono;

    @Column(nullable = false, length = 70, unique = true)
    private String email;

    @Column(length = 100)
    private String direccion;
}
