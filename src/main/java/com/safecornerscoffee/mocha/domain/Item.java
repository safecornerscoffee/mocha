package com.safecornerscoffee.mocha.domain;

import com.safecornerscoffee.mocha.exception.OutOfStockException;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Item {
    private Long id;
    private Product product;

    private int stock;
    private int price;

    @Builder
    private Item(Product product, int stock, int price) {
        this.product = product;
        this.stock = stock;
        this.price = price;
    }

    public Item(Long id, Product product, int stock, int price) {
        this.id = id;
        this.product = product;
        this.stock = stock;
        this.price = price;
    }

    public void addStockQuantity(int quantity) {
        this.stock += quantity;
    }

    public void removeStockQuantity(int quantity) {
        if (this.stock - quantity < 0) {
            throw new OutOfStockException();
        }
        this.stock -= quantity;
    }
}
