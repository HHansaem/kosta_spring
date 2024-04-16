package com.kosta.bank.dao;

import com.kosta.bank.dto.Member;

public interface MemberDao {
	void insertMember(Member member) throws Exception;
	Member selectMember(String id) throws Exception;
}
