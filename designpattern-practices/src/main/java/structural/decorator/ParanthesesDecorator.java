package structural.decorator;

public class ParanthesesDecorator extends Decorator {
	ParanthesesDecorator(Component component) {
		super(component);
	}
	@Override
	public String operation() {
		// TODO Auto-generated method stub
		String text = component.operation();
		return "("+text+")";
	}

}
