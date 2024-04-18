package com.kosta.shop.dao;

import com.kosta.shop.dto.Member;

public interface MemberDao {
	void insertMember(Member member) throws Exception;
	Integer idCheck(String userid) throws Exception;
	Member selectMember(String userid) throws Exception;
}
