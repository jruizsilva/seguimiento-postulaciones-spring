package seguimientopostulaciones.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seguimientopostulaciones.entity.ProductEntity;
import seguimientopostulaciones.exceptions.CustomEntityNotFoundException;
import seguimientopostulaciones.http.request.product.CreateProductRequest;
import seguimientopostulaciones.http.request.product.UpdateProductRequest;
import seguimientopostulaciones.http.response.product.ProductResponse;
import seguimientopostulaciones.mapper.ProductMapper;
import seguimientopostulaciones.repository.ProductRepository;
import seguimientopostulaciones.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        ProductEntity productEntityToSave = productMapper.createProductRequestToEntity(createProductRequest);
        ProductEntity productEntitySaved = productRepository.save(productEntityToSave);
        return productMapper.entityToResponse(productEntitySaved);
    }

    @Override
    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(updateProductRequest.getId());

        if (productEntityOptional.isEmpty()) {
            throw new CustomEntityNotFoundException("product to edit not found");
        }
        ProductEntity productEntity = productEntityOptional.get();
        ProductEntity productEntityToUpdate = productMapper.updateProductRequestToEntity(updateProductRequest,
                                                                                         productEntity);
        ProductEntity productEntityUpdated = productRepository.save(productEntityToUpdate);
        return productMapper.entityToResponse(productEntityUpdated);
    }

    @Override
    public ProductResponse findProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("product not found"));
        return productMapper.entityToResponse(productEntity);
    }

    @Override
    public ProductResponse findProductByName(String name) {
        ProductEntity productEntity = productRepository.findProductByName(name)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("product not found"));
        return productMapper.entityToResponse(productEntity);
    }

    @Override
    public void deleteProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("product to delete not found"));

        productRepository.delete(productEntity);
    }

    @Override
    public List<ProductResponse> findAllProductsSortedByPriceAsc() {
        List<ProductEntity> productEntityList = productRepository.findAllProductsSortedByPriceAsc();
        return productEntityList.stream()
                                .map(productMapper::entityToResponse)
                                .toList();
    }

    @Override
    public List<ProductResponse> findAllProductsSortedByPriceDesc() {
        List<ProductEntity> productEntityList = productRepository.findAllProductsSortedByPriceDesc();
        return productEntityList.stream()
                                .map(productMapper::entityToResponse)
                                .toList();
    }

    @Override
    public List<ProductResponse> findAllProductsByName(String name) {
        List<ProductEntity> productEntityList = productRepository.findAllProductsByName(name);
        return productEntityList.stream()
                                .map(productMapper::entityToResponse)
                                .toList();
    }
}
