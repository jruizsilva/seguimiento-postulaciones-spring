package seguimientopostulaciones.postulacionesespontanes.domain.dto;

import lombok.*;
import seguimientopostulaciones.utils.Estado;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostulacionEspontaneaResponse {
    private Long id;
    private String nombre;
    private String empresa;
    private String mensaje;
    private String fecha;
    private Estado estado;
}
