package co.edu.reto.plazoleta.infrastructure.output.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.RestauranteEntity;
public interface RestauranteJpaRepository extends JpaRepository<RestauranteEntity, Long> {  boolean existsByNit(String nit); org.springframework.data.domain.Page<RestauranteEntity> findAll(org.springframework.data.domain.Pageable pageable); }
