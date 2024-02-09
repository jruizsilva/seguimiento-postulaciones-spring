package seguimientopostulaciones.postulacionesespontanes.domain.mapper;

import seguimientopostulaciones.postulacionesespontanes.domain.PostulacionEspontaneaEntity;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.PostulacionEspontaneaResponse;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.UpdatePostulacionEspontaneaRequest;

import java.util.List;

public interface PostulacionEspontaneaMapper {
    PostulacionEspontaneaEntity createRequestToEntity(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest);

    PostulacionEspontaneaEntity updateRequestToEntity(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest,
                                                      PostulacionEspontaneaEntity postulacionEspontaneaEntity);

    PostulacionEspontaneaResponse entityToResponse(PostulacionEspontaneaEntity postulacionEspontaneaEntity);

    List<PostulacionEspontaneaResponse> entityListToResponseList(List<PostulacionEspontaneaEntity> postulacionEspontaneaEntityList);
}
