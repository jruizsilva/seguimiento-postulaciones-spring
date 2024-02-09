package seguimientopostulaciones.postulacionesespontanes.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import seguimientopostulaciones.utils.Estado;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "postulaciones_espontaneas")
public class PostulacionEspontaneaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private Long id;
    private String nombre;
    private String empresa;
    @Column(length = 3000)
    @Size(max = 3000)
    private String mensaje;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @PrePersist
    protected void onCreate() {
        fecha = new Date();
    }

}