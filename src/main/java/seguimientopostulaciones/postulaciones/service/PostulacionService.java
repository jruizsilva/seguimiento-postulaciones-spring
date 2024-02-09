package seguimientopostulaciones.postulaciones.service;

import seguimientopostulaciones.postulaciones.domain.dto.CreatePostulacionRequest;
import seguimientopostulaciones.postulaciones.domain.dto.UpdatePostulacionRequest;
import seguimientopostulaciones.postulaciones.domain.dto.PostulacionResponse;

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
