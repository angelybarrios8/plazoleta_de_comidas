package co.edu.reto.plazoleta.infrastructure.output.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.UsuarioEntity;
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {  boolean existsByCorreo(String correo); boolean existsByDocumento(String documento); }
