package co.edu.reto.plazoleta.infrastructure.input.rest.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
public record ActualizarPlatoRequest(@NotNull @DecimalMin("0.01") BigDecimal precio, @NotBlank String descripcion) {}
