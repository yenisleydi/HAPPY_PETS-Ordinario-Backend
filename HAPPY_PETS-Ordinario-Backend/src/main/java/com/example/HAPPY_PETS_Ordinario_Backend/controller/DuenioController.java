package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IDuenioService;
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
@RequestMapping("/duenios")
public class DuenioController {

    @Autowired
    private IDuenioService iDuenioService;

    @GetMapping
    public List<Duenio> list(){
        return iDuenioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Duenio> remitenteOptional = iDuenioService.findById(id);
        if (remitenteOptional.isPresent()){
            return ResponseEntity.ok(remitenteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Duenio duenio, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        Duenio duenio1 = iDuenioService.save(duenio);
        return ResponseEntity.status(HttpStatus.CREATED).body(duenio1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Duenio duenio, BindingResult result, @PathVariable Long id){
        Optional<Duenio> remitenteOptional = iDuenioService.update(duenio, id);
        if (remitenteOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(remitenteOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove (@PathVariable Long id){
        Optional<Duenio> remitenteOptional = iDuenioService.findById(id);
        if (remitenteOptional.isPresent()) {
            iDuenioService.remove(id);
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
