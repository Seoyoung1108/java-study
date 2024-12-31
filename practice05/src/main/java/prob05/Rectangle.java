package prob05;

public class Rectangle extends Shape implements Resizable {
	public Rectangle(float width, float height) {
		this.width=width;
		this.height=height;
	}
	
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		double area = width*height;
		return area;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		double perimeter = (width+height)*2;
		return perimeter;
	}

	@Override
	public void resize(double rate) {
		// TODO Auto-generated method stub
		width*=rate;
		height*=rate;
	}
}
