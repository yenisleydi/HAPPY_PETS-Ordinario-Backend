package com.example.HAPPY_PETS_Ordinario_Backend.service;

import com.example.HAPPY_PETS_Ordinario_Backend.model.ServicioVeterinaria;

import java.util.List;
import java.util.Optional;

public interface IServicioVeterinariaService {
    List<ServicioVeterinaria> findAll();
    Optional<ServicioVeterinaria> findById(Long id);
    ServicioVeterinaria save (ServicioVeterinaria servicioVeterinaria);
    Optional<ServicioVeterinaria> update (ServicioVeterinaria servicioVeterinaria, Long id);
    void remove(Long id);
}
