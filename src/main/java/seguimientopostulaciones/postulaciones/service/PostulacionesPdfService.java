package seguimientopostulaciones.postulaciones.service;

import com.itextpdf.text.DocumentException;
import seguimientopostulaciones.postulaciones.domain.dto.PostulacionResponse;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface PostulacionesPdfService {
    ByteArrayOutputStream generarPdf(List<PostulacionResponse> postulacionResponseList) throws DocumentException;
}
