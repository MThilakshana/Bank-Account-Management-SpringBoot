package com.bankapp.BankManagementApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapp.BankManagementApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
