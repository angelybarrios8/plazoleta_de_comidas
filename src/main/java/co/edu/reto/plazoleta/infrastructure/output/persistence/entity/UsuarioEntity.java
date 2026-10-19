package co.edu.reto.plazoleta.infrastructure.output.persistence.entity;
import jakarta.persistence.*;
@Entity @Table(name="usuarios", uniqueConstraints=@UniqueConstraint(columnNames="correo"))
public class UsuarioEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String nombre;
 @Column(nullable=false) public String apellido;
 @Column(nullable=false, unique=true) public String correo;
 @Column(nullable=false) public String passwordHash;
 @Column(nullable=false, unique=true) public String documento;
 @Enumerated(EnumType.STRING) @Column(nullable=false) public co.edu.reto.plazoleta.domain.model.Rol rol;
 public Long idRestaurante;
 public UsuarioEntity() {}
}
