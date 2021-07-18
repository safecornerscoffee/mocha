package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.Address;
import com.safecornerscoffee.mocha.domain.Name;
import com.safecornerscoffee.mocha.dto.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public void register(RegistrationForm form) {

        Account account = Account.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(new Name(form.getFirstName(), form.getLastName()))
                .address(new Address("address1", "address2", "city", "state", "06332"))
                .phoneNumber("010-3333-3333")
                .build();

        accountService.createAccount(account);
    }
}
