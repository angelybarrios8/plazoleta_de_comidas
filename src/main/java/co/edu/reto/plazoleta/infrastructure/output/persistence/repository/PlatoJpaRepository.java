package co.edu.reto.plazoleta.infrastructure.output.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.PlatoEntity;
public interface PlatoJpaRepository extends JpaRepository<PlatoEntity, Long> {  org.springframework.data.domain.Page<PlatoEntity> findByIdRestauranteAndCategoriaIgnoreCaseAndActivoTrue(Long idRestaurante, String categoria, org.springframework.data.domain.Pageable pageable); }
