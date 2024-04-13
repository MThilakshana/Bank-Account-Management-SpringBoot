package com.bankapp.BankManagementApp.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.BankManagementApp.entity.Account;
import com.bankapp.BankManagementApp.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    // create the account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createAccount = service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    // get account details by acc number
    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable Long accountNumber) {
        Account account = service.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }

    // get all accounts
    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails() {
        List<Account> allAccountDetails = service.getAllAccountDetails();
        return allAccountDetails;
    }

    // deposit the money
    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAccount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        Account account = service.depositAmount(accountNumber, amount);
        return account;
    }

    // withdraw money
    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdrawAmount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        Account account = service.withdrawAmount(accountNumber, amount);
        return account;
    }

    // close the account
    @DeleteMapping("/deleteaccount/{accountNumber}")
    public ResponseEntity<String> deleteaccount(@PathVariable Long accountNumber) {
        service.closeAccount(accountNumber);
        return ResponseEntity.ok("Account Closed");
    }

}
