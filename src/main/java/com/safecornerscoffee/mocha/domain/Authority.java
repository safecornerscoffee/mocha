package com.safecornerscoffee.mocha.domain;

import lombok.*;

@EqualsAndHashCode(of={"authority"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Authority {
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }
}
