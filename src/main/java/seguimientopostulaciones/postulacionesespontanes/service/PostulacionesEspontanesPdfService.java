package seguimientopostulaciones.postulacionesespontanes.service;

import com.itextpdf.text.DocumentException;
import seguimientopostulaciones.postulacionesespontanes.domain.PostulacionEspontaneaEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface PostulacionesEspontanesPdfService {
    ByteArrayOutputStream generarPdf(List<PostulacionEspontaneaEntity> postulacionEspontaneaEntityList) throws DocumentException;
}
