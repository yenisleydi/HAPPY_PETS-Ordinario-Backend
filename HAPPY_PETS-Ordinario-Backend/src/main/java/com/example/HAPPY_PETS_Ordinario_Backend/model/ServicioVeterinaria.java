package com.example.HAPPY_PETS_Ordinario_Backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "servicio_veterinaria")
@Data
public class ServicioVeterinaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long id;

    @Column(length = 60)
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    private double precio;
}
