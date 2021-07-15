package com.safecornerscoffee.integration.mocha.service;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.mapper.AccountMapper;
import com.safecornerscoffee.mocha.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class AccountServiceIntegrationTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @Test
    public void shouldGetAccountById() {
        //given
        Account account = createAccount("mocha");
        accountMapper.insertAccount(account);
        //when
        Account foundAccount = accountService.getAccountById(account.getId());

        //then
        assertThat(foundAccount).isEqualTo(account);
    }

    @Test
    public void shouldGetAccountByEmail() {
        //given
        Account account = createAccount("mocha");
        accountMapper.insertAccount(account);

        //when
        Account foundAccount = accountService.getAccountByEmail(account.getEmail());

        //then
        assertThat(foundAccount).isEqualTo(account);
    }

    @Test
    public void shouldGetAccounts() {
        //given
        List<Account> accounts = Arrays.asList(
                createAccount("mocha"),
                createAccount("cappuccino")
        );
        accounts.forEach(account -> accountMapper.insertAccount(account));

        //when
        List<Account> foundAccounts = accountService.getAccounts();

        //then
        assertThat(accounts).hasSize(accounts.size());
        assertThat(accounts).containsAll(accounts);
    }

    @Test
    public void shouldCreateAccount() {
        //given
        Account account = createAccount("cappuccino");

        //when
        Long accountId = accountService.createAccount(account);

        //then
        Account createdUser = accountMapper.getAccountById(accountId);

        assertThat(createdUser).isEqualTo(account);
    }

    @Test
    public void shouldUpdateAccount() {
        //given
        Account account = createAccount("cappuccino");
        accountMapper.insertAccount(account);

        //when
        String email = "wet-cappuccino@safecornerscoffee.com";
        account.setEmail(email);
        accountService.updateAccount(account);

        //then
        Account updatedAccount = accountMapper.getAccountById(account.getId());
        assertThat(updatedAccount.getEmail()).isEqualTo(email);
    }

    @Test
    public void shouldDeleteAccount() {
        //given
        Account account = createAccount("cappuccino");
        accountMapper.insertAccount(account);

        //when
        accountService.deleteAccount(account.getId());

        //then
        Account deletedAccount = accountMapper.getAccountById(account.getId());
        assertThat(deletedAccount).isNull();
    }


    private Account createAccount(String name) {
        return Account.builder()
                .email(name + "@safecornerscoffee.com")
                .password(name)
                .firstName(name)
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