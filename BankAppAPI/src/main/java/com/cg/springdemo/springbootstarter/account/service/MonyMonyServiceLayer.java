package com.cg.springdemo.springbootstarter.account.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bank.framework.account.pojo.BankAccount;
import com.cg.bank.framework.account.pojo.CurrentAccount;
import com.cg.bank.framework.account.pojo.SavingsAccount;
import com.cg.springdemo.springbootstarter.account.dao.MoneyMoneyBankCollection;

@Service
public class MonyMonyServiceLayer {

	@Autowired
	MoneyMoneyBankCollection dataLayer;
		
	
	public BankAccount createNewCurrentAccount(CurrentAccount createNewCurrentAccount) {
		return dataLayer.addBankAccount(createNewCurrentAccount);
		
	}

	public BankAccount createNewSavingsAccount(SavingsAccount createNewSavingsAccount) {
		return dataLayer.addBankAccount(createNewSavingsAccount);
	}

	public double depositAmount(int accountToBeDepsitedIn, double amount) {
		return dataLayer.depositAmount(accountToBeDepsitedIn, amount);
	}

	
	public Collection<BankAccount> getAllAccounts() {
		return dataLayer.getAllAccounts();
	}
	
	public boolean validateUser(int accountNumber) {
		
		return dataLayer.validateUser(accountNumber);
	}

	public BankAccount getAccountByAccountNumber(int accountNumberToBeSearched){
		return dataLayer.getAccountByAccountNumber(accountNumberToBeSearched);
	}

	public double performFundTransfer(int receipientAccountNumber, int donerAccountNumber,
			double amountToBeTransffered) {
		return dataLayer.performFundTransfer(receipientAccountNumber, donerAccountNumber, amountToBeTransffered);
	}

	public Map<Integer,Integer> withdrawWithDenomination(int accountNumber, double amountToBeWithdrawn) {
		return dataLayer.withdrawWithDenomination(accountNumber,amountToBeWithdrawn);
	}

	public void updateCustomer(int accountNumber, Map<String, Object> updatedMap) {
		BankAccount b = dataLayer.getAccountByAccountNumber(accountNumber);
		b.getAccountHolder().setDateOfBirth((LocalDate)updatedMap.get("dateOfBirth"));
		b.getAccountHolder().setContactNumber(Long.parseLong(updatedMap.get("contactNumber").toString()));
		b.getAccountHolder().setCustomerName((String) updatedMap.get("customerName"));
		b.getAccountHolder().setEmailId((String) updatedMap.get("emailId"));
	}
	
}
