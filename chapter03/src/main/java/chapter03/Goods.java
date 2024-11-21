package chapter03;

public class Goods {
	private String name;
	private int price;
	private int countStock;
	private int countSold;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	
	public void printInfo() {
		// this: 런타임 때 객체가 스스로를 레퍼런스 하는 것(생략 가능)
		System.out.println("상품이름: "+ this.name + ", 가격: "+ price + ", 판매량: "+ countSold + ", 재고량: "+ countStock);
	}
}
