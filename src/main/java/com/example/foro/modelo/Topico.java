package com.example.foro.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topicos")
public class Topico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min = 5, max = 200)
    private String titulo;
    
    @NotBlank
    @Size(min = 10, max = 2000)
    @Column(columnDefinition = "TEXT")
    private String mensaje;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    
    @NotBlank
    @Size(min = 2, max = 100)
    private String curso;
    
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();
    
    public Topico() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public Topico(String titulo, String mensaje, String curso, Usuario autor) {
        this();
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
        this.autor = autor;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public StatusTopico getStatus() { return status; }
    public void setStatus(StatusTopico status) { this.status = status; }
    
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    
    public List<Respuesta> getRespuestas() { return respuestas; }
    public void setRespuestas(List<Respuesta> respuestas) { this.respuestas = respuestas; }
    
    public void actualizarDatos(String titulo, String mensaje, String curso) {
        if (titulo != null) this.titulo = titulo;
        if (mensaje != null) this.mensaje = mensaje;
        if (curso != null) this.curso = curso;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;
        return Objects.equals(id, topico.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}