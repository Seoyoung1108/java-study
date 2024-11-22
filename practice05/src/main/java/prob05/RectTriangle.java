package prob05;

public class RectTriangle extends Shape {
	public RectTriangle(float width, float height) {
		this.width=width;
		this.height=height;
	}
	
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		double area = width*height*0.5;
		return area;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		double perimeter = width+height+Math.sqrt(width*width+height*height);
		return perimeter;
	}

}
