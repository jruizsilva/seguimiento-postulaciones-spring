package seguimientopostulaciones.postulaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seguimientopostulaciones.postulaciones.domain.PostulacionEntity;

public interface PostulacionRepository extends JpaRepository<PostulacionEntity, Long> {
}