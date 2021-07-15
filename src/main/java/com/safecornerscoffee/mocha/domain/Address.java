package com.safecornerscoffee.mocha.domain;

import lombok.*;

@EqualsAndHashCode(of = {"address1", "address2", "city", "state", "postalCode"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Address {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;

    @Builder
    public Address(String address1, String address2, String city, String state, String postalCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }
}
