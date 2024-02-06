package seguimientopostulaciones.service;

import com.itextpdf.text.DocumentException;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface PdfService {
    ByteArrayOutputStream generarPdf(List<PostulacionResponse> postulacionResponseList) throws DocumentException;
}
