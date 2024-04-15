package com.example.di.sample4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DIApplication {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans4.xml");
		MessageBean bean = context.getBean("messageBean", MessageBean.class);
		bean.sayHello();
	}
}
