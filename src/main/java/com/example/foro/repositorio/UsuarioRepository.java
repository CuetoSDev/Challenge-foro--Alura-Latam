package com.example.foro.repositorio;

import com.example.foro.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    UserDetails findByEmail(String email);
    
    Optional<Usuario> findByEmailOptional(String email);
    
    boolean existsByEmail(String email);
    
    boolean existsByNombre(String nombre);
}