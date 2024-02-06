package seguimientopostulaciones.controller;

import com.itextpdf.text.DocumentException;
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
import seguimientopostulaciones.service.PdfService;
import seguimientopostulaciones.service.PostulacionService;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postulaciones")
@CrossOrigin
public class PostulacionController {
    private final PostulacionService postulacionService;
    private final PdfService pdfService;

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

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> generateTablePdf() throws DocumentException {
        // Crear un documento PDF
        ByteArrayOutputStream pdfStream = pdfService.generarPdf(postulacionService.findAllPostulaciones());

        // Configurar la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=table.pdf");
        headers.setContentLength(pdfStream.size());

        return new ResponseEntity<>(pdfStream.toByteArray(),
                                    headers,
                                    HttpStatus.OK);
    }
}
