package seguimientopostulaciones.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seguimientopostulaciones.http.request.postulacion.CreatePostulacionRequest;
import seguimientopostulaciones.http.request.postulacion.UpdatePostulacionRequest;
import seguimientopostulaciones.http.response.postulacion.PostulacionResponse;
import seguimientopostulaciones.service.PostulacionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
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

    @DeleteMapping("/{postulacionId}")
    public ResponseEntity<Void> deletePostulacion(@PathVariable Long postulacionId) {
        postulacionService.deletePostulacionById(postulacionId);
        return ResponseEntity.noContent()
                             .build();
    }
}
