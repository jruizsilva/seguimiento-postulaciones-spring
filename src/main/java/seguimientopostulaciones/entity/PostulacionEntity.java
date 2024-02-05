package seguimientopostulaciones.entity;

import jakarta.persistence.*;
import lombok.*;
import seguimientopostulaciones.entity.util.Estado;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "postulaciones")
public class PostulacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private Long id;

    private String puesto;
    private String empresa;
    private String enlace;
    private Date fecha;
    @Enumerated(EnumType.STRING)
    private Estado estado;
}