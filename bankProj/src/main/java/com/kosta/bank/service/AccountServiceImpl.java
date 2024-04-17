package com.kosta.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;

@Service //@Controller처럼 자동생성 (Service에서 사용)
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
//	public void setAccountDao(AccountDao accountDao) {
//		this.accountDao = accountDao;
//	}

	@Override
	public void makeAccount(Account acc) throws Exception {
		if(accountDao.selectAccount(acc.getId()) != null) {
			throw new Exception("계좌번호 중복 오류");
		}
		accountDao.insertAccount(acc);
	}

	@Override
	public Account accountInfo(String id) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		return acc;
	}

	@Override
	public void deposit(String id, Integer money) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		acc.deposit(money);
		accountDao.updateAccountBalance(id, acc.getBalance());
	}

	@Override
	public void withdraw(String id, Integer money) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		acc.withdraw(money);
		accountDao.updateAccountBalance(id, acc.getBalance());
	}

	@Override
	public List<Account> allAccountInfo() throws Exception {
		return accountDao.selectAccountList();
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Account sacc = accountDao.selectAccount(sid);
		if(sacc == null) throw new Exception("보내는 계좌 오류");
		Account racc = accountDao.selectAccount(rid);
		if(racc == null) throw new Exception("받는 계좌 오류");
		sacc.withdraw(money);
		racc.deposit(money);
		accountDao.updateAccountBalance(sid, sacc.getBalance());
		accountDao.updateAccountBalance(rid, racc.getBalance());
	}

	@Override
	public Boolean checkAccountDoubleId(String id) throws Exception {
		Account acc = accountDao.selectAccount(id);
		return acc != null;
	}

}
