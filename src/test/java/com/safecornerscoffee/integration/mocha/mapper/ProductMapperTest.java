package com.safecornerscoffee.integration.mocha.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.safecornerscoffee.mocha.domain.Product;
import com.safecornerscoffee.mocha.mapper.ProductMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "file:src/main/webapp/WEB-INF/applicationContext.xml",
    "file:src/main/webapp/WEB-INF/securityContext.xml"
})
@Transactional
public class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void getProductById() {
        //given
        Product product = createProduct();
        productMapper.insertProduct(product);

        //when
        Product found = productMapper.getProductById(product.getId());

        //then
        assertThat(found.getId()).isEqualTo(product.getId());
        assertThat(found.getName()).isEqualTo(product.getName());
        assertThat(found.getSlug()).isEqualTo(product.getSlug());
        assertThat(found.getDescription()).isEqualTo(product.getDescription());
    }

    @Test
    public void getProductBySlug() {
        //given
        Product product = createProduct();
        productMapper.insertProduct(product);

        //when
        Product found = productMapper.getProductBySlug(product.getSlug());

        //then
        assertThat(found.getId()).isEqualTo(product.getId());
        assertThat(found.getName()).isEqualTo(product.getName());
        assertThat(found.getSlug()).isEqualTo(product.getSlug());
        assertThat(found.getDescription()).isEqualTo(product.getDescription());
    }

    @Test
    public void getProductByName() {
        //given
        Product product = createProduct();
        productMapper.insertProduct(product);

        //when
        Product found = productMapper.getProductByName(product.getName());

        //then
        assertThat(found.getId()).isEqualTo(product.getId());
        assertThat(found.getName()).isEqualTo(product.getName());
        assertThat(found.getSlug()).isEqualTo(product.getSlug());
        assertThat(found.getDescription()).isEqualTo(product.getDescription());
    }

    @Test
    public void getProducts() {
        //given
        Product product = createProduct();
        Product other = createOtherProduct();
        productMapper.insertProduct(product);
        productMapper.insertProduct(other);

        //when
        List<Product> products = productMapper.getProducts();

        //then
        assertThat(products).hasSize(2);
        assertThat(products).contains(product, other);
    }

    @Test
    public void insertProduct() {
        //given
        Product product = createProduct();

        //when
        productMapper.insertProduct(product);

        //then
        assertThat(product.getId()).isNotNull();
    }

    @Test
    public void updateProduct() {
        //given
        Product product = createProduct();
        productMapper.insertProduct(product);

        Product data = createOtherProduct();
        data.setId(product.getId());

        //when
        productMapper.updateProduct(data);

        //then
        Product updatedProduct = productMapper.getProductById(product.getId());
        assertThat(updatedProduct.getName()).isEqualTo(data.getName());
        assertThat(updatedProduct.getSlug()).isEqualTo(data.getSlug());
        assertThat(updatedProduct.getDescription()).isEqualTo(data.getDescription());
    }

    @Test
    public void deleteProduct() {
        //given
        Product product = createProduct();
        productMapper.insertProduct(product);

        //when
        productMapper.deleteProduct(product.getId());

        //then
        Product deletedProduct = productMapper.getProductById(product.getId());
        assertThat(deletedProduct).isNull();
    }

    private Product createProduct() {
        return Product.builder()
                .name("Ethiopia Mordecofe Organic")
                .description("Floral, sweet aroma and peach flavor. Garden Coffees, grown in small holder plots (usually measured in terms of trees rather than hectares) along with other crops.")
                .build();
    }

    private Product createOtherProduct() {
        return Product.builder()
                .name("Guatemala Natural Decaf")
                .description("Natural Decaf coffee is decaffeinated using Ethyl Acetate (EA).")
                .build();
    }
}