package seguimientopostulaciones.mapper.impl;

import org.springframework.stereotype.Component;
import seguimientopostulaciones.entity.ProductEntity;
import seguimientopostulaciones.http.request.product.CreateProductRequest;
import seguimientopostulaciones.http.request.product.UpdateProductRequest;
import seguimientopostulaciones.http.response.product.ProductResponse;
import seguimientopostulaciones.mapper.ProductMapper;

import java.math.BigDecimal;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductEntity createProductRequestToEntity(CreateProductRequest createProductRequest) {
        return ProductEntity.builder()
                            .nombre(createProductRequest.getNombre())
                            .descripcion(createProductRequest.getDescripcion())
                            .precio(createProductRequest.getPrecio())
                            .cantidad(createProductRequest.getCantidad())
                            .build();
    }

    @Override
    public ProductEntity updateProductRequestToEntity(UpdateProductRequest updateProductRequest,
                                                      ProductEntity productEntity) {
        String nombre = updateProductRequest.getNombre();
        String descripcion = updateProductRequest.getDescripcion();
        BigDecimal precio = updateProductRequest.getPrecio();
        Long cantidad = updateProductRequest.getCantidad();

        if (nombre != null && nombre.length() > 1) {
            productEntity.setNombre(nombre);
        }
        if (descripcion != null && descripcion.length() > 1) {
            productEntity.setDescripcion(descripcion);
        }
        BigDecimal minPrice = new BigDecimal("0.01");
        if (precio != null && precio.compareTo(minPrice) >= 0) {
            productEntity.setPrecio(precio);
        }
        if (cantidad != null && cantidad > 0) {
            productEntity.setCantidad(cantidad);
        }

        return productEntity;
    }

    @Override
    public ProductResponse entityToResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                              .id(productEntity.getId())
                              .nombre(productEntity.getNombre())
                              .descripcion(productEntity.getDescripcion())
                              .precio(productEntity.getPrecio())
                              .cantidad(productEntity.getCantidad())
                              .build();
    }
}
