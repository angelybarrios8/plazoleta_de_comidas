package co.edu.reto.plazoleta.infrastructure.output.persistence.entity;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="detalles_pedido")
public class DetallePedidoEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public Long idPedido;
 @Column(nullable=false) public Long idPlato;
 @Column(nullable=false) public String nombrePlato;
 @Column(nullable=false) public Integer cantidad;
 @Column(nullable=false, precision=12, scale=2) public BigDecimal precioUnitario;
 public DetallePedidoEntity() {}
}
