package com.kosta.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.shop.dto.Goods;

@Repository
public class GoodsDaoImpl implements GoodsDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Goods> selectGoodsList() throws Exception {
		return sqlSession.selectList("mapper.goods.selectGoodsList");
	}

}
