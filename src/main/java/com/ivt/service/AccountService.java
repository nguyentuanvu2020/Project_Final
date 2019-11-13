/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.CustomerEntity;
import com.ivt.repositories.AccountRepository;
import com.ivt.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MailService mailService;

    public AccountEntity
            findAccountByEmailAndPassword(String email, String password) {
        return accountRepository.findAccountByEmailAndPassword(email, password);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean registerAccount(AccountEntity account) {
        AccountEntity accountData = accountRepository.findByEmail(account.getEmail());
        if (accountData != null && accountData.getId() > 0) {
            return false;
        }
        AccountEntity newAccount = accountRepository.save(account);
        CustomerEntity customer = customerRepository.findByEmail(newAccount.getEmail());
        if (customer != null) {
            customer.setAccount(newAccount);
        }
        try {
            mailService.sendNewAccountMailPage(newAccount);
        } catch (Exception e) {
        }
        return true;
    }

    public void updateAccount(AccountEntity account) {
        AccountEntity updateAccount = accountRepository.findOne(account.getId());
        updateAccount.setName(account.getName());
        updateAccount.setBirthDate(account.getBirthDate());
        updateAccount.setGender(account.getGender());
        updateAccount.setAddress(account.getAddress());
        accountRepository.save(updateAccount);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean changePassword(int id, String oldPassword, String newPassword) {
        AccountEntity updateAccount = accountRepository.findOne(id);
        if (passwordEncoder.matches(oldPassword, updateAccount.getPassword()) == true) {
            updateAccount.setPassword(passwordEncoder.encode(newPassword));
            accountRepository.save(updateAccount);
            return true;
        } else {
            return false;
        }
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
