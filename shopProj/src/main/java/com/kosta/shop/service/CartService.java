package com.kosta.shop.service;

import java.util.List;
import java.util.Map;

import com.kosta.shop.dto.Cart;

public interface CartService {
	void cartAdd(Cart cart) throws Exception;
	List<Cart> cartList(String userid) throws Exception;
	Cart cartRetrive(Integer num) throws Exception;
	List<Cart> orderAllConfirm(List<Integer> cartNum) throws Exception;
	void cartUpdate(Map<String, Integer> param) throws Exception;
//	void removeCart(Integer num) throws Exception;
}
