package com.kosta.bank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.bank.dto.Member;
import com.kosta.bank.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@ResponseBody  //return해서 주는게 view가 아니라 데이터 (비동기통신에 사용)
	@RequestMapping(value = "/memberDoubleId", method = RequestMethod.POST)
	public String memberDoubleIdCheck(String id) {
		try {
			Boolean check = memberService.checkMemberDoubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Member member, Model model) {
		try {
			memberService.join(member);
			model.addAttribute("member", member);
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String id, String password, Model model) {
		try {
			Member member = memberService.login(id, password);
			member.setPassword("");
			session.setAttribute("user", member.getId());
			model.addAttribute("member", member);
			return "makeAccount";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		session.removeAttribute("user");
		return "makeAccount";
	}
}
