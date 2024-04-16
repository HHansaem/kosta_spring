package com.kosta.bank.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.dto.Member;

public class MemberDaoImpl implements MemberDao {
	
	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertMember(Member member) throws Exception {
		sqlSession.insert("mapper.member.insertMember", member);
	}

	@Override
	public Member selectMember(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMember", id);
	}
}
