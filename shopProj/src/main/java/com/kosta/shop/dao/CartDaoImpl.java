package com.kosta.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.shop.dto.Cart;

@Repository
public class CartDaoImpl implements CartDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertCart(Cart cart) throws Exception {
		sqlSession.insert("mapper.cart.insertCart", cart);
	}
	
	@Override
	public List<Cart> selectCartList(String userid) throws Exception {
		return sqlSession.selectList("mapper.cart.selectCartList", userid);
	}

	@Override
	public Cart selectCart(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.cart.selectCart", num);
	}

	@Override
	public List<Cart> selectCheckedCart(List<Integer> list) throws Exception {
		return sqlSession.selectList("mapper.cart.selectCheckedCart", list);
	}

	@Override
	public void updateCartAmount(Map<String, Integer> param) throws Exception {
		sqlSession.update("mapper.cart.updateCartAmount", param);
	}

	@Override
	public void deleteCart(Integer num) throws Exception {
		sqlSession.delete("mapper.cart.deleteCart", num);
	}

}
