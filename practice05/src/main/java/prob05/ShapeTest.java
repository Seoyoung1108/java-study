package prob05;

import java.util.ArrayList;
import java.util.List;

public class ShapeTest {
//shape 변수들 protected
	public static void main(String[] args) {
		List<Shape> list = new ArrayList<Shape>();

		list.add(new Rectangle(5, 6));
		list.add(new RectTriangle(6, 2));

		for (Shape shape : list) {
			System.out.println("area:" + shape.getArea());
			System.out.println("perimeter:" + shape.getPerimeter());

			if (shape instanceof Resizable) {
				Resizable resizable = (Resizable) shape;
				resizable.resize(0.5); //1/2로 줄어듬.
				System.out.println("new area:" + shape.getArea());
				System.out.println("new perimeter:" + shape.getPerimeter());
			}
		}
	}
}