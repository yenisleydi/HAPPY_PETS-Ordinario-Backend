package com.example.HAPPY_PETS_Ordinario_Backend.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "historial_medico")
public class HistorialMedico {
    @Id
    private String id;

    private String id_mascota;
    private List<Historial> historial;

    @Data
    public static class Historial {
        private String fecha;
        private String diagnostico;
        private String tratamiento;
        private List<Medicamento> medicamentos;
        private String idVeterinario;
    }

    @Data
    public static class Medicamento {
        private String nombre;
        private String dosis;
        private String duracion;
    }
}