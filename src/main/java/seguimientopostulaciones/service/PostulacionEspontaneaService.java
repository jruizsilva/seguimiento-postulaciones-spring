package seguimientopostulaciones.service;

import seguimientopostulaciones.http.request.postulacion.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.request.postulacionespontanea.UpdatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.response.postulacionespontanea.PostulacionEspontaneaResponse;

import java.util.List;

public interface PostulacionEspontaneaService {
    PostulacionEspontaneaResponse create(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest);
    PostulacionEspontaneaResponse update(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest);
    PostulacionEspontaneaResponse findById(Long id);
    void deleteById(Long id);
    List<PostulacionEspontaneaResponse> findAll();
}
