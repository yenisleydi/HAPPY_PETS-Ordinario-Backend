package com.example.HAPPY_PETS_Ordinario_Backend.service.db;

import com.example.HAPPY_PETS_Ordinario_Backend.model.ServicioVeterinaria;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.ServicioVeterinariaRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.VeterinarioRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IServicioVeterinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioVeterinariaJPA implements IServicioVeterinariaService {

    @Autowired
    private ServicioVeterinariaRepository servicioVeterinariaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ServicioVeterinaria> findAll() {
        return (List<ServicioVeterinaria>) servicioVeterinariaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioVeterinaria> findById(Long id) {
        return servicioVeterinariaRepository.findById(id);
    }

    @Override
    @Transactional
    public ServicioVeterinaria save(ServicioVeterinaria servicioVeterinaria) {
        return servicioVeterinariaRepository.save(servicioVeterinaria);
    }
    @Override
    @Transactional
    public Optional<ServicioVeterinaria> update(ServicioVeterinaria servicioVeterinaria, Long id) {
        Optional<ServicioVeterinaria> servicioVeterinariaOptional = this.findById(id);
        ServicioVeterinaria servicioVeterinaria1 = null;
        if (servicioVeterinariaOptional.isPresent()){
            ServicioVeterinaria servicioVeterinaria2 = servicioVeterinariaOptional.orElseThrow();
            servicioVeterinaria2.setNombre(servicioVeterinaria.getNombre());
            servicioVeterinaria2.setDescripcion(servicioVeterinaria.getDescripcion());
            servicioVeterinaria2.setPrecio(servicioVeterinaria.getPrecio());
            servicioVeterinaria1 = this.save(servicioVeterinaria2);
        }
        return Optional.ofNullable(servicioVeterinaria1);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        servicioVeterinariaRepository.deleteById(id);
    }

}
