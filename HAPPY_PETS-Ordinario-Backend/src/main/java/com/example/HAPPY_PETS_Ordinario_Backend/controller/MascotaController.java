package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Mascota;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IDuenioService;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IMascotaService;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IVeterinarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private IMascotaService iMascotaService;

    @Autowired
    IDuenioService iDuenioService;

    @GetMapping
    public List<Mascota> list(){
        return iMascotaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Mascota> mascotaOptional = iMascotaService.findById(id);
        if (mascotaOptional.isPresent()){
            Mascota mascota = mascotaOptional.orElseThrow();
            // Asegúrate de que el dueño esté cargado
            System.out.println(mascota.getDuenio());
            return ResponseEntity.ok(mascota);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/dueno/{idDueno}")
    public ResponseEntity<?> getByDuenoId(@PathVariable Long idDueno) {
        List<Mascota> mascotas = iMascotaService.findByDuenoId(idDueno);
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Mascota mascota, BindingResult result) {
        if (result.hasErrors()){
            return validation(result);
        }

        // Verifica que se envíe el objeto duenio con su id
        if (mascota.getDuenio() == null || mascota.getDuenio().getId() == null) {
            return ResponseEntity.badRequest().body("El dueño es obligatorio");
        }

        // Recupera la entidad Duenio completa
        Optional<Duenio> duenioOpt = iDuenioService.findById(mascota.getDuenio().getId());
        if (!duenioOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Dueño no encontrado");
        }

        // Asigna la entidad completa del dueño a la mascota
        mascota.setDuenio(duenioOpt.get());

        // Guarda la mascota
        Mascota mascotaGuardada = iMascotaService.save(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaGuardada);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Mascota mascota, BindingResult result, @PathVariable Long id){
        Optional<Mascota> mascotaOptional = iMascotaService.update(mascota, id);
        if (mascotaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mascotaOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove (@PathVariable Long id){
        Optional<Mascota> mascotaOptional = iMascotaService.findById(id);
        if (mascotaOptional.isPresent()) {
            iMascotaService.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation (BindingResult result){
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.notFound().build();
    }

}
