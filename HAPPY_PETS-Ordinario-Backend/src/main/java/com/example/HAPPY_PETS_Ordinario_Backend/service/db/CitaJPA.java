package com.example.HAPPY_PETS_Ordinario_Backend.service.db;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Cita;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Mascota;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.CitaRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.MascotaRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitaJPA implements ICitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> findAll() {
        return (List<Cita>) citaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    @Transactional
    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    @Transactional
    public Optional<Cita> update(Cita cita, Long id) {
        Optional<Cita> citaOptional = this.findById(id);
        Cita cita1 = null;
        if (citaOptional.isPresent()){
            Cita cita2 = citaOptional.orElseThrow();
            cita2.setFechaHora(cita.getFechaHora());
            cita2.setMotivo(cita.getMotivo());
            cita2.setMascota(cita.getMascota());
            cita2.setVeterinario(cita.getVeterinario());
            cita2.setEstado(cita.getEstado());
            cita1 = this.save(cita2);
        }
        return Optional.ofNullable(cita1);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        citaRepository.deleteById(id);
    }
}
