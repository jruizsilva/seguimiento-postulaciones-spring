package seguimientopostulaciones.http.request.postulacionespontanea;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import seguimientopostulaciones.entity.util.Estado;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdatePostulacionEspontaneaRequest {
    @NotNull
    private Long id;
    private String nombre;
    private String empresa;
    private String mensaje;
    private Estado estado;
}
