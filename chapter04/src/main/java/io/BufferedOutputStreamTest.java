package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

	public static void main(String[] args) {
		// 보조 스트림
		BufferedOutputStream bos = null;
		try {
			// 기반 스트림
			FileOutputStream fos = new FileOutputStream("hello.txt");
			
			// 보조 스트림
			bos = new BufferedOutputStream(fos);
			
			for(int i=65; i<=90; i++) {
				bos.write(i); // A~Z까지 작성
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found: "+e);
		} catch (IOException e) {
			System.out.println("error: "+e);
		} finally {
			try {
				if(bos!=null) {
					bos.close(); // 보조 스트림만 닫아도 된다.
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
