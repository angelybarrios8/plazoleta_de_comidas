package co.edu.reto.plazoleta.infrastructure.input.rest;
import co.edu.reto.plazoleta.domain.exception.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import org.springframework.web.bind.MethodArgumentNotValidException; import java.util.*;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(RecursoNoEncontradoException.class) public ResponseEntity<Map<String,String>> noEncontrado(RuntimeException e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(ReglaNegocioException.class) public ResponseEntity<Map<String,String>> regla(RuntimeException e){return ResponseEntity.status(400).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,String>> validacion(MethodArgumentNotValidException e){return ResponseEntity.badRequest().body(Map.of("error","Solicitud inválida: revise los campos obligatorios y sus formatos"));}
}
