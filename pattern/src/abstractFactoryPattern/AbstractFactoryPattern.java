package abstractFactoryPattern;

public class AbstractFactoryPattern {
	public static void main(String[] args) {
		KitchenFactory factory = new AKFactory();
		System.out.printf("food:%s, tableWare:%s",factory.getFood().getFoodName(),factory.getTableWare().getTableWare());
	}
}


interface Food {
	String getFoodName();
}

interface TableWare{
	String getTableWare();
}

interface KitchenFactory{
	Food getFood();
	
	TableWare getTableWare();
}

class Apple implements Food{

	@Override
	public String getFoodName() {
		return "apple";
	}
}

class Knife implements TableWare{

	@Override
	public String getTableWare() {
		return "knife";
	}
}

class AKFactory implements KitchenFactory{

	@Override
	public Food getFood() {
		return new Apple();
	}

	@Override
	public TableWare getTableWare() {
		return new Knife();
	}
}

