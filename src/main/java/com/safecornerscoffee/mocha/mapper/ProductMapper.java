package com.safecornerscoffee.mocha.mapper;

import com.safecornerscoffee.mocha.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    Product getProductById(Long id);
    Product getProductBySlug(String slug);
    Product getProductByName(String name);
    List<Product> getProducts();

    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}
