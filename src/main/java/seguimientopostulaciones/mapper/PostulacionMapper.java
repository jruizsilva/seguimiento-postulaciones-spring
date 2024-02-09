package seguimientopostulaciones.mapper;

import seguimientopostulaciones.entity.PostulacionEntity;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;

import java.util.List;
import java.util.Map;

public interface PostulacionMapper {
    PostulacionEntity createPostulacionRequestToEntity(CreatePostulacionRequest createPostulacionRequest);

    PostulacionEntity updatePostulacionRequestToEntity(UpdatePostulacionRequest updatePostulacionRequest,
                                                       PostulacionEntity postulacionEntity);

    PostulacionResponse entityToResponse(PostulacionEntity postulacionEntity);

    List<Map<String, Object>> entityListToMapList(List<PostulacionEntity> postulacionEntityList);
}
