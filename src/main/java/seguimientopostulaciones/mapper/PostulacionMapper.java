package seguimientopostulaciones.mapper;

import seguimientopostulaciones.entity.PostulacionEntity;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;

public interface PostulacionMapper {
    PostulacionEntity createProductRequestToEntity(CreatePostulacionRequest createPostulacionRequest);

    PostulacionEntity updateProductRequestToEntity(UpdatePostulacionRequest updatePostulacionRequest,
                                                   PostulacionEntity postulacionEntity);

    PostulacionResponse entityToResponse(PostulacionEntity postulacionEntity);
}
