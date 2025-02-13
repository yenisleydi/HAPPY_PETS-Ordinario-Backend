package com.example.HAPPY_PETS_Ordinario_Backend.repository;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Mascota;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {
    
    List<Mascota> findByDuenioId(Long idDueno);
}
