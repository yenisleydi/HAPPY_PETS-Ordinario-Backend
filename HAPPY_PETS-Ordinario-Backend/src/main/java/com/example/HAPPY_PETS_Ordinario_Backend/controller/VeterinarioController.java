package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;
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
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "http://localhost:3000") 
public class VeterinarioController {

    @Autowired
    private IVeterinarioService iVeterinarioService;

    @GetMapping
    public List<Veterinario> list(){
        return iVeterinarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Veterinario> veterinarioOptional = iVeterinarioService.findById(id);
        if (veterinarioOptional.isPresent()){
            return ResponseEntity.ok(veterinarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Veterinario veterinario, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        Veterinario veterinario1 = iVeterinarioService.save(veterinario);
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinario1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Veterinario veterinario, BindingResult result, @PathVariable Long id){
        Optional<Veterinario> veterinarioOptional = iVeterinarioService.update(veterinario, id);
        if (veterinarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(veterinarioOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove (@PathVariable Long id){
        Optional<Veterinario> veterinarioOptional = iVeterinarioService.findById(id);
        if (veterinarioOptional.isPresent()) {
            iVeterinarioService.remove(id);
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
