package seguimientopostulaciones.http.request.postulacion;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePostulacionEspontaneaRequest {
    @NotNull
    private String nombre;
    @NotNull
    private String empresa;
    @NotNull
    private String mensaje;
}
