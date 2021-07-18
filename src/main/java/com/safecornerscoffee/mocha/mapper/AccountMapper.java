package com.safecornerscoffee.mocha.mapper;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {

    Account getAccountById(Long id);
    Account getAccountByEmail(String email);
    List<Account> getAccounts();

    void insertAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Long id);

    void insertAuthority(Authority authority);
}
