package com.example.HAPPY_PETS_Ordinario_Backend.repository;

import com.example.HAPPY_PETS_Ordinario_Backend.model.ComentariosOpiniones;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosOpinionesRepository extends MongoRepository<ComentariosOpiniones, String> {
}
