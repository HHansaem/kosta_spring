package com.example.di.sample4;

import java.io.IOException;

public class MessageBeanImpl implements MessageBean {
	private String name;  
	private String greeting;  
	private Outputer outputter;
	
	//생성자를 통한 주입
	public MessageBeanImpl(String name) {
		this.name = name;
	}
	
	//setter를 통한 주입
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public void setOutputter(Outputer outputter) {
		this.outputter = outputter;
	}

	@Override
	public void sayHello() {
		String message = greeting + ", " + name + "!";
		System.out.println(message);
		//파일로 저장
		try {
			outputter.output(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
