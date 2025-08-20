package com.example.foro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutenticacionDTO {
    
    @Schema(description = "DTO para el login de usuario")
    public record DatosAutenticacionUsuario(
            @NotBlank(message = "El email no puede estar vacío")
            @Email(message = "Debe ser un email válido")
            String email,
            
            @NotBlank(message = "La contraseña no puede estar vacía")
            String password
    ) {}
    
    @Schema(description = "DTO para registrar un nuevo usuario")
    public record DatosRegistroUsuario(
            @NotBlank(message = "El nombre no puede estar vacío")
            @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
            String nombre,
            
            @NotBlank(message = "El email no puede estar vacío")
            @Email(message = "Debe ser un email válido")
            String email,
            
            @NotBlank(message = "La contraseña no puede estar vacía")
            @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
            String password
    ) {}
    
    @Schema(description = "DTO de respuesta con el token JWT")
    public record DatosJWTToken(
            String token,
            String tipo,
            Long expiracion
    ) {}
    
    @Schema(description = "DTO con información del usuario")
    public record DatosUsuario(
            Long id,
            String nombre,
            String email
    ) {}
}