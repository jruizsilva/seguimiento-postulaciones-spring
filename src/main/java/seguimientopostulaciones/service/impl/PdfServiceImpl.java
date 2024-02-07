package seguimientopostulaciones.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;
import seguimientopostulaciones.service.PdfService;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {
    @Override
    public ByteArrayOutputStream generarPdf(List<PostulacionResponse> postulaciones) throws DocumentException {
        // Crear un documento PDF
        Document document = new Document();
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document,
                              pdfStream);
        document.open();

        // Crear una tabla
        PdfPTable table = new PdfPTable(7); // 7 columnas
        table.setWidthPercentage(100);

        escribirCabeceraDeLaTabla(table);
        escribirDatosDeLaTabla(table,
                               postulaciones);
        // Añadir la tabla al documento
        document.add(table);

        // Cerrar el documento
        document.close();
        return pdfStream;
    }

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(BaseColor.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(BaseColor.WHITE);
        celda.setPhrase(new Phrase("ID",
                                   fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Puesto",
                                   fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Empresa",
                                   fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Plataforma",
                                   fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Enlace",
                                   fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Fecha",
                                   fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Estado",
                                   fuente));
        tabla.addCell(celda);
    }

    private void escribirDatosDeLaTabla(PdfPTable tabla,
                                        List<PostulacionResponse> postulaciones) {
        for (PostulacionResponse postulacion : postulaciones) {
            tabla.addCell(String.valueOf(postulacion.getId()));
            tabla.addCell(postulacion.getPuesto());
            tabla.addCell(postulacion.getEmpresa());
            tabla.addCell(postulacion.getPlataforma());
            tabla.addCell(createAnchorCell(postulacion.getEnlace(),
                                           "Abrir enlace"));
            tabla.addCell(postulacion.getFecha());
            tabla.addCell(postulacion.getEstado()
                                     .toString());
        }
    }

    private static PdfPCell createAnchorCell(String url,
                                             String text) {
        PdfPCell cell = new PdfPCell();

        Chunk chunk = new Chunk(text);
        chunk.setFont(anchorStyles());
        chunk.setAnchor(url);
        cell.addElement(chunk);
        return cell;
    }

    private static Font anchorStyles() {
        // Crear una fuente con estilo de enlace
        Font font = new Font(Font.FontFamily.HELVETICA,
                             12,
                             Font.UNDERLINE);
        font.setColor(BaseColor.BLUE);
        return font;
    }
}
