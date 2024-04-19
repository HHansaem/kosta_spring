package com.kosta.shop.dao;

import java.util.List;
import java.util.Map;

import com.kosta.shop.dto.Cart;

public interface CartDao {
	void insertCart(Cart cart) throws Exception;
	List<Cart> selectCartList(String userid) throws Exception;
	Cart selectCart(Integer num) throws Exception;
	List<Cart> selectCheckedCart(List<Integer> list) throws Exception;
	void updateCartAmount(Map<String, Integer> param) throws Exception;
	void deleteCart(Integer num) throws Exception;
}
