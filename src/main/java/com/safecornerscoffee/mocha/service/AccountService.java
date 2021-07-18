package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.Authority;
import com.safecornerscoffee.mocha.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;

    public Account getAccountById(Long id) {
        return accountMapper.getAccountById(id);
    }

    public Account getAccountByEmail(String email) {
        return accountMapper.getAccountByEmail(email);
    }

    public List<Account> getAccounts() {
        return accountMapper.getAccounts();
    }

    @Transactional
    public Long createAccount(Account account) {
        accountMapper.insertAccount(account);
        for (Authority authority : account.getAuthorities()) {
            accountMapper.insertAuthority(authority);
        }
        return account.getId();
    }

    @Transactional
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }

    @Transactional
    public void deleteAccount(Long id) {
        accountMapper.deleteAccount(id);
    }
}