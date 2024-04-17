package com.kosta.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.board.dao.MemberDao;
import com.kosta.board.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
	
	@Override
	public void join(Member member) throws Exception {
		Member mem = memberDao.selectMember(member.getId());
		if(mem != null) throw new Exception("중복된 아이디입니다");
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
		if(!mem.getPassword().equals(password.trim())) throw new Exception("비밀번호가 틀렸습니다");
		return mem;
	}
}
