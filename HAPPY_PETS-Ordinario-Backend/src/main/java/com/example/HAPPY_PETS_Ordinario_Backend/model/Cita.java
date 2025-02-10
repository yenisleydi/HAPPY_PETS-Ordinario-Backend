package com.example.HAPPY_PETS_Ordinario_Backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "cita")
@Data
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;  

    
    @Column(nullable = false)
    private String hora;   


    @ManyToOne
    @JoinColumn(name = "id_duenio", referencedColumnName = "id_duenio")
    private Duenio duenio;
    
    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "id_veterinario", referencedColumnName = "id_veterinario")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    private ServicioVeterinaria servicioVeterinaria;

    @ManyToOne
    @JoinColumn(name = "id_duenio", referencedColumnName = "id_duenio")
    private Duenio duenio;

    @Column(length = 20)
    private String estado; // Pendiente, Completada, Cancelada


}
