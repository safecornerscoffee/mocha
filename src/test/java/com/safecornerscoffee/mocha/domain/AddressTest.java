package com.safecornerscoffee.mocha.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AddressTest {

    @Test
    public void createAddress() {
        Address address = new Address("address1", "address2", "city", "state", "06332");
    }

    @Test
    public void createAddressUsingBuilder() {
        Address address = new Address("address1", "address2", "city", "state", "06332");

        Address other = Address.builder()
                .address1("address1")
                .address2("address2")
                .city("city")
                .state("state")
                .postalCode("06332")
                .build();

        assertThat(address).isEqualTo(other);
    }
}