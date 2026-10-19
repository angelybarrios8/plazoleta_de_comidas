package co.edu.reto.plazoleta.infrastructure.output.persistence.entity;
import jakarta.persistence.*;
@Entity @Table(name="restaurantes", uniqueConstraints=@UniqueConstraint(columnNames="nit"))
public class RestauranteEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String nombre;
 @Column(nullable=false, unique=true) public String nit;
 @Column(nullable=false) public String direccion;
 @Column(nullable=false) public String telefono;
 public String urlLogo;
 @Column(nullable=false) public Long idPropietario;
 public RestauranteEntity() {}
}
