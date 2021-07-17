package com.safecornerscoffee.mocha.domain;

import com.safecornerscoffee.mocha.exception.OutOfStockException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ItemTest {

    @Test
    public void createItem() {
        Product product =createProduct("Cold Brew Decaf");

        Item item = Item.builder()
                .product(product)
                .price(3300)
                .stock(10)
                .build();
    }

    @Test
    public void throwOutOtStockExceptionWhenRemovingQuantityIsGreaterThanStock() {
        Product product =createProduct("Cold Brew Decaf");

        Item item = Item.builder()
                .product(product)
                .price(3300)
                .stock(10)
                .build();

        assertThatThrownBy(() -> {
            item.removeStockQuantity(item.getStock()+ 1);
        }).isInstanceOf(OutOfStockException.class);
    }

    private Product createProduct(String name) {
        return Product.builder()
                .name(name)
                .description(name)
                .build();
    }

}