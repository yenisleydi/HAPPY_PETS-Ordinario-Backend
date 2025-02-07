package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Cita;
import com.example.HAPPY_PETS_Ordinario_Backend.service.ICitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private ICitaService iCitaService;

    @GetMapping
    public ResponseEntity<List<Cita>> list() {
        List<Cita> citas = iCitaService.findAll();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Cita> citaOptional = iCitaService.findById(id);
        return citaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Cita cita, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
    
        try {
            Cita citaGuardada = iCitaService.save(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(citaGuardada);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear cita");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cita cita, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }

        Optional<Cita> citaActualizada = iCitaService.update(cita, id);
        return citaActualizada.map(citaObj -> ResponseEntity.ok().body(citaObj))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Cita> citaOptional = iCitaService.findById(id);
        if (citaOptional.isPresent()) {
            iCitaService.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
