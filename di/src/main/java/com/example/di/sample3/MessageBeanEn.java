package com.example.di.sample3;

public class MessageBeanEn implements MessageBean {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello, " + name);
	}
}
