package seguimientopostulaciones.http.request.postulacion;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePostulacionRequest {
    @NotNull
    private String puesto;
    @NotNull
    private String empresa;
    @NotNull
    private String plataforma;

    private String enlace;
}
