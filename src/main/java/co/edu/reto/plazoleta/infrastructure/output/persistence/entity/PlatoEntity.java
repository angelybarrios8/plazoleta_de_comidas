package co.edu.reto.plazoleta.infrastructure.output.persistence.entity;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="platos")
public class PlatoEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String nombre;
 @Column(nullable=false, precision=12, scale=2) public BigDecimal precio;
 @Column(nullable=false, length=1000) public String descripcion;
 public String urlImagen;
 @Column(nullable=false) public String categoria;
 @Column(nullable=false) public Long idRestaurante;
 @Column(nullable=false) public boolean activo=true;
 public PlatoEntity() {}
}
