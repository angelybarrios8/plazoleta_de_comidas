package co.edu.reto.plazoleta.infrastructure.input.rest.dto;
import jakarta.validation.constraints.*;
public record RestauranteRequest(@NotBlank String nombre, @NotBlank String nit, @NotBlank String direccion, @NotBlank String telefono, String urlLogo, @NotNull Long idPropietario) {}
