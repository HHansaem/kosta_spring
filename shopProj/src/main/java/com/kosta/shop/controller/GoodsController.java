package com.kosta.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.Goods;
import com.kosta.shop.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	@GetMapping("/goodsRetrieve")
	public ModelAndView goodsRetrieve(String gCode) {
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
}
