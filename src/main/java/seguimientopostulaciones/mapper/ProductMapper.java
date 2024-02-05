package seguimientopostulaciones.mapper;

import seguimientopostulaciones.entity.ProductEntity;
import seguimientopostulaciones.http.request.product.CreateProductRequest;
import seguimientopostulaciones.http.request.product.UpdateProductRequest;
import seguimientopostulaciones.http.response.product.ProductResponse;

public interface ProductMapper {
    ProductEntity createProductRequestToEntity(CreateProductRequest createProductRequest);

    ProductEntity updateProductRequestToEntity(UpdateProductRequest updateProductRequest,
                                               ProductEntity productEntity);

    ProductResponse entityToResponse(ProductEntity productEntity);
}
