package prob02;

public class Goods {
	private String name;
	private String price;
	private String stock;
	
	public Goods(String name, String price, String stock) {
		this.name=name;
		this.price=price;
		this.stock=stock;
	}
	
	public void printInfo() {
		System.out.println(name+"(가격: "+price+"원)이 "+stock+"개 입고 되었습니다.");
	}
}