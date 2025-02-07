package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Mascota;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;
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

    @GetMapping
    public List<Mascota> list(){
        return iMascotaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Mascota> mascotaOptional = iMascotaService.findById(id);
        if (mascotaOptional.isPresent()){
            return ResponseEntity.ok(mascotaOptional.orElseThrow());
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
    public ResponseEntity<?> create(@Valid @RequestBody Mascota mascota, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        Mascota mascota1 = iMascotaService.save(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(mascota1);
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
