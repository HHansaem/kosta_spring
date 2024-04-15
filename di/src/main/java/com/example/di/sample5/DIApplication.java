package com.example.di.sample5;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DIApplication {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans5.xml");
		Account bean = context.getBean("account", Account.class);
		bean.info();
	}
}

//계좌번호:1001, 이름:홍길동, 잔액:100000
