package com.kosta.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired  //이미 생성된 게 AccountService가 있으면 자동으로 생성해라
	private AccountService accountService;
	
	@RequestMapping(value = "/makeAccount", method = RequestMethod.GET)
	public String makeAccount() {
		return "makeAccount";
	}

	@RequestMapping(value = "/makeAccount", method = RequestMethod.POST)
	public String makeAccount(Account acc) {
		try {
			accountService.makeAccount(acc);
			return "accountInfo";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
