package com.safecornerscoffee.mocha.domain;

import lombok.*;

@EqualsAndHashCode(of={"firstName", "lastName"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
