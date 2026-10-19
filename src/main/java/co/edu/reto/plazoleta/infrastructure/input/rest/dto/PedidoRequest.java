package co.edu.reto.plazoleta.infrastructure.input.rest.dto;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import java.util.List;
public record PedidoRequest(@NotNull Long idRestaurante, @NotEmpty List<@Valid ItemPedidoRequest> items) {}
