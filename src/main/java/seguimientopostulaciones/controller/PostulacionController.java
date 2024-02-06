package seguimientopostulaciones.controller;

import com.itextpdf.text.DocumentException;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;
import seguimientopostulaciones.service.PostulacionService;
import seguimientopostulaciones.util.pdf.PdfUtils;
import seguimientopostulaciones.util.reportes.PostulacionesExporterExcel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postulaciones")
@CrossOrigin
public class PostulacionController {
    private final PostulacionService postulacionService;

    @PostMapping
    public ResponseEntity<PostulacionResponse> createProduct(@Valid @RequestBody
                                                             CreatePostulacionRequest createPostulacionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(postulacionService.createPostulacion(createPostulacionRequest));
    }

    @PatchMapping
    public ResponseEntity<PostulacionResponse> updateProduct(@Valid @RequestBody
                                                             UpdatePostulacionRequest updatePostulacionRequest) {
        return ResponseEntity.ok(postulacionService.updatePostulacion(updatePostulacionRequest));
    }

    @GetMapping("/{postulacionId}")
    public ResponseEntity<PostulacionResponse> findProductById(@PathVariable Long postulacionId) {
        return ResponseEntity.ok(postulacionService.findPostulacionById(postulacionId));
    }

    @GetMapping
    public ResponseEntity<List<PostulacionResponse>> findAllPostulaciones() {
        return ResponseEntity.ok(postulacionService.findAllPostulaciones());
    }

    @DeleteMapping("/{postulacionId}")
    public ResponseEntity<Void> deletePostulacion(@PathVariable Long postulacionId) {
        postulacionService.deletePostulacionById(postulacionId);
        return ResponseEntity.noContent()
                             .build();
    }

    @GetMapping("/generarPDF")
    public ResponseEntity<byte[]> generarListadoDePostulacionesEnPDF(HttpServletResponse response) throws IOException {
        // Crear el documento PDF con OpenPDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,
                              baos);
        document.open();
        document.add(new Paragraph("Hola, este es un PDF generado con OpenPDF."));
        document.close();

        // Configurar la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",
                                              "archivo.pdf");

        // Enviar el archivo PDF como bytes en la respuesta
        return ResponseEntity.ok()
                             .headers(headers)
                             .body(baos.toByteArray());
    }

    @GetMapping("/generarExcel")
    public void generarListadoDePostulacionesEnExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Postulaciones_" + fechaActual + ".xlsx";

        response.setHeader(cabecera,
                           valor);

        List<PostulacionResponse> postulaciones = postulacionService.findAllPostulaciones();
        PostulacionesExporterExcel exporterExcel = new PostulacionesExporterExcel(postulaciones);
        exporterExcel.exportar(response);
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPdf() throws DocumentException {
        List<Map<String, Object>> queryResults = postulacionService.findAllMapPostulaciones();
        ByteArrayOutputStream pdfStream = PdfUtils.generatePdfStream(queryResults);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(),
                                    headers,
                                    HttpStatus.OK);
    }
}
