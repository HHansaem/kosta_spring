package com.kosta.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
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

	//이렇게도 쓸 수 있음 (item이란 이름으로 goodsRetrieve 뷰로 넘겨줌 (뷰는 GetMapping의 이름)
//	@GetMapping("/goodsRetrieve")
//	@ModelAttribute("item")
//	public Goods goodsRetrieve(@RequestParam("gCode") String gCode) {
//		Goods goods = null;
//		try {
//			goods = goodsService.goodsDetail(gCode);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return goods;
//	}
	
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
}
