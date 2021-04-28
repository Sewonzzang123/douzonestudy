package chapter03;

public class Goods2App {

	public static void main(String[] args) {
		Goods2 goods = new Goods2();
		goods.setName("Nikon");
		goods.setPrice(10000);
		goods.setCountStock(30);
		goods.setCountSold(20);
		
		goods.setPrice(-1);
		
		goods.showInfo();
		

	}

}
