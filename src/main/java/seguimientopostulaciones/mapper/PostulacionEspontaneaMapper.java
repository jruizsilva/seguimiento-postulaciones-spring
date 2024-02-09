package seguimientopostulaciones.mapper;

import seguimientopostulaciones.entity.PostulacionEspontaneaEntity;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.request.postulacionespontanea.UpdatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.response.postulacionespontanea.PostulacionEspontaneaResponse;

import java.util.List;

public interface PostulacionEspontaneaMapper {
    PostulacionEspontaneaEntity createPostulacionEspontaneaRequestToEntity(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest);

    PostulacionEspontaneaEntity updatePostulacionEspontaneaRequestToEntity(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest,
                                                                           PostulacionEspontaneaEntity postulacionEspontaneaEntity);

    PostulacionEspontaneaResponse entityToResponse(PostulacionEspontaneaEntity postulacionEspontaneaEntity);

    List<PostulacionEspontaneaResponse> entityListToResponseList(List<PostulacionEspontaneaEntity> postulacionEspontaneaEntityList);
}
