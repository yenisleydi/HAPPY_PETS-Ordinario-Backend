package com.example.HAPPY_PETS_Ordinario_Backend.service.db;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.VeterinarioRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IVeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioJPA implements IVeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Veterinario> findAll() {
        return (List<Veterinario>) veterinarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Veterinario> findById(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Veterinario save(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    @Transactional
    public Optional<Veterinario> update(Veterinario veterinario, Long id) {
        Optional<Veterinario> veterinarioOptional = this.findById(id);
        Veterinario veterinario1 = null;
        if (veterinarioOptional.isPresent()){
            Veterinario veterinario2 = veterinarioOptional.orElseThrow();
            veterinario2.setNombre(veterinario.getNombre());
            veterinario2.setApellidos(veterinario.getApellidos());
            veterinario2.setTelefono(veterinario.getTelefono());
            veterinario2.setEmail(veterinario.getEmail());
            veterinario2.setEspecialidad(veterinario.getEspecialidad());
            veterinario1 = this.save(veterinario2);
        }
        return Optional.ofNullable(veterinario1);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        veterinarioRepository.deleteById(id);
    }

}
