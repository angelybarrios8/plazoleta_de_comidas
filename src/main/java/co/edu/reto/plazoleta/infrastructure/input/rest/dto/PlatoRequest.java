package co.edu.reto.plazoleta.infrastructure.input.rest.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
public record PlatoRequest(@NotBlank String nombre, @NotNull @DecimalMin("0.01") BigDecimal precio, @NotBlank String descripcion, String urlImagen, @NotBlank String categoria, @NotNull Long idRestaurante) {}
