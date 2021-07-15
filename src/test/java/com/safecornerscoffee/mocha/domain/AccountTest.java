package com.safecornerscoffee.mocha.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AccountTest {

    @Test
    public void createAccount() {
        Account.builder()
            .email("mocha@safecornerscoffee.com")
            .password("mocha")
            .name(new Name("mocha", "coffee"))
            .address(new Address("address1", "address2", "city", "state", "06332"))
            .phoneNumber("010-4442-4442")
            .build();
    }
}