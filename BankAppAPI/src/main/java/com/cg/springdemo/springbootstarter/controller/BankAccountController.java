package com.cg.springdemo.springbootstarter.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bank.framework.account.pojo.BankAccount;
import com.cg.springdemo.springbootstarter.account.service.MonyMonyServiceLayer;
import com.cg.springdemo.springbootstarter.exceptions.AccountNotFoundException;
import com.cg.springdemo.springbootstarter.exceptions.NoAccountFoundException;
import com.cg.springdemo.springbootstarter.factory.MMBankFactory;

@RestController
public class BankAccountController {

	@Autowired
	private MonyMonyServiceLayer moneyMoneyServiceLayer;
	@Autowired
	private MMBankFactory mMBankFactory;

	private Map<String, Object> accountDetails = new HashMap<String, Object>();

	@RequestMapping("/add")
	public String addInitialAccount() {

		accountDetails.put("accountHolderName", "Amit");

		String dateOfBirth = "1995-12-15";
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dateOfBirth, formater);
		accountDetails.put("dateOfBirth", date);

		accountDetails.put("contactNumber", "9430038575");

		accountDetails.put("nationality", "Indian");

		accountDetails.put("gender", "Male");

		accountDetails.put("emailID", "kumaramit21031995@gmail.com");

		accountDetails.put("houseNo", "13");

		accountDetails.put("street", "25");

		accountDetails.put("city", "Saharsha");

		accountDetails.put("state", "Bihar");

		accountDetails.put("pinCode", "852127");

		accountDetails.put("accountType", "savingAccount");

		accountDetails.put("accountBalance", "20500");
		accountDetails.put("salary", true);
		moneyMoneyServiceLayer.createNewSavingsAccount(mMBankFactory.createNewSavingsAccount(accountDetails));

		return "Added Successfully";
	}

	@RequestMapping("/accounts")
	public Collection<BankAccount> getAllBankAccount() throws NoAccountFoundException {
		if(moneyMoneyServiceLayer.getAllAccounts().size()==0) {
			throw new NoAccountFoundException("The Account List is Empty");
		}else {
			return moneyMoneyServiceLayer.getAllAccounts();
		}
	}

	@RequestMapping("/accounts/{accountNumberToBeSearched}")
	public BankAccount getAccount(@PathVariable int accountNumberToBeSearched) throws AccountNotFoundException {
		 BankAccount bankAccount =  moneyMoneyServiceLayer.getAccountByAccountNumber(accountNumberToBeSearched);
		 if(bankAccount==null) {
			 throw new AccountNotFoundException("The Account Requested Was Not Found ");
		 }else {
			 return bankAccount;
		 }
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String createAccount(@RequestBody Map<String, Object> map) {
		if (map.get("accountType").toString().equals("savingAccount")) {
			moneyMoneyServiceLayer.createNewSavingsAccount(mMBankFactory.createNewSavingsAccount(map));
			return "Created Successfully";
		} else {
			moneyMoneyServiceLayer.createNewCurrentAccount(mMBankFactory.createNewCurrentAccount(map));
			return "Created Successfully";
		}
	}

	@RequestMapping("/update/{accountDetailsToBeUpdated}")
	public Map<String, Object> takeUpdateRequest(@PathVariable int accountDetailsToBeUpdated) {
		Map<String, Object> accountToBeUpdated = new HashMap<String, Object>();

		accountToBeUpdated.put("accountNumber",
				moneyMoneyServiceLayer.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountNumber());

		accountToBeUpdated.put("customerID", moneyMoneyServiceLayer.getAccountByAccountNumber(accountDetailsToBeUpdated)
				.getAccountHolder().getCustomerId());

		accountToBeUpdated.put("accountHolderName", moneyMoneyServiceLayer
				.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountHolder().getCustomerName());

		accountToBeUpdated.put("contactNumber", moneyMoneyServiceLayer
				.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountHolder().getContactNumber());

		accountToBeUpdated.put("dateOfBirth", moneyMoneyServiceLayer
				.getAccountByAccountNumber(accountDetailsToBeUpdated).getAccountHolder().getDateOfBirth());

		accountToBeUpdated.put("emailID", moneyMoneyServiceLayer.getAccountByAccountNumber(accountDetailsToBeUpdated)
				.getAccountHolder().getEmailId());

		return accountToBeUpdated;

	}

	@PutMapping("/update")
	public void writeUpdate(@RequestBody Map<String, Object> updatedMap) {
		
		moneyMoneyServiceLayer.updateCustomer(Integer.parseInt(updatedMap.get("accountNumber").toString()), updatedMap);
	}
}
