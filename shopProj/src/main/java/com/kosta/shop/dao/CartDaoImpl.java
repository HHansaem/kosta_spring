package com.kosta.shop.dao;

import java.util.List;

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

}
