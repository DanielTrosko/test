package pl.trosko.product.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.trosko.product.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query( "select p from Product p where p.id in :ids" )
    List<Product> findByIds(@Param("ids") List<Long> ids);

    Optional<Product> findById(Long id);
}
