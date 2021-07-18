package com.safecornerscoffee.mocha.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ProductTest {

    @Test
    public void CreateProduct() {
        Product product = Product.builder()
                .name("Ethiopia Mordecofe Organic")
                .description("Floral, sweet aroma and peach flavor. Garden Coffees, grown in small holder plots (usually measured in terms of trees rather than hectares) along with other crops.")
                .build();
    }

    @Test
    public void ShouldHaveSlug() {
        Product product = Product.builder()
                .name("Guatemala Natural Decaf")
                .description("Natural Decaf coffee is decaffeinated using Ethyl Acetate (EA).")
                .build();

        assertThat(product.getSlug()).isEqualTo("guatemala-natural-decaf");
    }
}