package com.example.HAPPY_PETS_Ordinario_Backend.service.db;

import com.example.HAPPY_PETS_Ordinario_Backend.model.Mascota;
import com.example.HAPPY_PETS_Ordinario_Backend.model.Veterinario;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.MascotaRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.repository.VeterinarioRepository;
import com.example.HAPPY_PETS_Ordinario_Backend.service.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaJPA implements IMascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> findAll() {
        return (List<Mascota>) mascotaRepository.findAll();
    }

     @Override
    public List<Mascota> findByDuenoId(Long idDueno) {
        return mascotaRepository.findByDuenioId(idDueno); 
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mascota> findById(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    @Transactional
    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    @Transactional
    public Optional<Mascota> update(Mascota mascota, Long id) {
        Optional<Mascota> mascotaOptional = this.findById(id);
        Mascota mascota1 = null;
        if (mascotaOptional.isPresent()){
            Mascota mascota2 = mascotaOptional.orElseThrow();
            mascota2.setNombre(mascota.getNombre());
            mascota2.setEspecie(mascota.getEspecie());
            mascota2.setRaza(mascota.getRaza());
            mascota2.setEdad(mascota.getEdad());
            mascota2.setSexo(mascota.getSexo());
            mascota2.setDuenio(mascota.getDuenio());
            mascota1 = this.save(mascota2);
        }
        return Optional.ofNullable(mascota1);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        mascotaRepository.deleteById(id);
    }

}
