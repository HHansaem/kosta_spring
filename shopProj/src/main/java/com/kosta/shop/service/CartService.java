package com.kosta.shop.service;

import java.util.List;
import java.util.Map;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Order;
import com.kosta.shop.dto.OrderInfo;

public interface CartService {
	void cartAdd(Cart cart) throws Exception;
	List<Cart> cartList(String userid) throws Exception;
	Cart cartRetrive(Integer num) throws Exception;
	List<Cart> orderAllConfirm(List<Integer> cartNum, List<Integer> cartAmt) throws Exception;
	void cartUpdate(Map<String, Integer> param) throws Exception;
	void cartDelete(Integer num) throws Exception;
	void cartDeleteAll(List<Integer> nums) throws Exception;
	void orderDone(OrderInfo orderinfo, Order order, Integer cartNum) throws Exception;
	void orderAllDone(OrderInfo order, List<Integer> nums) throws Exception;
	List<Order> orderList(Integer orderinfo_num) throws Exception;
}
