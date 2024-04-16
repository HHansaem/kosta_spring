package com.kosta.bank.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.dto.Account;

public class AccountDaoImpl implements AccountDao {

	private SqlSessionTemplate sqlSession;
	
	//setter를 통한 주입
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount", acc);
	}

	@Override
	public Account selectAccount(String id) throws Exception {
		return sqlSession.selectOne("mapper.account.selectAccount", id);
	}

	@Override
	public void updateAccountBalance(String id, Integer balance) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("balance", balance);
		sqlSession.update("mapper.account.updateAccountBalance", param);
	}

	@Override
	public List<Account> selectAccountList() throws Exception {
		return sqlSession.selectList("mapper.account.selectAccountList");
	}

}
