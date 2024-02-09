package seguimientopostulaciones.postulacionesespontanes.domain.mapper;

import org.springframework.stereotype.Component;
import seguimientopostulaciones.postulacionesespontanes.domain.PostulacionEspontaneaEntity;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.PostulacionEspontaneaResponse;
import seguimientopostulaciones.postulacionesespontanes.domain.dto.UpdatePostulacionEspontaneaRequest;
import seguimientopostulaciones.utils.Estado;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class PostulacionEspontaneaMapperImpl implements PostulacionEspontaneaMapper {
    @Override
    public PostulacionEspontaneaEntity createRequestToEntity(CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest) {
        return PostulacionEspontaneaEntity.builder()
                                          .nombre(createPostulacionEspontaneaRequest.getNombre())
                                          .empresa(createPostulacionEspontaneaRequest.getEmpresa())
                                          .mensaje(createPostulacionEspontaneaRequest.getMensaje())
                                          .estado(Estado.EN_PROCESO)
                                          .build();
    }

    @Override
    public PostulacionEspontaneaEntity updateRequestToEntity(UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest,
                                                             PostulacionEspontaneaEntity postulacionEspontaneaEntity) {
        String nombre = updatePostulacionEspontaneaRequest.getNombre();
        String empresa = updatePostulacionEspontaneaRequest.getEmpresa();
        Estado estado = updatePostulacionEspontaneaRequest.getEstado();

        if (nombre != null) {
            postulacionEspontaneaEntity.setNombre(nombre);
        }
        if (empresa != null) {
            postulacionEspontaneaEntity.setEmpresa(empresa);
        }
        if (estado != null) {
            postulacionEspontaneaEntity.setEstado(estado);
        }
        return postulacionEspontaneaEntity;
    }

    @Override
    public PostulacionEspontaneaResponse entityToResponse(PostulacionEspontaneaEntity postulacionEspontaneaEntity) {
        return PostulacionEspontaneaResponse.builder()
                                            .id(postulacionEspontaneaEntity.getId())
                                            .nombre(postulacionEspontaneaEntity.getNombre())
                                            .empresa(postulacionEspontaneaEntity.getEmpresa())
                                            .mensaje(postulacionEspontaneaEntity.getMensaje())
                                            .estado(postulacionEspontaneaEntity.getEstado())
                                            .fecha(formatDate(postulacionEspontaneaEntity.getFecha()))
                                            .build();
    }

    @Override
    public List<PostulacionEspontaneaResponse> entityListToResponseList(List<PostulacionEspontaneaEntity> postulacionEspontaneaEntityList) {
        return postulacionEspontaneaEntityList.stream()
                                              .map(this::entityToResponse)
                                              .toList();
    }

    private String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy");
        return simpleDateFormat.format(date);
    }
}
