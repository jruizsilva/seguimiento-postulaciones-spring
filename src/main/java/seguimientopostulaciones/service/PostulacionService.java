package seguimientopostulaciones.service;

import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;

import java.util.List;

public interface PostulacionService {
    PostulacionResponse createPostulacion(CreatePostulacionRequest createPostulacionRequest);
    PostulacionResponse updatePostulacion(UpdatePostulacionRequest updatePostulacionRequest);
    PostulacionResponse findPostulacionById(Long postulacionId);
    void deletePostulacionById(Long postulacionId);
    List<PostulacionResponse> findAllPostulaciones();
}
