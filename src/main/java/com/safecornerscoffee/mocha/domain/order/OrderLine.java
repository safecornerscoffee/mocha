package com.safecornerscoffee.mocha.domain.order;

import com.safecornerscoffee.mocha.domain.Item;
import lombok.*;

@EqualsAndHashCode(of={"id"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Setter
public class OrderLine {
    private Long id;
    private Long orderId;
    private Item item;

    private int lineNumber;
    private int quantity;
    private int price;

    protected static OrderLine createOrderLine(Long orderId, int lineNumber, Item item, int quantity) {
        OrderLine line = new OrderLine();
        line.setOrderId(orderId);
        line.setLineNumber(lineNumber);
        line.setItem(item);
        line.setQuantity(quantity);
        line.setPrice(item.getPrice());

        return line;
    }

    public int calculatePrice() {
        return getItem().getPrice() * getQuantity();
    }
}
