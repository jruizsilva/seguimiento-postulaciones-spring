package seguimientopostulaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seguimientopostulaciones.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("select p from ProductEntity p where upper(p.nombre) = upper(:nombre)")
    Optional<ProductEntity> findProductByName(@Param("nombre") String nombre);
    @Query("select p from ProductEntity p where upper(p.nombre) like upper(concat('%', :nombre, '%'))")
    List<ProductEntity> findAllProductsByName(@Param("nombre") String nombre);
    @Query("select p from ProductEntity p order by p.precio ASC")
    List<ProductEntity> findAllProductsSortedByPriceAsc();
    @Query("select p from ProductEntity p order by p.precio DESC")
    List<ProductEntity> findAllProductsSortedByPriceDesc();
}