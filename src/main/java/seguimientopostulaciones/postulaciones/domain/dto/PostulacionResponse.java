package seguimientopostulaciones.postulaciones.domain.dto;

import lombok.*;
import seguimientopostulaciones.utils.Estado;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostulacionResponse {
    private Long id;
    private String puesto;
    private String fecha;
    private String empresa;
    private String enlace;
    private String plataforma;
    private Estado estado;
}
