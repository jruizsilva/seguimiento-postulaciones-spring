package seguimientopostulaciones.postulacionesespontanes.service;

import com.itextpdf.text.DocumentException;
import seguimientopostulaciones.postulacionesespontanes.domain.PostulacionEspontaneaEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PostulacionesEspontanesPdfServiceImpl implements PostulacionesEspontanesPdfService {
    @Override
    public ByteArrayOutputStream generarPdf(List<PostulacionEspontaneaEntity> postulacionEspontaneaEntityList) throws DocumentException {
        return null;
    }
}
