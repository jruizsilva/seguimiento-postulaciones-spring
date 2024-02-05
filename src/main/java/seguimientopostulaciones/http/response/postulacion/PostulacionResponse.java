package seguimientopostulaciones.http.response.postulacion;

import lombok.*;
import seguimientopostulaciones.entity.util.Estado;

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
