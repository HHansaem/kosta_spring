package com.kosta.shop.dao;

import java.util.List;

import com.kosta.shop.dto.Cart;

public interface CartDao {
	void insertCart(Cart cart) throws Exception;
	List<Cart> selectCartList(String userid) throws Exception;
	Cart selectCart(Integer num) throws Exception;
}
