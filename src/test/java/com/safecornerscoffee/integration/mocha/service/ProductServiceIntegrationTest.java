package com.safecornerscoffee.integration.mocha.service;

import com.safecornerscoffee.mocha.domain.Product;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
import com.safecornerscoffee.mocha.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ProductServiceIntegrationTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    Product product;
    Product otherProduct;

    @Before
    public void setUp() {
        product = createEthiopia();
        otherProduct = createGuatemala();
        productMapper.insertProduct(product);
        productMapper.insertProduct(otherProduct);
    }

    @Test
    public void getProductById() {
        //when
        Product other = productService.getProductById(product.getId());

        //then
        assertThat(other).isEqualTo(product);
    }

    @Test
    public void getProductBySlug() {
        //when
        Product other = productService.getProductBySlug(product.getSlug());

        //then
        assertThat(other).isEqualTo(product);
    }

    @Test
    public void getProductByName() {
        //when
        Product other = productService.getProductByName(product.getName());

        //then
        assertThat(other).isEqualTo(product);
    }

    @Test
    public void getProducts() {
        //when
        List<Product> products = productService.getProducts();

        //then
        assertThat(products).hasSize(2);
        assertThat(products).contains(product, otherProduct);
    }

    @Test
    public void createProduct() {
        //given
        Product newProduct = Product.builder()
                .name("Guatemala Natural Organic")
                .description("Natural Decaf coffee is decaffeinated using Ethyl Acetate (EA).")
                .price(18000)
                .quantity(30)
                .build();

        //when
        Long productId = productService.createProduct(newProduct);

        //then
        assertThat(productId).isNotNull();
    }

    @Test
    public void updateProduct() {
        //when
        String updateName = "Ethiopia Mordecofe Organic Decaf";
        product.setName(updateName);
        productService.updateProduct(product);

        //then
        Product updatedProduct = productMapper.getProductById(product.getId());
        assertThat(updatedProduct.getName()).isEqualTo(updateName);
    }

    @Test
    public void deleteProduct() {
        //when
        productService.deleteProduct(product.getId());

        //then
        Product deleted = productMapper.getProductById(product.getId());

        assertThat(deleted).isNull();
    }

    private Product createEthiopia() {
        return Product.builder()
                .name("Ethiopia Mordecofe Organic")
                .description("Floral, sweet aroma and peach flavor. Garden Coffees, grown in small holder plots (usually measured in terms of trees rather than hectares) along with other crops.")
                .price(13500)
                .quantity(100)
                .build();
    }

    private  Product createGuatemala() {
        return Product.builder()
                .name("Guatemala Natural Decaf")
                .description("Natural Decaf coffee is decaffeinated using Ethyl Acetate (EA).")
                .price(13500)
                .quantity(100)
                .build();
    }

}