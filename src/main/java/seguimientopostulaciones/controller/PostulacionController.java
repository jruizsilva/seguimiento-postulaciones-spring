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
    public ResponseEntity<PostulacionResponse> create(@Valid @RequestBody
                                                      CreatePostulacionRequest createPostulacionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(postulacionService.create(createPostulacionRequest));
    }

    @PatchMapping
    public ResponseEntity<PostulacionResponse> update(@Valid @RequestBody
                                                      UpdatePostulacionRequest updatePostulacionRequest) {
        return ResponseEntity.ok(postulacionService.update(updatePostulacionRequest));
    }

    @GetMapping("/{postulacionId}")
    public ResponseEntity<PostulacionResponse> findById(@PathVariable Long postulacionId) {
        return ResponseEntity.ok(postulacionService.findById(postulacionId));
    }

    @GetMapping
    public ResponseEntity<List<PostulacionResponse>> findAll() {
        return ResponseEntity.ok(postulacionService.findAll());
    }

    @DeleteMapping("/{postulacionId}")
    public ResponseEntity<Void> delete(@PathVariable Long postulacionId) {
        postulacionService.deleteById(postulacionId);
        return ResponseEntity.noContent()
                             .build();
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> generatePdf() throws DocumentException {
        // Crear un documento PDF
        ByteArrayOutputStream pdfStream = pdfService.generarPdf(postulacionService.findAll());

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
