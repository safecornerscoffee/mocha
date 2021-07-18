package com.safecornerscoffee.mocha.domain.cart;

import com.safecornerscoffee.mocha.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Setter
public class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void changeQuantity(int quantity) {
        if (quantity > 0)
        this.quantity = quantity;
    }

    public int calculatePrice() {
        return item.getPrice() * quantity;
    }


}
