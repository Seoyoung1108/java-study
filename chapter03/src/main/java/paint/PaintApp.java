package paint;

public class PaintApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point point1 = new Point();
		point1.setX(10);
		point1.setY(10);
		point1.show();

		Point point2=new Point(100,200);
		point2.show(true);
		point2.show(false);
	}
}
