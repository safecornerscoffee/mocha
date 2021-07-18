package com.safecornerscoffee.mocha.domain.cart;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.domain.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartItemTest {

    @Test
    public void createCartItem() {
        Item item = createItem("Kenya Green Bean", 12000, 30);
        CartItem cartItem = new CartItem(item, 3);
    }

    @Test
    public void calculatePrice() {
        //given
        Item item = createItem("Kenya Green Bean", 10000, 100);

        //when
        CartItem cartItem = new CartItem(item, 3);

        //then
        assertThat(cartItem.calculatePrice()).isEqualTo(30000);
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