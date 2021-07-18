package com.safecornerscoffee.mocha.domain.cart;

import com.safecornerscoffee.mocha.domain.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Cart {

    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(Item item, int quantity) {
        cartItems.add(new CartItem(item, quantity));
    }

    public int size() {
        return cartItems.size();
    }

    public int calculateTotalPrice() {
        return cartItems.stream().mapToInt(CartItem::calculatePrice).sum();
    }
}