package seguimientopostulaciones.util.reportes;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;

import java.io.IOException;
import java.util.List;

public class PostulacionesExporterExcel {
    private List<PostulacionResponse> postulaciones;

    private XSSFWorkbook libro;
    private XSSFSheet hoja;

    public PostulacionesExporterExcel(List<PostulacionResponse> postulaciones) {
        this.postulaciones = postulaciones;
        this.libro = new XSSFWorkbook();
        this.hoja = this.libro.createSheet("Postulaciones");
    }

    private void escribirCabeceraDeLaTabla() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);

        celda = fila.createCell(1);
        celda.setCellValue("Puesto");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("Empresa");
        celda.setCellStyle(estilo);

        celda = fila.createCell(3);
        celda.setCellValue("Plataforma");
        celda.setCellStyle(estilo);

        celda = fila.createCell(4);
        celda.setCellValue("Enlace");
        celda.setCellStyle(estilo);

        celda = fila.createCell(5);
        celda.setCellValue("Fecha");
        celda.setCellStyle(estilo);

        celda = fila.createCell(6);
        celda.setCellValue("Estado");
        celda.setCellStyle(estilo);
    }

    private void escribirDatosDeLaTabla() {
        int numeroFilas = 1;
        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for (PostulacionResponse empleado : postulaciones) {
            Row fila = hoja.createRow(numeroFilas++);

            Cell celda = fila.createCell(0);
            celda.setCellValue(empleado.getId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue(empleado.getPuesto());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);

            celda = fila.createCell(2);
            celda.setCellValue(empleado.getEmpresa());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);

            celda = fila.createCell(3);
            celda.setCellValue(empleado.getPlataforma());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);

            celda = fila.createCell(4);
            celda.setCellValue(empleado.getEnlace());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);

            celda = fila.createCell(5);
            celda.setCellValue(empleado.getFecha()
                                       .toString());
            hoja.autoSizeColumn(5);
            celda.setCellStyle(estilo);

            celda = fila.createCell(6);
            celda.setCellValue(empleado.getEstado()
                                       .toString());
            hoja.autoSizeColumn(6);
            celda.setCellStyle(estilo);
        }
    }

    public void exportar(HttpServletResponse response) throws IOException {
        escribirCabeceraDeLaTabla();
        escribirDatosDeLaTabla();

        ServletOutputStream outputStream = response.getOutputStream();
        libro.write(outputStream);

        libro.close();
        outputStream.close();
    }
}
