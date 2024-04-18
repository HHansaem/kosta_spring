package com.kosta.shop.dao;

import java.util.List;

import com.kosta.shop.dto.Cart;

public interface CartDao {
	List<Cart> selectCartList(String userid) throws Exception;
}
