/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.AccountEntity;
import com.ivt.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity
            findAccountByEmailAndPassword(String email, String password) {
        return accountRepository.findAccountByEmailAndPassword(email, password);
    }

    public void registerAccount(AccountEntity account) {
        accountRepository.save(account);
    }

    public AccountEntity findAccountById(int id) {
        return accountRepository.findOne(id);
    }

    public AccountEntity findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public boolean isAvailable(String email) {
        if (accountRepository.findByEmail2(email).size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
