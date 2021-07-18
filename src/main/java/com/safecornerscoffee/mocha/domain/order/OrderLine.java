package com.safecornerscoffee.mocha.domain.order;

import com.safecornerscoffee.mocha.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Setter
public class OrderLine {
    private Long id;
    private Order Order;
    private int lineNumber;

    private Item item;
    private int quantity;
    private int price;



    protected static OrderLine createOrderLine(int lineNumber, Order order, Item item, int quantity) {
        OrderLine line = new OrderLine();
        line.setLineNumber(lineNumber);
        line.setOrder(order);
        line.setItem(item);
        line.setQuantity(quantity);
        line.setPrice(item.getPrice());

        return line;
    }

    public int calculatePrice() {
        return getItem().getPrice() * getQuantity();
    }
}
