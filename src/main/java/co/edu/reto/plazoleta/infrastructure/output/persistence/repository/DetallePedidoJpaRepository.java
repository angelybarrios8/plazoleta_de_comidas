package co.edu.reto.plazoleta.infrastructure.output.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.DetallePedidoEntity;
public interface DetallePedidoJpaRepository extends JpaRepository<DetallePedidoEntity, Long> {  }
