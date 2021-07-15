package com.safecornerscoffee.mocha.domain;

import lombok.*;

@EqualsAndHashCode(of={"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Product {
    private Long id;
    private String slug;
    private String name;
    private String description;
    private int quantity;
    private int price;

    @Builder
    public Product(String name, String description, int quantity, int price) {
        this.name = name;
        this.slug = name.toLowerCase().replace(' ', '-');
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
}
