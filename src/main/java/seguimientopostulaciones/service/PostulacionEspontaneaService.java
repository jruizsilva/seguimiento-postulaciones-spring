package seguimientopostulaciones.service;

import seguimientopostulaciones.http.request.postulacion.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.request.postulacionespontanea.UpdatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.response.postulacionespontanea.PostulacionEspontaneaResponse;

import java.util.List;

public interface PostulacionEspontaneaService {
    PostulacionEspontaneaResponse createPostulacionEspontanea(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest);
    PostulacionEspontaneaResponse updatePostulacionEspontanea(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest);
    PostulacionEspontaneaResponse findPostulacionEspontaneaById(Long postulacionId);
    void deletePostulacionEspontaneaById(Long postulacionId);
    List<PostulacionEspontaneaResponse> findAllPostulacionesEspontaneas();
}
