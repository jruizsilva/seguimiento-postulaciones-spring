package seguimientopostulaciones.postulaciones.domain.mapper;

import seguimientopostulaciones.postulaciones.domain.PostulacionEntity;
import seguimientopostulaciones.postulaciones.domain.dto.CreatePostulacionRequest;
import seguimientopostulaciones.postulaciones.domain.dto.UpdatePostulacionRequest;
import seguimientopostulaciones.postulaciones.domain.dto.PostulacionResponse;

import java.util.List;
import java.util.Map;

public interface PostulacionMapper {
    PostulacionEntity createRequestToEntity(CreatePostulacionRequest createPostulacionRequest);

    PostulacionEntity updateRequestToEntity(UpdatePostulacionRequest updatePostulacionRequest,
                                            PostulacionEntity postulacionEntity);

    PostulacionResponse entityToResponse(PostulacionEntity postulacionEntity);

    List<Map<String, Object>> entityListToMapList(List<PostulacionEntity> postulacionEntityList);
}
