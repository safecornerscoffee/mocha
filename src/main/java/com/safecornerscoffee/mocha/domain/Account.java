package com.safecornerscoffee.mocha.domain;

import lombok.*;

@EqualsAndHashCode(of={"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Account {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String phoneNumber;

    @Builder
    public Account(Long id, String email, String password, String firstName, String lastName, String address1, String address2, String city, String state, String postalCode, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }
}
