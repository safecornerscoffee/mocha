package com.safecornerscoffee.mocha.domain.cart;

import com.safecornerscoffee.mocha.domain.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void addItem(Item item, int quantity) {
        items.add(new CartItem(item, quantity));
    }

    public int size() {
        return items.size();
    }

    public int calculateTotalPrice() {
        return items.stream().mapToInt(CartItem::calculatePrice).sum();
    }
}