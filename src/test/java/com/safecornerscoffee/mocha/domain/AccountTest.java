package com.safecornerscoffee.mocha.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AccountTest {

    @Test
    public void createAccount() {
        Account.builder()
            .email("mocha@safecornerscoffee.com")
            .password("mocha")
            .firstName("name")
            .lastName("coffee")
            .address1("address1")
            .address2("address2")
            .city("city")
            .state("state")
            .postalCode("06232")
            .phoneNumber("010-4442-4442")
            .build();
    }
}