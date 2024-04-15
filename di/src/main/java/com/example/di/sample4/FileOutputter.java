package com.example.di.sample4;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputer {
	private String filePath;
	
	//setter를 통한 주입
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void output(String message) throws IOException {
		FileWriter writer = new FileWriter(filePath);
		writer.write(message);
		writer.close();
	}

}
