package co.edu.reto.plazoleta.infrastructure.input.rest;
import co.edu.reto.plazoleta.application.service.PlazoletaService; import co.edu.reto.plazoleta.infrastructure.input.rest.dto.*; import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.UsuarioEntity; import co.edu.reto.plazoleta.domain.model.Rol;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import org.springframework.http.*;
@RestController @RequestMapping("/api/usuarios")
public class UsuarioController {
 private final PlazoletaService service; public UsuarioController(PlazoletaService s){service=s;}
 @PostMapping("/propietarios") public ResponseEntity<UsuarioEntity> propietario(@RequestHeader("X-User-Role") String role,@Valid @RequestBody UsuarioRequest q){exigirAdmin(role);return ResponseEntity.status(201).body(service.crearUsuario(q,Rol.PROPIETARIO));}
 @PostMapping("/empleados") public ResponseEntity<UsuarioEntity> empleado(@RequestHeader("X-User-Role") String role,@Valid @RequestBody UsuarioRequest q){exigirAdmin(role);return ResponseEntity.status(201).body(service.crearUsuario(q,Rol.EMPLEADO));}
 private void exigirAdmin(String role){if(!"ADMIN".equals(role)) throw new co.edu.reto.plazoleta.domain.exception.ReglaNegocioException("Se requiere rol ADMIN");}
 @PostMapping("/clientes") public ResponseEntity<UsuarioEntity> cliente(@Valid @RequestBody UsuarioRequest q){return ResponseEntity.status(201).body(service.crearUsuario(q,Rol.CLIENTE));}
}
