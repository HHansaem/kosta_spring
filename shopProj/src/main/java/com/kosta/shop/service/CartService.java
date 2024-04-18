package com.kosta.shop.service;

import java.util.List;

import com.kosta.shop.dto.Cart;

public interface CartService {
	List<Cart> cartList(String userid) throws Exception;
}
