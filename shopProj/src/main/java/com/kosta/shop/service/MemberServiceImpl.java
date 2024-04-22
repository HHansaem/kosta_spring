package com.kosta.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.MemberDao;
import com.kosta.shop.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void signUp(Member member) throws Exception {
		memberDao.insertMember(member);
	}

	@Override
	public Boolean idCheck(String userid) throws Exception {
		Integer cnt = memberDao.idCheck(userid);
		return cnt==1;
	}

	@Override
	public Member login(String userid, String passwd) throws Exception {
		Member member = memberDao.selectMember(userid);
		if(member == null) return null;
		if(!member.getPasswd().equals(passwd.trim())) return null;
		return member;
	}

	@Override
	public Member myPage(String userid) throws Exception {
		return memberDao.selectMember(userid);
	}

	@Override
	public void modifyMyPage(Member member) throws Exception {
		memberDao.updateMember(member);
	}

}
