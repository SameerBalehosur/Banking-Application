package com.te.accountapp.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.te.accountapp.model.Account;
import com.te.accountapp.model.Account.accountStatus;
import com.te.accountapp.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepo repo;

	@Override
	public Account addAccount(Account account) {
		return repo.save(account);

	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public String updateAccount(Long accountId,Account account) {
		Account accounts = repo.findByAccountId(accountId);
		accounts.setAddress(account.getAddress());
		repo.save(accounts);
		return "Data updated ok";
	}

	@Override
	public String deleteAccount(Long accountId) {
		Account findByAccountId = repo.findByAccountId(accountId);
		findByAccountId.setAccountStatus(accountStatus.INACTIVE);
		repo.save(findByAccountId);
		return "Account Deleted Successfully";
	}

	@Override
	public String withdrawAmount(Long accountId,double amount) {
		Account currentAmount = repo.findByAccountId(accountId);
		double currentAmount2 = currentAmount.getCurrentAmount();
		if (amount < currentAmount2) {
			currentAmount2 = currentAmount2 - amount;
			currentAmount.setCurrentAmount(currentAmount2);
			repo.save(currentAmount);
			return "Withdraw Success";
		}
		return null;
	}

	@Override
	public String addAmount(Long accountId ,double amount) {
		Account account2= repo.findByAccountId(accountId);
		double currentAmount2 = account2.getCurrentAmount();
		currentAmount2=currentAmount2+amount;
		account2.setCurrentAmount(currentAmount2);
		repo.save(account2);
		return "Added";

	}

	  @Override 
	  public List<Account> findAllAccounts() { 
		  List<Account> findAll =repo.findAll(); 
		  return findAll;
		  }
	 

}
