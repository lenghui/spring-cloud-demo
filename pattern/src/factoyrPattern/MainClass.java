package factoyrPattern;

public class MainClass {
	
	public static void main(String[] args) {
		HumanFactory humanFactory = new HumanFactory();
		Human male = humanFactory.createHuman("male");
		male.eat();
		male.sleep();
		male.beat();
	}
}
