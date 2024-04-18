package com.kosta.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.BFile;
import com.kosta.board.dto.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Board> selectBoardList(Integer row) throws Exception {
		//쿼리문의 결과가 여러개(list)일 때는 selectList
		return sqlSession.selectList("mapper.board.selectBoardList", row);
	}

	@Override
	public Integer selectBoardCount() throws Exception {
		//쿼리문의 결과가 하나일 때는 selectOne
		return sqlSession.selectOne("mapper.board.selectBoardCount");
	}

	@Override
	public Board selectBoard(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoard", num);
	}
	
	 @Override
	 public void insertBoard(Board board) throws Exception {
	    sqlSession.insert("mapper.board.insertBoard", board);
	 }
	
	 @Override
	 public void insertFile(BFile file) throws Exception {
	    sqlSession.insert("mapper.board.insertFile", file);
	 }

	@Override
	public void updateBoard(Board board) throws Exception {
		sqlSession.update("mapper.board.updateBoard", board);
	}

	@Override
	public void updateBrdViewCnt(Integer num) throws Exception {
		sqlSession.update("mapper.board.updateBrdViewCnt", num);
	}

	@Override
	public void deleteFile(Integer num) throws Exception {
		sqlSession.delete("mapper.board.deleteFile", num);
	}

	@Override
	public List<Board> selectBoardSearchList(Integer row, String type, String word) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("row", row);
		param.put("type", type);
		param.put("word", word);
		return sqlSession.selectList("mapper.board.selectBoardSearchList",param);
	}

	@Override
	public Integer selectBoardSearchCount(String type, String word) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("word", word);
		return sqlSession.selectOne("mapper.board.selectBoardSearchCount",param);
	}
}
