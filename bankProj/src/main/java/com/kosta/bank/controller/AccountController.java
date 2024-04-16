package com.kosta.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@ResponseBody  //return해서 주는게 view가 아니라 데이터 (비동기통신에 사용)
	@RequestMapping(value = "/accountDoubleId", method = RequestMethod.POST)
	public String accountDoubleIdCheck(String id) {
		try {
			Boolean check = accountService.checkAccountDoubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@RequestMapping(value = "/makeAccount", method = RequestMethod.POST)
	public String makeAccount(Account acc, Model model) {
		try {
			accountService.makeAccount(acc);
			model.addAttribute("acc", acc);
			return "accountInfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public String accountInfo() {
		return "accountInfoForm";
	}

	@RequestMapping(value = "/accountInfo", method = RequestMethod.POST)
	public ModelAndView accountInfo(@RequestParam("id") String id) {
		//파라미터랑 이름이 다르면 @RequestParam로 name으로 받아온 이름을 써줌
		ModelAndView mav = new ModelAndView();
		try {
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public String deposit() {
		return "deposit";
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public ModelAndView deposit(String id, Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.deposit(id, money);
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String withdraw() {
		return "withdraw";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdraw(String id, Integer money, Model model) {
		try {
			accountService.withdraw(id, money);
			Account acc = accountService.accountInfo(id);
			model.addAttribute("acc", acc);
			return "accountInfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/allAccountInfo", method = RequestMethod.GET)
	public String allAccountInfo(Model model) {
		try {
			List<Account> accs = accountService.allAccountInfo();
			model.addAttribute("accs", accs);
			return "allAccountInfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String transfer() {
		return "transfer";
	}
	
	@Transactional
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ModelAndView transfer(String sid, String rid, Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.transfer(sid, rid, money);
			Account acc = accountService.accountInfo(sid);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
}
