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
    private LocalTime hora;   


    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "id_veterinario", referencedColumnName = "id_veterinario")
    private Veterinario veterinario;

    @Column(length = 20)
    private String estado; // Pendiente, Completada, Cancelada


    @ManyToMany
    @JoinTable(
        name = "cita_servicio",
        joinColumns = @JoinColumn(name = "id_cita"),
        inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<ServicioVeterinaria> servicios;
}
