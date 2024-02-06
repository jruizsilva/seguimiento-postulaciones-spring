package seguimientopostulaciones.util.reportes;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostulacionesExporterPDF {
    private List<PostulacionResponse> postulaciones;

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);
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

    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for (PostulacionResponse postulacion : postulaciones) {
            tabla.addCell(String.valueOf(postulacion.getId()));
            tabla.addCell(postulacion.getPuesto());
            tabla.addCell(postulacion.getEmpresa());
            tabla.addCell(postulacion.getPlataforma());
            tabla.addCell(postulacion.getEnlace());
            tabla.addCell(postulacion.getFecha()
                                     .toString());
            tabla.addCell(postulacion.getEstado()
                                     .toString());
        }
    }

    public void exportar(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,
                              response.getOutputStream());
        document.open();
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de postulaciones",
                                         fuente);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{1f, 2.3f, 2.3f, 6f, 2.9f, 3.5f, 2.2f});
        table.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(table);
        escribirDatosDeLaTabla(table);

        document.add(table);
        document.close();
    }
}
