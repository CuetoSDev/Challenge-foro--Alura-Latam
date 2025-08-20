package com.example.foro.dto;

import com.example.foro.modelo.StatusTopico;
import com.example.foro.modelo.Topico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TopicoDTO {
    
    // Request DTOs
    @Schema(description = "DTO para crear un nuevo tópico")
    public record DatosRegistroTopico(
            @NotBlank(message = "El título no puede estar vacío")
            @Size(min = 5, max = 200, message = "El título debe tener entre 5 y 200 caracteres")
            String titulo,
            
            @NotBlank(message = "El mensaje no puede estar vacío")
            @Size(min = 10, max = 2000, message = "El mensaje debe tener entre 10 y 2000 caracteres")
            String mensaje,
            
            @NotBlank(message = "El curso no puede estar vacío")
            @Size(min = 2, max = 100, message = "El curso debe tener entre 2 y 100 caracteres")
            String curso
    ) {}
    
    @Schema(description = "DTO para actualizar un tópico existente")
    public record DatosActualizacionTopico(
            @Size(min = 5, max = 200, message = "El título debe tener entre 5 y 200 caracteres")
            String titulo,
            
            @Size(min = 10, max = 2000, message = "El mensaje debe tener entre 10 y 2000 caracteres")
            String mensaje,
            
            @Size(min = 2, max = 100, message = "El curso debe tener entre 2 y 100 caracteres")
            String curso,
            
            StatusTopico status
    ) {}
    
    // Response DTOs
    @Schema(description = "DTO con información completa del tópico")
    public record DatosRespuestaTopico(
            Long id,
            String titulo,
            String mensaje,
            LocalDateTime fechaCreacion,
            StatusTopico status,
            String autor,
            String curso,
            int numeroRespuestas
    ) {
        public DatosRespuestaTopico(Topico topico) {
            this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso(),
                topico.getRespuestas().size()
            );
        }
    }
    
    @Schema(description = "DTO con información resumida del tópico para listados")
    public record DatosListadoTopico(
            Long id,
            String titulo,
            String mensaje,
            LocalDateTime fechaCreacion,
            StatusTopico status,
            String autor,
            String curso
    ) {
        public DatosListadoTopico(Topico topico) {
            this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje().substring(0, Math.min(topico.getMensaje().length(), 100)) + "...",
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso()
            );
        }
    }
}