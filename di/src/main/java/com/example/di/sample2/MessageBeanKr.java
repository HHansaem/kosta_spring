package com.example.di.sample2;

public class MessageBeanKr implements MessageBean {
	@Override
	public void sayHello(String name) {
		System.out.println("안녕, " + name);
	}
}
