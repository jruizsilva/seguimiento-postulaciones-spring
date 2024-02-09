package seguimientopostulaciones.service;

import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;

import java.util.List;
import java.util.Map;

public interface PostulacionService {
    PostulacionResponse create(CreatePostulacionRequest createPostulacionRequest);
    PostulacionResponse update(UpdatePostulacionRequest updatePostulacionRequest);
    PostulacionResponse findById(Long id);
    void deleteById(Long id);
    List<PostulacionResponse> findAll();
    List<Map<String, Object>> findAllMapPostulaciones();
}
