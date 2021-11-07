package pl.trosko.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.trosko.product.entity.Product;
import pl.trosko.product.respository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll(List<Long> ids) {
        return productRepository.findByIds(ids);
    }

    public Product createProduct(Long creditId, Product product) {
        product.setCreditId(creditId);
        return productRepository.save(product);
    }
}
