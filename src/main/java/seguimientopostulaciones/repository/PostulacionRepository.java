package seguimientopostulaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seguimientopostulaciones.entity.PostulacionEntity;

public interface PostulacionRepository extends JpaRepository<PostulacionEntity, Long> {
}