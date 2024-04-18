package com.kosta.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.CartDao;
import com.kosta.shop.dto.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	@Override
	public List<Cart> cartList(String userid) throws Exception {
		return cartDao.selectCartList(userid);
	}

}
