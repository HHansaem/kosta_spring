package com.kosta.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/cartList")
	public ModelAndView cartList(String userid) {
		ModelAndView mav = new ModelAndView();
		try {
			List<Cart> CartList = cartService.cartList(userid);
			mav.addObject("goods", CartList);
			mav.setViewName("cartList");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "장바구니 조회");
			mav.addObject("message", "장바구니 조회 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}
}
