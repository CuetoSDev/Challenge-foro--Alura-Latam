package com.example.foro.repositorio;

import com.example.foro.modelo.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    
    List<Respuesta> findByTopicoId(Long topicoId);
    
    List<Respuesta> findByAutorId(Long autorId);
}