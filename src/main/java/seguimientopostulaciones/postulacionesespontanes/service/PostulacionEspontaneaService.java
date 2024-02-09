package seguimientopostulaciones.postulacionesespontanes.service;

import seguimientopostulaciones.postulacionesespontanes.domain.dto.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.PostulacionEspontaneaResponse;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.UpdatePostulacionEspontaneaRequest;

import java.util.List;

public interface PostulacionEspontaneaService {
    PostulacionEspontaneaResponse create(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest);
    PostulacionEspontaneaResponse update(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest);
    PostulacionEspontaneaResponse findById(Long id);
    void deleteById(Long id);
    List<PostulacionEspontaneaResponse> findAll();
}
