package structural.decorator;

public class BraceDecorator extends Decorator {
	BraceDecorator(Component component) {
		super(component);
	}
	@Override
	public String operation() {
		// TODO Auto-generated method stub
		String text = component.operation();
		return "{"+text+"}";
	}

}
