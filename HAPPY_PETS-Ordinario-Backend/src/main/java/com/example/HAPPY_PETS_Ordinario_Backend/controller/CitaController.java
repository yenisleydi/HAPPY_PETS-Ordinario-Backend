package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Cita;
import com.example.HAPPY_PETS_Ordinario_Backend.model.EstadoRequest;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Mascota;
import com.example.HAPPY_PETS_Ordinario_Backend.model.ServicioVeterinaria;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.MascotaRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.ServicioVeterinariaRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.VeterinarioRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.service.ICitaService;

import jakarta.transaction.Transactional;
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
    private MascotaRepository mascotaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private ServicioVeterinariaRepository servicioVeterinariaRepository;

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
        // Asignar los objetos correspondientes a partir de los IDs recibidos
        Mascota mascota = mascotaRepository.findById(cita.getMascota().getId()).orElse(null);
        Veterinario veterinario = veterinarioRepository.findById(cita.getVeterinario().getId()).orElse(null);
        ServicioVeterinaria servicio = servicioVeterinariaRepository.findById(cita.getServicioVeterinaria().getId()).orElse(null);

        // Asignar los objetos encontrados a la cita
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setServicioVeterinaria(servicio);

        // Guardar la cita
        Cita citaGuardada = iCitaService.save(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaGuardada);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear cita");
    }
}

@PutMapping("/{id}")
public ResponseEntity<Cita> actualizarEstado(@PathVariable Long id, @RequestBody EstadoRequest estadoRequest) {
    Optional<Cita> citaActualizadaOptional = iCitaService.updateEstado(id, estadoRequest.getEstado());

    // Si la cita fue encontrada y actualizada
    if (citaActualizadaOptional.isPresent()) {
        return ResponseEntity.ok(citaActualizadaOptional.get()); // Devuelve la cita actualizada
    } else {
        return ResponseEntity.notFound().build(); // Si no se encuentra la cita, devuelve 404
    }
}



    
    @PutMapping("/{id}/update")
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
