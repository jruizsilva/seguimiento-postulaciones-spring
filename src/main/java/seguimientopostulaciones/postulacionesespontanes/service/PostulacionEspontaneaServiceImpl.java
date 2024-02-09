package seguimientopostulaciones.postulacionesespontanes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seguimientopostulaciones.exceptions.CustomEntityNotFoundException;
import seguimientopostulaciones.postulacionesespontanes.domain.PostulacionEspontaneaEntity;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.PostulacionEspontaneaResponse;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.UpdatePostulacionEspontaneaRequest;
import seguimientopostulaciones.postulacionesespontanes.domain.mapper.PostulacionEspontaneaMapper;
import seguimientopostulaciones.postulacionesespontanes.repository.PostulacionEspontaneaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostulacionEspontaneaServiceImpl implements PostulacionEspontaneaService {
    private final PostulacionEspontaneaRepository repository;
    private final PostulacionEspontaneaMapper mapper;

    @Override
    public PostulacionEspontaneaResponse create(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest) {
        PostulacionEspontaneaEntity entityToSave = mapper.createRequestToEntity(createPostulacionEspontaneaRequest);
        PostulacionEspontaneaEntity entitySaved = repository.save(entityToSave);
        return mapper.entityToResponse(entitySaved);
    }

    @Override
    public PostulacionEspontaneaResponse update(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest) {
        Optional<PostulacionEspontaneaEntity> entityOptional = repository.findById(updatePostulacionEspontaneaRequest.getId());

        if (entityOptional.isEmpty()) {
            throw new CustomEntityNotFoundException("postulacion espontanea to edit not found");
        }
        PostulacionEspontaneaEntity entity = entityOptional.get();
        PostulacionEspontaneaEntity entityToUpdate = mapper.updateRequestToEntity(updatePostulacionEspontaneaRequest,
                                                                                  entity);
        PostulacionEspontaneaEntity entityUpdated = repository.save(entityToUpdate);
        return mapper.entityToResponse(entityUpdated);
    }

    @Override
    public PostulacionEspontaneaResponse findById(Long id) {
        PostulacionEspontaneaEntity entity = repository.findById(id)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("postulacion espontanea not found"));
        return mapper.entityToResponse(entity);
    }

    @Override
    public void deleteById(Long id) {
        PostulacionEspontaneaEntity entity = repository.findById(id)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("postulacion to delete not found"));

        repository.delete(entity);
    }

    @Override
    public List<PostulacionEspontaneaResponse> findAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::entityToResponse)
                         .toList();
    }
}
