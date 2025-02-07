package com.example.HAPPY_PETS_Ordinario_Backend.service;

import com.example.HAPPY_PETS_Ordinario_Backend.model.ComentariosOpiniones;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.ComentariosOpinionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComentariosOpinionesService {

    @Autowired
    private ComentariosOpinionesRepository repository;

    // Obtener todos los comentarios
    public List<ComentariosOpiniones> obtenerTodos() {
        return repository.findAll();
    }

    // Obtener por ID
    public Optional<ComentariosOpiniones> obtenerPorId(String id) {
        return repository.findById(id);
    }

    // Guardar o actualizar
    public ComentariosOpiniones guardar(ComentariosOpiniones comentariosOpiniones) {
        return repository.save(comentariosOpiniones);
    }

    // Eliminar por ID
    public void eliminarPorId(String id) {
        repository.deleteById(id);
    }
}
