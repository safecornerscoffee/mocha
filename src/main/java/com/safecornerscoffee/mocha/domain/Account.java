package com.safecornerscoffee.mocha.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of={"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Account {
    private Long id;
    private String email;
    private String password;
    private Name name;
    private Address address;
    private String phoneNumber;
    private List<Authority> authorities = new ArrayList<>();

    @Builder
    public Account(Long id, String email, String password, Name name, Address address, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        authorities.add(new Authority("ROLE_USER"));
    }
}
