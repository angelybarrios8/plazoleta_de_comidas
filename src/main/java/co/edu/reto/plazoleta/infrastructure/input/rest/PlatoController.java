package co.edu.reto.plazoleta.infrastructure.input.rest;
import co.edu.reto.plazoleta.application.service.PlazoletaService; import co.edu.reto.plazoleta.infrastructure.input.rest.dto.*; import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.PlatoEntity;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import org.springframework.http.*; import org.springframework.data.domain.Page;
@RestController @RequestMapping("/api")
public class PlatoController {
 private final PlazoletaService service; public PlatoController(PlazoletaService s){service=s;}
 @PostMapping("/platos") public ResponseEntity<PlatoEntity> crear(@RequestHeader("X-User-Id") Long userId,@RequestHeader("X-User-Role") String role,@Valid @RequestBody PlatoRequest q){exigirPropietario(role);return ResponseEntity.status(201).body(service.crearPlato(q,userId));}
 @PutMapping("/platos/{id}") public PlatoEntity actualizar(@PathVariable Long id,@RequestHeader("X-User-Id") Long userId,@RequestHeader("X-User-Role") String role,@Valid @RequestBody ActualizarPlatoRequest q){exigirPropietario(role);return service.actualizarPlato(id,q,userId);}
 @PatchMapping("/platos/{id}/estado") public PlatoEntity estado(@PathVariable Long id,@RequestParam boolean activo,@RequestHeader("X-User-Id") Long userId,@RequestHeader("X-User-Role") String role){exigirPropietario(role);return service.cambiarEstadoPlato(id,activo,userId);}
 @GetMapping("/restaurantes/{idRestaurante}/platos") public Page<PlatoEntity> listar(@PathVariable Long idRestaurante,@RequestParam String categoria,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){return service.listarPlatos(idRestaurante,categoria,page,size);}
 private void exigirPropietario(String role){if(!"PROPIETARIO".equals(role)) throw new co.edu.reto.plazoleta.domain.exception.ReglaNegocioException("Se requiere rol PROPIETARIO");}
}
