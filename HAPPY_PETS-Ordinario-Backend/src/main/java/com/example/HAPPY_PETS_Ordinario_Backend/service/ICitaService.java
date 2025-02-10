package com.example.HAPPY_PETS_Ordinario_Backend.service;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Cita;

import java.util.List;
import java.util.Optional;

public interface ICitaService {
    List<Cita> findAll();
    Optional<Cita> findById(Long id);
    Cita save (Cita cita);
    Optional<Cita> update (Cita cita, Long id);
    void remove(Long id);
    Optional<Cita> updateEstado(Long id, String estado);
}
