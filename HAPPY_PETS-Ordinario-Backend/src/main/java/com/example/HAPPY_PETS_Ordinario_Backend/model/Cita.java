package com.example.HAPPY_PETS_Ordinario_Backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
@Data
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 100)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "id_veterinario", referencedColumnName = "id_veterinario")
    private Veterinario veterinario;

    @Column(length = 20)
    private String estado; // Pendiente, Completada, Cancelada
}