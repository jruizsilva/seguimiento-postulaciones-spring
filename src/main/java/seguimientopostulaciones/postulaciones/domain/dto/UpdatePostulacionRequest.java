package seguimientopostulaciones.postulaciones.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import seguimientopostulaciones.utils.Estado;

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
    private String plataforma;
}
