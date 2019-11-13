/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.service;

import com.ivt.entities.AccountEntity;
import com.ivt.repositories.AccountRepository;
import java.util.List;
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

    public AccountEntity
            findAccountByEmailAndPassword(String email, String password) {
        return accountRepository.findAccountByEmailAndPassword(email, password);
    }

    public boolean registerAccount(AccountEntity account) {
        AccountEntity accountData = accountRepository.findByEmail(account.getEmail());
        if (accountData != null && accountData.getId() > 0) {

            return false;
        }
        accountRepository.save(account);
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
    
    public List<AccountEntity> getAll(){
        return (List<AccountEntity>) accountRepository.findAll();
    }
    
    public AccountEntity addNewAccount(AccountEntity newAccount){
        return accountRepository.save(newAccount);
    }
}
