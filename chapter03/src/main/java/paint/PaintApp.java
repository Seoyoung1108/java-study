package paint;

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
