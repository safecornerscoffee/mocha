package com.safecornerscoffee.mocha.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class CreateItemCommand {
    private String name;
    private String description;
    private int price;
    private int stock;

    @Builder
    private CreateItemCommand(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
