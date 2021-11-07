package pl.trosko.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.trosko.product.dto.ProductDto;
import pl.trosko.product.entity.Product;
import pl.trosko.product.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getByUuid(@RequestParam("id") List<Long> ids) {
        log.info("GET: Get products");

        List<ProductDto> products = productService.getAll(ids).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PostMapping("{creditId}")
    public ResponseEntity<ProductDto> createProduct(@PathVariable Long creditId, @RequestBody ProductDto product) {
        log.info("GET: Create new product: {}", product);

        Product savedProduct = productService.createProduct(creditId, modelMapper.map(product, Product.class));
        return ResponseEntity.ok(modelMapper.map(savedProduct, ProductDto.class));
    }
}
