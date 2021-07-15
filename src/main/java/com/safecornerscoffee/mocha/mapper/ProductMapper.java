package com.safecornerscoffee.mocha.mapper;

import com.safecornerscoffee.mocha.domain.Product;

import java.util.List;

public interface ProductMapper {

    Product getProductById(Long id);
    Product getProductBySlug(String slug);
    Product getProductByName(String name);
    List<Product> getProducts();

    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}
