package com.safecornerscoffee.mocha.domain.cart;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.domain.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {


    @Test
    public void size() {
        //given
        Cart cart = new Cart();
        Item item = createItem("Kenya Green Bean", 18000, 30);
        Item other = createItem("Kenya Decaf", 12000, 10);

        //when
        cart.addItem(item, 3);
        cart.addItem(other, 4);

        //then
        assertThat(cart.size()).isEqualTo(2);
    }

    @Test
    public void calculateTotalPrice() {
        //given
        Cart cart = new Cart();
        Item item = createItem("Kenya Green Bean", 18000, 30);
        Item other = createItem("Kenya Decaf", 12000, 10);

        //when
        cart.addItem(item, 3);
        cart.addItem(other, 4);

        //then
        assertThat(cart.calculateTotalPrice()).isEqualTo(3*18000+4*12000);
    }

    private Item createItem(String productName, int price, int quantity) {
        Product product = Product.builder()
                .name(productName)
                .description(productName)
                .build();
        return Item.builder()
                .product(product)
                .price(price)
                .stock(quantity)
                .build();
    }
}