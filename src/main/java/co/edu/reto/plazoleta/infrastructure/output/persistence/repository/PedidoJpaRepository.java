package co.edu.reto.plazoleta.infrastructure.output.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.PedidoEntity;
public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {  boolean existsByIdClienteAndEstadoIn(Long idCliente, java.util.Collection<co.edu.reto.plazoleta.domain.model.EstadoPedido> estados); }
