package seguimientopostulaciones.service;

import seguimientopostulaciones.http.request.product.CreateProductRequest;
import seguimientopostulaciones.http.request.product.UpdateProductRequest;
import seguimientopostulaciones.http.response.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest createProductRequest);
    ProductResponse updateProduct(UpdateProductRequest updateProductRequest);
    ProductResponse findProductById(Long productId);
    ProductResponse findProductByName(String name);
    void deleteProductById(Long productId);
    List<ProductResponse> findAllProductsSortedByPriceAsc();
    List<ProductResponse> findAllProductsSortedByPriceDesc();
    List<ProductResponse> findAllProductsByName(String name);
}
