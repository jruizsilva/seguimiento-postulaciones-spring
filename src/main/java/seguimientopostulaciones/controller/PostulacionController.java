package seguimientopostulaciones.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;
import seguimientopostulaciones.service.PostulacionService;
import seguimientopostulaciones.util.reportes.PostulacionesExporterExcel;
import seguimientopostulaciones.util.reportes.PostulacionesExporterPDF;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/exportarPDF")
    public void exportarListadoDePostulacionesEnPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Empleados_" + fechaActual + ".pdf";

        response.setHeader(cabecera,
                           valor);

        List<PostulacionResponse> postulaciones = postulacionService.findAllPostulaciones();
        PostulacionesExporterPDF exporterPDF = new PostulacionesExporterPDF(postulaciones);
        exporterPDF.exportar(response);
    }

    @GetMapping("/exportarExcel")
    public void exportarListadoDePostulacionesEnExcel(HttpServletResponse response) throws IOException {
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
}
