package com.example.foro.repositorio;

import com.example.foro.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    
    // Verificar si ya existe un tópico con el mismo título y mensaje
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    
    // Buscar tópicos por curso
    Page<Topico> findByCursoContainingIgnoreCase(String curso, Pageable pageable);
    
    // Buscar tópicos por título
    Page<Topico> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    
    // Buscar tópicos por autor
    Page<Topico> findByAutorId(Long autorId, Pageable pageable);
    
    // Buscar tópicos por rango de fechas
    Page<Topico> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin, Pageable pageable);
    
    // Buscar los últimos tópicos
    @Query("SELECT t FROM Topico t ORDER BY t.fechaCreacion DESC")
    List<Topico> findTop10ByOrderByFechaCreacionDesc();
    
    // Buscar tópicos por múltiples criterios
    @Query("SELECT t FROM Topico t WHERE " +
           "(:curso IS NULL OR LOWER(t.curso) LIKE LOWER(CONCAT('%', :curso, '%'))) AND " +
           "(:titulo IS NULL OR LOWER(t.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))")
    Page<Topico> findByMultipleCriteria(@Param("curso") String curso, 
                                       @Param("titulo") String titulo, 
                                       Pageable pageable);
}