package com.safecornerscoffee.mocha.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class UpdateItemCommand {
    private Long itemId;
    private String name;
    private String description;
    private int price;
    private int stock;

    @Builder
    private UpdateItemCommand(Long itemId, String name, String description, int price, int stock) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
