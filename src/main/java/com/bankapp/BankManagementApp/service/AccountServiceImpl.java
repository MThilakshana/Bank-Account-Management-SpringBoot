package com.bankapp.BankManagementApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.BankManagementApp.entity.Account;
import com.bankapp.BankManagementApp.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repo;

    @Override
    public Account createAccount(Account account) {
        Account account_saved = repo.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account is not available");
        }
        Account account_found = account.get();
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = repo.findAll();
        return listOfAccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {

        // check account availble or not
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account is not available");
        }
        Account accountPresent = account.get();

        // plus value in account
        Double totalBalance = accountPresent.getAccount_balance() + amount;
        accountPresent.setAccount_balance(totalBalance);

        // save in database
        repo.save(accountPresent);

        return accountPresent;
    }

    @Override
    public Account withdrawAmount(Long accountNumber, Double amount) {

        // check account availble or not
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account is not available");
        }
        Account accountPresent = account.get();

        Double totalBalance = accountPresent.getAccount_balance() - amount;
        accountPresent.setAccount_balance(totalBalance);
        repo.save(accountPresent);

        return accountPresent;
    }

    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber); // call to check account
        repo.deleteById(accountNumber);
    }

}
