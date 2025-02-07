package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.ComentariosOpiniones;
import com.example.HAPPY_PETS_Ordinario_Backend.service.ComentariosOpinionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
public class ComentariosOpinionesController {

    @Autowired
    private ComentariosOpinionesService service;

    // Obtener todos los comentarios
    @GetMapping
    public ResponseEntity<List<ComentariosOpiniones>> obtenerTodos() {
        System.out.println("📢 [GET] /comentarios - Obteniendo todos los comentarios...");
        List<ComentariosOpiniones> comentarios = service.obtenerTodos();
        System.out.println("✅ Se encontraron " + comentarios.size() + " comentarios.");
        return ResponseEntity.ok(comentarios);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ComentariosOpiniones> obtenerPorId(@PathVariable String id) {
        System.out.println("📢 [GET] /comentarios/" + id + " - Buscando comentario...");
        Optional<ComentariosOpiniones> comentario = service.obtenerPorId(id);

        if (comentario.isPresent()) {
            System.out.println("✅ Comentario encontrado: " + comentario.get());
            return ResponseEntity.ok(comentario.get());
        } else {
            System.out.println("❌ Comentario con ID " + id + " no encontrado.");
            return ResponseEntity.notFound().build();
        }
    }

    // Crear o actualizar un comentario
    @PostMapping
    public ResponseEntity<ComentariosOpiniones> guardar(@RequestBody ComentariosOpiniones comentariosOpiniones) {
        System.out.println("📢 [POST] /comentarios - Guardando comentario...");
        System.out.println("📋 Datos recibidos: " + comentariosOpiniones);

        ComentariosOpiniones guardado = service.guardar(comentariosOpiniones);

        System.out.println("✅ Comentario guardado con ID: " + guardado.getId());
        return ResponseEntity.ok(guardado);
    }

    // Eliminar un comentario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable String id) {
        System.out.println("📢 [DELETE] /comentarios/" + id + " - Eliminando comentario...");
        service.eliminarPorId(id);
        System.out.println("✅ Comentario eliminado con éxito.");
        return ResponseEntity.noContent().build();
    }
}
