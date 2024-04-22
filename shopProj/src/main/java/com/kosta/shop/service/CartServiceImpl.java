package com.kosta.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.CartDao;
import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Order;
import com.kosta.shop.dto.OrderInfo;

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
	public List<Cart> orderAllConfirm(List<Integer> cartNum, List<Integer> cartAmt) throws Exception {
		Map<String, Integer> param = new HashMap<>();
		//주문 수량으로 바꿔서 세팅
		for(int i=0; i<cartNum.size(); i++) {
			param.clear();
			param.put("num", cartNum.get(i));
			param.put("gAmount", cartAmt.get(i));
			cartDao.updateCartAmount(param);
		}
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

	@Override
	public void orderDone(OrderInfo orderInfo, Order order, Integer cartNum) throws Exception {
		cartDao.insertOrderInfo(orderInfo);
		order.setOrderinfo_num(orderInfo.getNum());
		cartDao.insertOrder(order);
		if(cartNum != null) cartDao.deleteCart(cartNum);
	}

	@Override
	public void orderAllDone(OrderInfo orderInfo, List<Integer> nums) throws Exception {
		cartDao.insertOrderInfo(orderInfo);
		for(Integer num : nums) {
			Cart cart = cartDao.selectCart(num);
			Order order = new Order();
			order.setUserid(cart.getUserid());
			order.setgCode(cart.getgCode());
			order.setgName(cart.getgName());
			order.setgPrice(cart.getgPrice());
			order.setgSize(cart.getgSize());
			order.setgColor(cart.getgColor());
			order.setgAmount(cart.getgAmount());
			order.setgImage(cart.getgImage());
			order.setOrderinfo_num(orderInfo.getNum());
			cartDao.insertOrder(order);
			cartDao.deleteCart(num);
		}
	}

	@Override
	public List<Order> orderList(Integer orderinfo_num) throws Exception {
		return cartDao.selectOrderList(orderinfo_num);
	}


}
