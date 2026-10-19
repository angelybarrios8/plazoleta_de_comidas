package co.edu.reto.plazoleta.infrastructure.input.rest;
import co.edu.reto.plazoleta.application.service.PlazoletaService; import co.edu.reto.plazoleta.infrastructure.input.rest.dto.PedidoRequest; import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.PedidoEntity;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import org.springframework.http.*;
@RestController @RequestMapping("/api/pedidos")
public class PedidoController {
 private final PlazoletaService service; public PedidoController(PlazoletaService s){service=s;}
 @PostMapping public ResponseEntity<PedidoEntity> crear(@RequestHeader("X-User-Id") Long clienteId,@RequestHeader("X-User-Role") String role,@Valid @RequestBody PedidoRequest q){if(!"CLIENTE".equals(role)) throw new co.edu.reto.plazoleta.domain.exception.ReglaNegocioException("Se requiere rol CLIENTE");return ResponseEntity.status(201).body(service.crearPedido(clienteId,q));}
}
