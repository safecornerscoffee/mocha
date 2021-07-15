package com.safecornerscoffee.integration.mocha.mapper;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.Address;
import com.safecornerscoffee.mocha.domain.Name;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class AccountMapperTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    public void getAccountById() {
        //when
        Account account = createAccount("mocha");
        accountMapper.insertAccount(account);

        // given
        Account other = accountMapper.getAccountById(account.getId());

        //then
        assertThat(other.getId()).isEqualTo(account.getId());
        assertThat(other.getEmail()).isEqualTo(account.getEmail());
        assertThat(other.getPassword()).isEqualTo(account.getPassword());
        assertThat(other.getAddress()).isEqualTo(account.getAddress());
        assertThat(other.getPhoneNumber()).isEqualTo(account.getPhoneNumber());
    }

    @Test
    public void getAccountByEmail() {
        //when
        Account account = createAccount("mocha");
        accountMapper.insertAccount(account);

        // given
        Account other = accountMapper.getAccountByEmail(account.getEmail());

        //then
        assertThat(other.getId()).isEqualTo(account.getId());
        assertThat(other.getEmail()).isEqualTo(account.getEmail());
        assertThat(other.getPassword()).isEqualTo(account.getPassword());
        assertThat(other.getAddress()).isEqualTo(account.getAddress());
        assertThat(other.getPhoneNumber()).isEqualTo(account.getPhoneNumber());
    }

    @Test
    public void getAccounts() {
        //given
        Account account = createAccount("mocha");
        Account another = createAccount("cappuccino");

        accountMapper.insertAccount(account);
        accountMapper.insertAccount(another);

        //when
        List<Account> accounts = accountMapper.getAccounts();

        //then
        assertThat(accounts).hasSize(2);
        assertThat(accounts).contains(account, another);
        accounts.forEach(a -> assertThat(a).hasNoNullFieldsOrProperties());
    }

    @Test
    public void insertAccount() {
        //given
        Account account = createAccount("mocha");

        //when
        accountMapper.insertAccount(account);

        ///then
        assertThat(account.getId()).isNotNull();
    }

    @Test
    public void insertAccount_DuplicatedKeyException_WhenEmailAlreadyExists() {
        //given
        Account account = createAccount("mocha");

        //when
        AbstractThrowableAssert<?, ? extends Throwable> throwableAssert = assertThatThrownBy(() -> {
            accountMapper.insertAccount(account);
            accountMapper.insertAccount(account);
        });

        //given
        throwableAssert.isInstanceOf(DuplicateKeyException.class)
                        .hasMessageContaining("email");
    }

    @Test
    public void updateAccount() {
        //given
        Account account = createAccount("mocha");
        accountMapper.insertAccount(account);

        //when
        changeAccountDetails(account);
        accountMapper.updateAccount(account);

        //then
        Account other = accountMapper.getAccountById(account.getId());

        assertThat(other.getEmail()).isEqualTo(account.getEmail());
        assertThat(other.getPassword()).isEqualTo(account.getPassword());
        assertThat(other.getAddress()).isEqualTo(account.getAddress());
        assertThat(other.getPhoneNumber()).isEqualTo(account.getPhoneNumber());

    }

    @Test
    public void deleteAccount() {
        //given
        Account account = createAccount("mocha");
        accountMapper.insertAccount(account);

        //when
        accountMapper.deleteAccount(account.getId());

        //then
        Account deletedAccount = accountMapper.getAccountById(account.getId());

        assertThat(deletedAccount).isNull();
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

    private void changeAccountDetails(Account account) {
        account.setEmail(account.getEmail().toUpperCase());
        account.setName(new Name(account.getName().getFirstName().toUpperCase(), account.getName().getFirstName().toLowerCase()));
        account.setAddress(new Address("address1", "address2", "city", "state", "06333"));
        account.setPhoneNumber("010-3332-3332");
    }
}