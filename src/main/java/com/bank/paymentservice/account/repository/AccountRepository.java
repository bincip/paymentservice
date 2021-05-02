package com.bank.paymentservice.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.paymentservice.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(String accountNumber);

	Optional<Account> findByIban(String iban);

}
