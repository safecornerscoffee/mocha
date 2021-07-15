package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Product;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public Product getProductById(Long id) {
        return productMapper.getProductById(id);
    }

    public Product getProductBySlug(String slug) {
        return productMapper.getProductBySlug(slug);
    }

    public Product getProductByName(String name) {
        return productMapper.getProductByName(name);
    }

    public List<Product> getProducts() {
        return productMapper.getProducts();
    }

    @Transactional
    public Long createProduct(Product product) {
        productMapper.insertProduct(product);
        return product.getId();
    }

    @Transactional
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }
}
