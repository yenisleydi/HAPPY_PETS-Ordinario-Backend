package com.example.HAPPY_PETS_Ordinario_Backend.service;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Mascota;

import java.util.List;
import java.util.Optional;

public interface IMascotaService {
    List<Mascota> findAll();
    Optional<Mascota> findById(Long id);
    Mascota save (Mascota mascota);
    Optional<Mascota> update (Mascota mascota, Long id);
    void remove(Long id);
}
