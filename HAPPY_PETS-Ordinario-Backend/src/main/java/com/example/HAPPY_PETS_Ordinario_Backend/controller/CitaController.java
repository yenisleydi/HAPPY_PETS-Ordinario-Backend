package com.example.HAPPY_PETS_Ordinario_Backend.controller;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Cita;
import com.example.HAPPY_PETS_Ordinario_Backend.service.ICitaService;
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
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private ICitaService iCitaService;

    @GetMapping
    public List<Cita> list(){
        return iCitaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Cita> citaOptional = iCitaService.findById(id);
        if (citaOptional.isPresent()){
            return ResponseEntity.ok(citaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Cita cita, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        Cita cita1 = iCitaService.save(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(cita1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cita cita, BindingResult result, @PathVariable Long id){
        Optional<Cita> citaOptional = iCitaService.update(cita, id);
        if (citaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(citaOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove (@PathVariable Long id){
        Optional<Cita> citaOptional = iCitaService.findById(id);
        if (citaOptional.isPresent()) {
            iCitaService.remove(id);
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
