package com.kosta.bank.service;

import javax.servlet.http.HttpSession;

import com.kosta.bank.dao.MemberDao;
import com.kosta.bank.dto.Member;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public void join(Member member) throws Exception {
		if(member.getId() == null) throw new Exception("중복된 아이디입니다");
		memberDao.insertMember(member);
	}

	@Override
	public Boolean checkMemberDoubleId(String id) throws Exception {
		Member mem = memberDao.selectMember(id);
		return mem != null;
	}

	@Override
	public Member login(String id, String password) throws Exception {
		Member mem = memberDao.selectMember(id);
		if(mem == null) throw new Exception("존재하지 않는 아이디입니다");
		if(!mem.getPassword().equals(password)) throw new Exception("비밀번호가 틀렸습니다");
		return mem;
	}
}
