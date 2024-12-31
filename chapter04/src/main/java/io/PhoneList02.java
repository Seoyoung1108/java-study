package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PhoneList02 {
	// scanner
	public static void main(String[] args) {
		Scanner scanner = null;
		
		try { // 보기 좋게 try를 위로 올리기
			File file = new File("./phone.txt");
			if(!file.exists()) {
				System.out.println("file not found: ");
				return;
			}
			
			System.out.println("== 파일 정보 ==");
			System.out.println(file.getAbsolutePath());
			System.out.println(file.length()+"bytes");
			Long timestamp = file.lastModified();
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(timestamp)));
			
			System.out.println("== 전화번호 ==");
			
			scanner = new Scanner(file);
			
			// 처리
			while(scanner.hasNextLine()) {
				String name = scanner.next();
				String phone01 = scanner.next();
				String phone02 = scanner.next();
				String phone03 = scanner.next();

				System.out.println(name+":"+phone01+"-"+phone02+"-"+phone03);
			}
		} catch (IOException e) {
			System.out.println("error: "+e);
		} finally {
			scanner.close();
		}
	}

}
