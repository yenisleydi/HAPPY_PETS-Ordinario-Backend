package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;
import com.example.HAPPY_PETS_Ordinario_Backend.model.ServicioVeterinaria;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IDuenioService;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IServicioVeterinariaService;
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
@RequestMapping("/servicios")
public class ServicioVeterinariaController {
    @Autowired
    private IServicioVeterinariaService iServicioVeterinariaService;

    @GetMapping
    public List<ServicioVeterinaria> list(){
        return iServicioVeterinariaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<ServicioVeterinaria> servicioVeterinariaOptional = iServicioVeterinariaService.findById(id);
        if (servicioVeterinariaOptional.isPresent()){
            return ResponseEntity.ok(servicioVeterinariaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ServicioVeterinaria servicioVeterinaria, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        ServicioVeterinaria servicioVeterinaria1 = iServicioVeterinariaService.save(servicioVeterinaria);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioVeterinaria1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ServicioVeterinaria servicioVeterinaria, BindingResult result, @PathVariable Long id){
        Optional<ServicioVeterinaria> servicioVeterinariaOptional = iServicioVeterinariaService.update(servicioVeterinaria, id);
        if (servicioVeterinariaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(servicioVeterinariaOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove (@PathVariable Long id){
        Optional<ServicioVeterinaria> servicioVeterinariaOptional = iServicioVeterinariaService.findById(id);
        if (servicioVeterinariaOptional.isPresent()) {
            iServicioVeterinariaService.remove(id);
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
