package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {
	// 텍스트파일 tab, space 처리 => 이 방법보다 scanner 사용이 낫다.
	public static void main(String[] args) {
		BufferedReader br = null;
		
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
			
			// 1. 기반 스트림
			FileInputStream fis = new FileInputStream(file);
			
			// 2. 보조 스트림1
			InputStreamReader isr = new InputStreamReader(fis);
			
			// 3. 보조 스트림2
			br = new BufferedReader(isr);
			
			// 4. 처리
			String line = null;
			while((line=br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line,"\t ");
				
				int index = 0;
	
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					
					if(index==0) { // 이름
						System.out.print(token+":");
					} else if(index==1||index==2) { // 전화번호1,2
						System.out.print(token+"-");
					} else { // 전화번호3
						System.out.println(token);
					}
					
					index++;
				}
			}
			
		} catch (IOException e) {
			System.out.println("error: "+e);
		} finally {
			try {
				if(br!=null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		// 2. 보조 스트림
	}

}
