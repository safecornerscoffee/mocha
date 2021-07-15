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
    private Address address;
    private String phoneNumber;

    @Builder
    public Account(Long id, String email, String password, String firstName, String lastName, Address address, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
