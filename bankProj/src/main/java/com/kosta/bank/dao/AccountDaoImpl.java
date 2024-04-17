package com.kosta.bank.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bank.dto.Account;

@Repository  //@Controller처럼 자동생성 (dao에서 사용, servlet-context.xml에 안 써줘도 됨)
public class AccountDaoImpl implements AccountDao {

	@Autowired  //(setter 없어도 됨)
	private SqlSessionTemplate sqlSession;
	
	//setter를 통한 주입
//	public void setSqlSession(SqlSessionTemplate sqlSession) {
//		this.sqlSession = sqlSession;
//	}

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
