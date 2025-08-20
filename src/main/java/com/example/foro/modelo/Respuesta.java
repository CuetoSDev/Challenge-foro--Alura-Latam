package com.example.foro.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "respuestas")
public class Respuesta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min = 10, max = 2000)
    @Column(columnDefinition = "TEXT")
    private String mensaje;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    
    private Boolean solucion = false;
    
    public Respuesta() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public Respuesta(String mensaje, Topico topico, Usuario autor) {
        this();
        this.mensaje = mensaje;
        this.topico = topico;
        this.autor = autor;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    
    public Topico getTopico() { return topico; }
    public void setTopico(Topico topico) { this.topico = topico; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    
    public Boolean getSolucion() { return solucion; }
    public void setSolucion(Boolean solucion) { this.solucion = solucion; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respuesta respuesta = (Respuesta) o;
        return Objects.equals(id, respuesta.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}