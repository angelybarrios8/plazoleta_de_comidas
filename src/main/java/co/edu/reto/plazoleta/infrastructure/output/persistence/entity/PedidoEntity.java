package co.edu.reto.plazoleta.infrastructure.output.persistence.entity;
import jakarta.persistence.*; import java.time.Instant; import java.math.BigDecimal;
@Entity @Table(name="pedidos")
public class PedidoEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public Long idCliente;
 @Column(nullable=false) public Long idRestaurante;
 @Enumerated(EnumType.STRING) @Column(nullable=false) public co.edu.reto.plazoleta.domain.model.EstadoPedido estado;
 @Column(nullable=false) public Instant creadoEn=Instant.now();
 @Column(nullable=false, precision=12, scale=2) public BigDecimal total=BigDecimal.ZERO;
 public PedidoEntity() {}
}
