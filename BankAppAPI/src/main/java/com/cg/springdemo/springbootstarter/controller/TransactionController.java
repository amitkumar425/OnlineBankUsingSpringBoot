package com.cg.springdemo.springbootstarter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springdemo.springbootstarter.account.service.MonyMonyServiceLayer;

@RestController
public class TransactionController {

	@Autowired
	private MonyMonyServiceLayer moneyMoneyServiceLayer;

	@RequestMapping("/account/withdraw/{accountNumber}/{amountToBeWithdrawn}")
	public Map<Integer, Integer> doWithdraw(@PathVariable int accountNumber, 
			@PathVariable double amountToBeWithdrawn) {
		return moneyMoneyServiceLayer.withdrawWithDenomination(accountNumber, amountToBeWithdrawn);
	}

	@RequestMapping("/account/deposit/{accountToBeDepsitedIn}/{amount}")
	public double dodeposit(@PathVariable int accountToBeDepsitedIn, @PathVariable double amount) {
		return moneyMoneyServiceLayer.depositAmount(accountToBeDepsitedIn, amount);
	}

	@RequestMapping("/account/fundtransfer/{receipientAccountNumber}/{donerAccountNumber}/{amountToBeTransffered}")
	public String doFundTransfer(@PathVariable int receipientAccountNumber, @PathVariable int donerAccountNumber,
			@PathVariable double amountToBeTransffered) {
		double amount =  moneyMoneyServiceLayer.performFundTransfer(receipientAccountNumber, donerAccountNumber,
				amountToBeTransffered);
		if(amount!=-1)
		return amount+" Transferred from "+donerAccountNumber+" to "+receipientAccountNumber+" Successfully";
		else 
			return "Fund Transfer Failed";
	}

}
