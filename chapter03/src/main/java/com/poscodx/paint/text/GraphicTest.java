package com.poscodx.paint.text;

import com.poscodx.paint.i.Drawable;

public class GraphicTest implements Drawable {
	private String text;
	
	public GraphicTest(String text) {
		this.text=text;
	}
	@Override
	public void draw() {
		System.out.println("테스트 '"+text+"'을 그렸습니다.");		
	}
}
