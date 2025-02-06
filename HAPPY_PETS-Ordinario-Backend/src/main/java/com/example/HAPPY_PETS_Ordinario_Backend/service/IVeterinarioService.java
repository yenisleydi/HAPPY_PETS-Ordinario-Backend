package com.example.HAPPY_PETS_Ordinario_Backend.service;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;

import java.util.List;
import java.util.Optional;

public interface IVeterinarioService {
    List<Veterinario> findAll();
    Optional<Veterinario> findById(Long id);
    Veterinario save (Veterinario veterinario);
    Optional<Veterinario> update (Veterinario veterinario, Long id);
    void remove(Long id);
}
