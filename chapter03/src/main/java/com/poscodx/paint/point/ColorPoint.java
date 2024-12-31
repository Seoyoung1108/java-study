package com.poscodx.paint.point;

public class ColorPoint extends Point {
	private String color;
	
	public ColorPoint(int x, int y, String color) {
		//setX(x);
		//setY(y);
		super(x,y);
		this.color=color;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	// annotation
	@Override
	public void show() {
		// 부분 재구현 할 거면 사용 - super.show();
		System.out.println("Point[x="+getX()+", y="+getY()+", color="+color+"]를 그렸습니다.");
	}
}
