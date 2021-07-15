package com.safecornerscoffee.mocha.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ProductTest {

    @Test
    public void CreateProduct() {
        Product product = Product.builder()
                .name("monolith")
                .description("This is our house blend that strikes a perfect balance of sweetness and richness.")
                .price(13000)
                .quantity(100)
                .build();
    }

    @Test
    public void ShouldHaveSlug() {
        Product product = Product.builder()
                .name("Guatemala Natural Decaf")
                .description("Natural Decaf coffee is decaffeinated using Ethyl Acetate (EA).")
                .price(13500)
                .quantity(100)
                .build();

        assertThat(product.getSlug()).isEqualTo("guatemala-natural-decaf");
    }
}