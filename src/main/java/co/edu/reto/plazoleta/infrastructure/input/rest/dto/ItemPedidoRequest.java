package co.edu.reto.plazoleta.infrastructure.input.rest.dto;
import jakarta.validation.constraints.*;
public record ItemPedidoRequest(@NotNull Long idPlato, @NotNull @Min(1) Integer cantidad) {}
