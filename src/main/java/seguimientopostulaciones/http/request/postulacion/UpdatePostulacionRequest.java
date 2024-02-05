package seguimientopostulaciones.http.request.postulacion;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import seguimientopostulaciones.entity.util.Estado;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdatePostulacionRequest {
    @NotNull
    private Long id;
    private String puesto;
    private String empresa;
    private String enlace;
    private Estado estado;
}
