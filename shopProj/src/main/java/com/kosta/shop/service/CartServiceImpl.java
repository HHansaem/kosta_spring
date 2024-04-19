package com.kosta.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.CartDao;
import com.kosta.shop.dto.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	@Override
	public void cartAdd(Cart cart) throws Exception {
		cartDao.insertCart(cart);
	}

	@Override
	public List<Cart> cartList(String userid) throws Exception {
		return cartDao.selectCartList(userid);
	}

	@Override
	public Cart cartRetrive(Integer num) throws Exception {
		return cartDao.selectCart(num);
	}

	@Override
	public List<Cart> orderAllConfirm(List<Integer> cartNum) throws Exception {
		return cartDao.selectCheckedCart(cartNum);
	}

	@Override
	public void cartUpdate(Map<String, Integer> param) throws Exception {
		cartDao.updateCartAmount(param);;
	}

	@Override
	public void cartDelete(Integer num) throws Exception {
		cartDao.deleteCart(num);
	}

	@Override
	public void cartDeleteAll(List<Integer> nums) throws Exception {
		for(Integer num : nums) {
			cartDao.deleteCart(num);
		}
	}


}
