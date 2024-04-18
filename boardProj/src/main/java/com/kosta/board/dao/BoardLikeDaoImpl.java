package com.kosta.board.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardLikeDaoImpl implements BoardLikeDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertBoardLike(String memberId, Integer boardNum) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		param.put("boardNum", boardNum);
		sqlSession.insert("mapper.boardlike.insertBoardLike", param);
	}

	@Override
	public void deleteBoardLike(String memberId, Integer boardNum) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		param.put("boardNum", boardNum);
		sqlSession.delete("mapper.boardlike.deleteBoardLike", param);
	}

	@Override
	public Integer selectBoardLike(String memberId, Integer boardNum) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		param.put("boardNum", boardNum);
		return sqlSession.selectOne("mapper.boardlike.selectBoardLike", param);
	}

}
