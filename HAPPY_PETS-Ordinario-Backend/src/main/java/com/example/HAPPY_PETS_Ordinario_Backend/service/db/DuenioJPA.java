package com.example.HAPPY_PETS_Ordinario_Backend.service.db;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Duenio;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.DuenioRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IDuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DuenioJPA implements IDuenioService {
    @Autowired
    private DuenioRepository duenioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Duenio> findAll() {
        return (List<Duenio>) duenioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Duenio> findById(Long id) {
        return duenioRepository.findById(id);
    }

    @Override
    @Transactional
    public Duenio save(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    @Override
    @Transactional
    public Optional<Duenio> update(Duenio duenio, Long id) {
        Optional<Duenio> duenioOptional = this.findById(id);
        Duenio duenio1 = null;
        if (duenioOptional.isPresent()){
            Duenio duenio2 = duenioOptional.orElseThrow();
            duenio2.setNombre(duenio.getNombre());
            duenio2.setApellido(duenio.getApellido());
            duenio2.setTelefono(duenio.getTelefono());
            duenio2.setEmail(duenio.getEmail());
            duenio2.setDireccion(duenio.getDireccion());
            duenio1 = this.save(duenio2);
        }
        return Optional.ofNullable(duenio1);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        duenioRepository.deleteById(id);
    }
}
