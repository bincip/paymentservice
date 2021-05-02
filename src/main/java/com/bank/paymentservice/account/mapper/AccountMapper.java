package com.bank.paymentservice.account.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bank.paymentservice.account.dto.AccountDto;
import com.bank.paymentservice.account.dto.CustomerDto;
import com.bank.paymentservice.account.entity.Account;
import com.bank.paymentservice.account.entity.Customer;

@Service
public class AccountMapper {

	public AccountDto accountToAccountDto(Account account) {
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(account, accountDto);
		
		if(account.getCustomer()!= null) {
			CustomerDto customer = new CustomerDto();
			customer.setCif(account.getCustomer().getCif());
			customer.setStatus(account.getCustomer().getStatus());
			customer.setFirstName(account.getCustomer().getFirstName());
			customer.setLastName(account.getCustomer().getLastName());

			accountDto.setCustomer(customer);
		}

		return accountDto;
	}

	public Account accountDtoToAccount(AccountDto accountDto) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDto, account);
		if(account.getCustomer()!= null) {
			Customer customer = new Customer();
			customer.setId(account.getCustomer().getId());
			customer.setCif(account.getCustomer().getCif());
			customer.setStatus(account.getCustomer().getStatus());
			customer.setFirstName(account.getCustomer().getFirstName());
			customer.setLastName(account.getCustomer().getLastName());
			account.setCustomer(customer);
		}

		return account;
	}

}
