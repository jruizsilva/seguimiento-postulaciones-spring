package seguimientopostulaciones.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.request.postulacionespontanea.UpdatePostulacionEspontaneaRequest;
import seguimientopostulaciones.http.response.postulacionespontanea.PostulacionEspontaneaResponse;
import seguimientopostulaciones.service.PostulacionEspontaneaService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/postulaciones-espontaneas")
public class PostulacionEspontaneaController {
    private final PostulacionEspontaneaService postulacionEspontaneaService;

    @PostMapping
    public ResponseEntity<PostulacionEspontaneaResponse> create(@Valid @RequestBody
                                                                CreatePostulacionEspontaneaRequest createPostulacionEspontaneaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(postulacionEspontaneaService.create(createPostulacionEspontaneaRequest));
    }

    @PatchMapping
    public ResponseEntity<PostulacionEspontaneaResponse> update(@Valid @RequestBody
                                                                UpdatePostulacionEspontaneaRequest updatePostulacionEspontaneaRequest) {
        return ResponseEntity.ok(postulacionEspontaneaService.update(updatePostulacionEspontaneaRequest));
    }

    @GetMapping("/{postulacionEspontaneaId}")
    public ResponseEntity<PostulacionEspontaneaResponse> findById(@PathVariable Long postulacionEspontaneaId) {
        return ResponseEntity.ok(postulacionEspontaneaService.findById(postulacionEspontaneaId));
    }

    @GetMapping
    public ResponseEntity<List<PostulacionEspontaneaResponse>> findAll() {
        return ResponseEntity.ok(postulacionEspontaneaService.findAll());
    }

    @DeleteMapping("/{postulacionEspontaneaId}")
    public ResponseEntity<Void> delete(@PathVariable Long postulacionEspontaneaId) {
        postulacionEspontaneaService.deleteById(postulacionEspontaneaId);
        return ResponseEntity.noContent()
                             .build();
    }
}
