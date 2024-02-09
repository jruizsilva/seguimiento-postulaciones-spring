package seguimientopostulaciones.postulaciones.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seguimientopostulaciones.postulaciones.domain.PostulacionEntity;
import seguimientopostulaciones.exceptions.CustomEntityNotFoundException;
import seguimientopostulaciones.postulaciones.domain.dto.CreatePostulacionRequest;
import seguimientopostulaciones.postulaciones.domain.dto.UpdatePostulacionRequest;
import seguimientopostulaciones.postulaciones.domain.dto.PostulacionResponse;
import seguimientopostulaciones.postulaciones.domain.mapper.PostulacionMapper;
import seguimientopostulaciones.postulaciones.repository.PostulacionRepository;
import seguimientopostulaciones.postulaciones.service.PostulacionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostulacionServiceImpl implements PostulacionService {
    private final PostulacionRepository postulacionRepository;
    private final PostulacionMapper postulacionMapper;

    @Override
    public PostulacionResponse create(CreatePostulacionRequest createPostulacionRequest) {
        PostulacionEntity postulacionEntityToSave = postulacionMapper.createRequestToEntity(createPostulacionRequest);
        PostulacionEntity postulacionEntitySaved = postulacionRepository.save(postulacionEntityToSave);
        return postulacionMapper.entityToResponse(postulacionEntitySaved);
    }

    @Override
    public PostulacionResponse update(UpdatePostulacionRequest updatePostulacionRequest) {
        Optional<PostulacionEntity> postulacionEntityOptional = postulacionRepository.findById(updatePostulacionRequest.getId());

        if (postulacionEntityOptional.isEmpty()) {
            throw new CustomEntityNotFoundException("postulacion to edit not found");
        }
        PostulacionEntity postulacionEntity = postulacionEntityOptional.get();
        PostulacionEntity postulacionEntityToUpdate = postulacionMapper.updateRequestToEntity(updatePostulacionRequest,
                                                                                              postulacionEntity);
        PostulacionEntity postulacionEntityUpdated = postulacionRepository.save(postulacionEntityToUpdate);
        return postulacionMapper.entityToResponse(postulacionEntityUpdated);
    }

    @Override
    public PostulacionResponse findById(Long id) {
        PostulacionEntity postulacionEntity = postulacionRepository.findById(id)
                                                                   .orElseThrow(() -> new CustomEntityNotFoundException("postulacion not found"));
        return postulacionMapper.entityToResponse(postulacionEntity);
    }

    @Override
    public void deleteById(Long id) {
        PostulacionEntity postulacionEntity = postulacionRepository.findById(id)
                                                                   .orElseThrow(() -> new CustomEntityNotFoundException("postulacion to delete not found"));

        postulacionRepository.delete(postulacionEntity);
    }

    @Override
    public List<PostulacionResponse> findAll() {
        return postulacionRepository.findAll()
                                    .stream()
                                    .map(postulacionMapper::entityToResponse)
                                    .toList();
    }

    @Override
    public List<Map<String, Object>> findAllMapPostulaciones() {
        List<PostulacionEntity> postulacionEntityList = postulacionRepository.findAll();
        return postulacionMapper.entityListToMapList(postulacionEntityList);
    }
}
