package structural.decorator;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new ConcreteComponent("Hello World").operation());
		System.out.println(new ParanthesesDecorator(new ConcreteComponent("Hello World")).operation());
		System.out.println(new BraceDecorator(new ParanthesesDecorator(new ConcreteComponent("Hello World"))).operation());
		System.out.println(new BraceDecorator(new ConcreteComponent("Hello World")).operation());
	}

}
