package com.example.di.sample3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DIApplication {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans3.xml");
		MessageBean bean = context.getBean("messageBean", MessageBean.class);
		bean.sayHello("Spring");
	}
}
