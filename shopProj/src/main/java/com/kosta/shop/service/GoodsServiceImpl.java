package com.kosta.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.GoodsDao;
import com.kosta.shop.dto.Goods;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public List<Goods> goodsList() throws Exception {
		return goodsDao.selectGoodsList();
	}

}
