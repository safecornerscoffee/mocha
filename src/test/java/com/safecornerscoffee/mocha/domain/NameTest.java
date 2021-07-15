package com.safecornerscoffee.mocha.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class NameTest {

    @Test
    public void createName() {
        Name name = new Name("Joshua", "Redman");
    }
}