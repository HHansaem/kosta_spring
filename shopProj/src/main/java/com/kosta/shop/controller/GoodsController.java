package com.kosta.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Goods;
import com.kosta.shop.dto.Member;
import com.kosta.shop.service.CartService;
import com.kosta.shop.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/goodsRetrieve")
	public ModelAndView goodsRetrieve(@RequestParam("gCode") String gCode) {
		ModelAndView mav = new ModelAndView();
		try {
			Goods goods = goodsService.goodsDetail(gCode);
			mav.addObject("item", goods);
			mav.setViewName("goodsRetrieve");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품 상세조회");
			mav.addObject("essage", "상품 상세조회 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	
	@GetMapping("/addCart")
	public String addCart(Cart cart) {
		Member member = (Member)session.getAttribute("user");
		cart.setUserid(member.getUserid());
		try {
			cartService.cartAdd(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/goodsRetrieve?gCode="+cart.getgCode();
	}
	
	//이렇게도 쓸 수 있음 (cartList이란 이름으로 cartList 뷰로 넘겨줌 (뷰는 GetMapping의 이름)
	@GetMapping("/cartList")
	@ModelAttribute("cartList")
	public List<Cart> cartList() {
		List<Cart> carts = null;
		try {
			Member member = (Member)session.getAttribute("user");
			carts = cartService.cartList(member.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carts;
	}
	
	@GetMapping("/orderConfirm")
	public ModelAndView orderConfirm(Goods goods, @RequestParam("gSize") String gSize,
			@RequestParam("gColor") String gColor, @RequestParam("gAmount") Integer gAmount) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("gDTO", goods);
		mav.addObject("gSize", gSize);
		mav.addObject("gColor", gColor);
		mav.addObject("gAmount", gAmount);
		mav.setViewName("orderConfirm");
		return mav;
	}

	@GetMapping("/cartOrderConfirm")
	public ModelAndView cartOrderConfirm(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Cart cart = cartService.cartRetrive(num);
			mav.addObject("cDTO", cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("orderConfirm");
		return mav;
	}
	
	@GetMapping("/cartOrderAllConfirm")
	public ModelAndView cartOrderAllConfirm(@RequestParam("check") Integer[] check,
											@RequestParam("cartAmount") Integer[] cartAmount) {
		ModelAndView mav = new ModelAndView();
		try {
			List<Cart> cartList = cartService.orderAllConfirm(Arrays.asList(check));  //Arrays.asList -> 배열을 리스트로 바꿔줌
			//주문 수량으로 바꿔서 세팅
			for(int i=0; i<cartList.size(); i++) {
				cartList.get(i).setgAmount(cartAmount[i]);
			}
			mav.addObject("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("orderAllConfirm");
		return mav;
	}
	
	@ResponseBody
	@GetMapping("/cartUpdate")
	public void cartUpdate(@RequestParam Map<String, Integer> map) {
		try {
			cartService.cartUpdate(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@GetMapping("/cartDelete")
	public void cartDelete(@RequestParam Integer num) {
		try {
			cartService.cartDelete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/CartDelAll")
	public String cartDelAll(@RequestParam("check") Integer[] num) {
		try {
			cartService.cartDeleteAll(Arrays.asList(num));  //Arrays.asList -> 배열을 리스트로 바꿔줌
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cartList";
	}
}
