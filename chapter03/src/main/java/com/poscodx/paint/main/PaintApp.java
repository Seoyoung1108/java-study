package com.poscodx.paint.main;

import com.poscodx.paint.i.Drawable;
import com.poscodx.paint.point.ColorPoint;
import com.poscodx.paint.point.Point;
import com.poscodx.paint.shape.Circle;
import com.poscodx.paint.shape.Rectangle;
import com.poscodx.paint.shape.Shape;
import com.poscodx.paint.shape.Triangle;
import com.poscodx.paint.text.GraphicTest;

public class PaintApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point point1 = new Point();
		point1.setX(10);
		point1.setY(10);
		// point1.show();
		//drawPoint(point1);
		draw(point1);

		Point point2=new Point(100,200);
		// point2.show(true);
		//drawPoint(point2);
		// point2.disappear(); - 리팩토링
		point2.show(false);
		draw(point2);
		
		//ColorPoint point3 = new ColorPoint();
		// point3.setX(50);
		// point3.setY(100);
		// point3.setColor("red");
		// drawColorPoint(point3);
		// 부모타입의 메소드를 불렀는데 자식으로 적용됨. -> 다형성, 오버라이드
		
		ColorPoint point3 = new ColorPoint(50,100,"red");
		//drawPoint(point3);
		draw(point3);
		
		//drawTriangle(new Triangle());
		//drawRectangle(new Rectangle());
		//drawShape(new Triangle());
		//drawShape(new Rectangle());
		//drawShape(new Circle());
		draw(new Triangle());
		draw(new Rectangle());
		draw(new Circle());
		
		draw(new GraphicTest("안녕"));
		
		// instanceof 연산자
		Circle c = new Circle();
		System.out.println(c instanceof Circle);
		System.out.println(c instanceof Object);
		System.out.println(c instanceof Shape);
		// 오류: 연산자 우측항이 클래스인 경우: 좌측항의 레퍼런스 타입의 hierachy의 상하위만 사용할 수 있다.
		//System.out.println(c instanceof Point);
		//System.out.println(c instanceof Rectangle);
		
		Shape s = new Circle();
		System.out.println(s instanceof Circle);
		System.out.println(s instanceof Object);
		System.out.println(s instanceof Shape);
		System.out.println(s instanceof Rectangle); // 주의
		
		// 연산자 우측항이 인터페이스인 경우: hierachy 상관없이 연산자를 사용할 수 있다.
		System.out.println(c instanceof Drawable);
		System.out.println(c instanceof Runnable);
	}
	
	public static void draw(Drawable drawable) {
		drawable.draw();
	}
	
	public static void drawPoint(Point point) {
		point.show();
	}
	
	//public static void drawColorPoint(ColorPoint point) {
	//	point.show();
	//}
	
	//public static void drawTriangle(Triangle triangle) {
	//	triangle.draw();
	//}
	
	//public static void drawRectangle(Rectangle rectangle) {
	//	rectangle.draw();
	//}
	
	public static void drawShape(Shape shape) {
		shape.draw();
	}
}
