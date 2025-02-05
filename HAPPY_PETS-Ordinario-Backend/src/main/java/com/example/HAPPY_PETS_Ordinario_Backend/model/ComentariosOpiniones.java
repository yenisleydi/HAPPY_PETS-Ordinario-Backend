package com.example.HAPPY_PETS_Ordinario_Backend.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "comentarios_opiniones")
public class ComentariosOpiniones {
    @Id
    private String id;

    private String id_duenio;
    private List<Comentario> comentarios;

    @Data
    public static class Comentario {
        private String fecha;
        private String mensaje;
        private int calificacion;
        private String idServicio;
    }
}
