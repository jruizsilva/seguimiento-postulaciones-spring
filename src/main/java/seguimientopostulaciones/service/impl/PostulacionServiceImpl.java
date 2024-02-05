package seguimientopostulaciones.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seguimientopostulaciones.entity.PostulacionEntity;
import seguimientopostulaciones.exceptions.CustomEntityNotFoundException;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;
import seguimientopostulaciones.mapper.PostulacionMapper;
import seguimientopostulaciones.repository.PostulacionRepository;
import seguimientopostulaciones.service.PostulacionService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostulacionServiceImpl implements PostulacionService {
    private final PostulacionRepository postulacionRepository;
    private final PostulacionMapper postulacionMapper;

    @Override
    public PostulacionResponse createPostulacion(CreatePostulacionRequest createPostulacionRequest) {
        PostulacionEntity postulacionEntityToSave = postulacionMapper.createProductRequestToEntity(createPostulacionRequest);
        PostulacionEntity postulacionEntitySaved = postulacionRepository.save(postulacionEntityToSave);
        return postulacionMapper.entityToResponse(postulacionEntitySaved);
    }

    @Override
    public PostulacionResponse updatePostulacion(UpdatePostulacionRequest updatePostulacionRequest) {
        Optional<PostulacionEntity> postulacionEntityOptional = postulacionRepository.findById(updatePostulacionRequest.getId());

        if (postulacionEntityOptional.isEmpty()) {
            throw new CustomEntityNotFoundException("postulacion to edit not found");
        }
        PostulacionEntity postulacionEntity = postulacionEntityOptional.get();
        PostulacionEntity postulacionEntityToUpdate = postulacionMapper.updateProductRequestToEntity(updatePostulacionRequest,
                                                                                                     postulacionEntity);
        PostulacionEntity postulacionEntityUpdated = postulacionRepository.save(postulacionEntityToUpdate);
        return postulacionMapper.entityToResponse(postulacionEntityUpdated);
    }

    @Override
    public PostulacionResponse findPostulacionById(Long postulacionId) {
        PostulacionEntity postulacionEntity = postulacionRepository.findById(postulacionId)
                                                                   .orElseThrow(() -> new CustomEntityNotFoundException("postulacion not found"));
        return postulacionMapper.entityToResponse(postulacionEntity);
    }

    @Override
    public void deletePostulacionById(Long postulacionId) {
        PostulacionEntity postulacionEntity = postulacionRepository.findById(postulacionId)
                                                                   .orElseThrow(() -> new CustomEntityNotFoundException("postulacion to delete not found"));

        postulacionRepository.delete(postulacionEntity);
    }

    @Override
    public List<PostulacionResponse> findAllPostulaciones() {
        return postulacionRepository.findAll()
                                    .stream()
                                    .map(postulacionMapper::entityToResponse)
                                    .toList();
    }
}
