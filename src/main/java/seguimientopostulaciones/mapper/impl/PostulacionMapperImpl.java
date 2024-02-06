package seguimientopostulaciones.mapper.impl;

import org.springframework.stereotype.Component;
import seguimientopostulaciones.entity.PostulacionEntity;
import seguimientopostulaciones.entity.util.Estado;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;
import seguimientopostulaciones.mapper.PostulacionMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostulacionMapperImpl implements PostulacionMapper {
    @Override
    public PostulacionEntity createProductRequestToEntity(CreatePostulacionRequest createPostulacionRequest) {
        return PostulacionEntity.builder()
                                .puesto(createPostulacionRequest.getPuesto())
                                .empresa(createPostulacionRequest.getEmpresa())
                                .enlace(createPostulacionRequest.getEnlace())
                                .plataforma(createPostulacionRequest.getPlataforma())
                                .estado(Estado.EN_PROCESO)
                                .build();
    }

    @Override
    public PostulacionEntity updateProductRequestToEntity(UpdatePostulacionRequest updatePostulacionRequest,
                                                          PostulacionEntity postulacionEntity) {
        String puesto = updatePostulacionRequest.getPuesto();
        String empresa = updatePostulacionRequest.getEmpresa();
        String enlace = updatePostulacionRequest.getEnlace();
        Estado estado = updatePostulacionRequest.getEstado();
        String plataforma = updatePostulacionRequest.getPlataforma();

        if (puesto != null) {
            postulacionEntity.setPuesto(puesto);
        }
        if (empresa != null) {
            postulacionEntity.setEmpresa(empresa);
        }
        if (enlace != null) {
            postulacionEntity.setEnlace(enlace);
        }
        if (estado != null) {
            postulacionEntity.setEstado(estado);
        }
        if (plataforma != null) {
            postulacionEntity.setPlataforma(plataforma);
        }
        return postulacionEntity;
    }

    @Override
    public PostulacionResponse entityToResponse(PostulacionEntity postulacionEntity) {
        return PostulacionResponse.builder()
                                  .id(postulacionEntity.getId())
                                  .puesto(postulacionEntity.getPuesto())
                                  .empresa(postulacionEntity.getEmpresa())
                                  .plataforma(postulacionEntity.getPlataforma())
                                  .enlace(postulacionEntity.getEnlace())
                                  .estado(postulacionEntity.getEstado())
                                  .fecha(postulacionEntity.getFecha()
                                                          .toString())
                                  .build();
    }

    @Override
    public List<Map<String, Object>> entityListToMapList(List<PostulacionEntity> postulacionEntityList) {
        return postulacionEntityList.stream()
                                    .map(PostulacionMapperImpl::entityToMap)
                                    .toList();
    }

    private static Map<String, Object> entityToMap(PostulacionEntity postulacion) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",
                postulacion.getId());
        map.put("puesto",
                postulacion.getPuesto());
        map.put("empresa",
                postulacion.getEmpresa());
        map.put("plataforma",
                postulacion.getPlataforma());
        map.put("enlace",
                postulacion.getEnlace());
        map.put("fecha",
                postulacion.getFecha());
        map.put("estado",
                postulacion.getEstado()
                           .name());
        // Puedes agregar más campos según sea necesario
        return map;
    }
}
