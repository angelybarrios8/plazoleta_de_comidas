package co.edu.reto.plazoleta.infrastructure.input.rest;
import co.edu.reto.plazoleta.application.service.PlazoletaService; import co.edu.reto.plazoleta.infrastructure.input.rest.dto.*; import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.*;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import org.springframework.http.*; import org.springframework.data.domain.Page;
@RestController @RequestMapping("/api/restaurantes")
public class RestauranteController {
 private final PlazoletaService service; public RestauranteController(PlazoletaService s){service=s;}
 @PostMapping public ResponseEntity<RestauranteEntity> crear(@RequestHeader("X-User-Role") String role,@Valid @RequestBody RestauranteRequest q){if(!"ADMIN".equals(role)) throw new co.edu.reto.plazoleta.domain.exception.ReglaNegocioException("Se requiere rol ADMIN");return ResponseEntity.status(201).body(service.crearRestaurante(q));}
 @GetMapping public Page<RestauranteEntity> listar(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){return service.listarRestaurantes(page,size);}
}
