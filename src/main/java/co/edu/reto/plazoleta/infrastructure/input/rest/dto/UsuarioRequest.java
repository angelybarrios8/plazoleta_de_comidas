package co.edu.reto.plazoleta.infrastructure.input.rest.dto;
import jakarta.validation.constraints.*;
import co.edu.reto.plazoleta.domain.model.Rol;
public record UsuarioRequest(@NotBlank String nombre, @NotBlank String apellido, @Email @NotBlank String correo, @Size(min=8) String password, @NotBlank String documento, @NotNull Rol rol, Long idRestaurante) {}
