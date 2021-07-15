package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Product;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductMapper productMapper;

    @Test
    public void ShouldGetProductById() {
        //given
        Long id = 1L;
        Product product = storeProduct(id);
        given(productMapper.getProductById(id)).willReturn(product);

        //when
        productService.getProductById(id);

        //then
        then(productMapper).should().getProductById(id);
    }

    @Test
    public void ShouldGetProductBySlug() {
        //given
        Product product = storeProduct(1L);
        given(productMapper.getProductBySlug(anyString())).willReturn(product);

        //when
        productService.getProductBySlug(product.getName());

        //then
        then(productMapper).should().getProductBySlug(anyString());
    }

    @Test
    public void ShouldGetProductByName() {
        //given
        Product product = storeProduct(1L);
        given(productMapper.getProductBySlug(anyString())).willReturn(product);

        //when
        productService.getProductBySlug(product.getName());

        //then
        then(productMapper).should().getProductBySlug(anyString());
    }

    @Test
    public void shouldGetProducts() {
        //given
        given(productMapper.getProducts()).willReturn(Collections.emptyList());

        //when
        productService.getProducts();

        //then
        then(productMapper).should().getProducts();
    }

    @Test
    public void shouldCreateProduct() {
        //given
        Product product = createProduct();
        will(invocationOnMock -> {
            Product p = invocationOnMock.getArgument(0, Product.class);
            p.setId(1L);
            return null;
        }).given(productMapper).insertProduct(product);

        //when
        Long id = productService.createProduct(product);

        //then
        then(productMapper).should().insertProduct(product);
        assertThat(id).isNotNull();
    }

    @Test
    public void shouldUpdateProduct() {
        //given
        Product product = storeProduct(1L);

        //when
        productService.updateProduct(product);

        //then
        then(productMapper).should().updateProduct(product);
    }

    @Test
    public void shouldDeleteProduct() {
        //given
        Product product = storeProduct(1L);

        //when
        productService.deleteProduct(product.getId());

        //then
        then(productMapper).should().deleteProduct(product.getId());
    }

    private Product createProduct() {
        return Product.builder()
                .name("Ethiopia Mordecofe Organic")
                .description("Floral, sweet aroma and peach flavor. Garden Coffees, grown in small holder plots (usually measured in terms of trees rather than hectares) along with other crops.")
                .price(13500)
                .quantity(100)
                .build();
    }

    private Product storeProduct(Long id) {
        Product product =  Product.builder()
                .name("Ethiopia Mordecofe Organic")
                .description("Floral, sweet aroma and peach flavor. Garden Coffees, grown in small holder plots (usually measured in terms of trees rather than hectares) along with other crops.")
                .price(13500)
                .quantity(100)
                .build();
        product.setId(id);
        return product;
    }

}