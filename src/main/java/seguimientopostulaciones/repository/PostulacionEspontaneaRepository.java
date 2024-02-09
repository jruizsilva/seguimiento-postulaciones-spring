package seguimientopostulaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seguimientopostulaciones.entity.PostulacionEspontaneaEntity;

public interface PostulacionEspontaneaRepository extends JpaRepository<PostulacionEspontaneaEntity, Long> {
}