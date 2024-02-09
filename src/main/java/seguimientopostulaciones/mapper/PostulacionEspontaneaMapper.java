package seguimientopostulaciones.mapper;

import seguimientopostulaciones.entity.PostulacionEspontaneaEntity;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.request.postulacionespontanea.UpdatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.response.postulacionespontanea.PostulacionEspontaneaResponse;

import java.util.List;

public interface PostulacionEspontaneaMapper {
    PostulacionEspontaneaEntity createRequestToEntity(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest);

    PostulacionEspontaneaEntity updateRequestToEntity(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest,
                                                      PostulacionEspontaneaEntity postulacionEspontaneaEntity);

    PostulacionEspontaneaResponse entityToResponse(PostulacionEspontaneaEntity postulacionEspontaneaEntity);

    List<PostulacionEspontaneaResponse> entityListToResponseList(List<PostulacionEspontaneaEntity> postulacionEspontaneaEntityList);
}
