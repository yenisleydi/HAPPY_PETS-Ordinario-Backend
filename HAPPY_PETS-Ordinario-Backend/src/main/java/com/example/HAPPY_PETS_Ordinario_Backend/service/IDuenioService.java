package com.example.HAPPY_PETS_Ordinario_Backend.service;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;

import java.util.List;
import java.util.Optional;

public interface IDuenioService {
    List<Duenio> findAll();
    Optional<Duenio> findById(Long id);
    Duenio save (Duenio duenio);
    Optional<Duenio> update (Duenio duenio, Long id);
    void remove(Long id);
}
