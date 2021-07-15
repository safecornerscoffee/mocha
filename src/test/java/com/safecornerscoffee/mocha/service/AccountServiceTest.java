package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.Address;
import com.safecornerscoffee.mocha.domain.Name;
import com.safecornerscoffee.integration.mocha.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountMapper accountMapper;

    @Test
    public void shouldGetAccountById() {
        //given
        Long accountId = 1L;
        Account storedAccount = storeAccount(accountId, "mocha");
        given(accountMapper.getAccountById(anyLong())).willReturn(storedAccount);

        //when
        Account account = accountService.getAccountById(accountId);

        //then
        then(accountMapper).should(atLeastOnce()).getAccountById(anyLong());
        assertThat(account.getId()).isEqualTo(accountId);
    }

    @Test
    public void shouldGetAccountByEmail() {
        //given
        Account storedAccount = storeAccount(1L, "mocha");
        String email = storedAccount.getEmail();
        given(accountMapper.getAccountByEmail(email)).willReturn(storedAccount);

        //when
        Account account = accountService.getAccountByEmail(email);

        //then
        then(accountMapper).should().getAccountByEmail(email);
        assertThat(account.getEmail()).isEqualTo(email);
    }

    @Test
    public void shouldGetAccounts() {
        //given
        List<Account> storedAccounts = Arrays.asList(
                storeAccount(1L, "mocha"),
                storeAccount(2L, "cappuccino")
        );
        given(accountMapper.getAccounts()).willReturn(storedAccounts);

        //when
        List<Account> accounts = accountService.getAccounts();

        //then
        assertThat(accounts).hasSize(2);
        assertThat(accounts).containsAll(storedAccounts);

    }

    @Test
    public void shouldCreateAccount() {
        //given
        Account account = createAccount("mocha");
        Long generatedId = 1L;

        will(invocationOnMock -> {
            Account insertUser = invocationOnMock.getArgument(0, Account.class);
            insertUser.setId(generatedId);
            return null;
        }).given(accountMapper).insertAccount(any(Account.class));

        //when
        Long accountId = accountService.createAccount(account);

        //then
        then(accountMapper).should(atMostOnce()).insertAccount(any(Account.class));
        assertThat(accountId).isEqualTo(generatedId);
    }

    @Test
    public void shouldUpdateAccount() {
        //given
        Account account = createAccount("mocha");

        //when
        accountService.updateAccount(account);

        //then
        then(accountMapper).should().updateAccount(account);
    }

    @Test
    public void shouldDeleteAccount() {
        //given
        Account account = storeAccount(1L, "mocha");

        //when
        accountService.deleteAccount(account.getId());

        //then
        then(accountMapper).should().deleteAccount(account.getId());
    }

    private Account createAccount(String name) {
        return Account.builder()
                .email(name + "@safecornerscoffee.com")
                .password(name)
                .name(new Name(name, "coffee"))
                .address(new Address("address1", "address2", "city", "state", "06332"))
                .phoneNumber("010-4442-4442")
                .build();
    }

    private Account storeAccount(Long id, String name) {
        return Account.builder()
                .id(id)
                .email(name + "@safecornerscoffee.com")
                .password(name)
                .name(new Name(name, "coffee"))
                .address(new Address("address1", "address2", "city", "state", "06332"))
                .phoneNumber("010-4442-4442")
                .build();
    }
}