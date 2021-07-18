package com.safecornerscoffee.mocha.domain.order;

import com.safecornerscoffee.mocha.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class OrderLine {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int lineNumber;
    private int quantity;
    private int price;

    private Item item;

}
