package wrapperPattern;

public class MainTest {
	public static void main(String[] args) {
		Girl g1 = new UsaGirl();
		System.out.println(g1.getDescription());
		GoldHair g2 = new GoldHair(g1);
		System.out.println(g2.getDescription());
		TallGirl g3 = new TallGirl(g2);
		System.out.println(g3.getDescription());
	}
}
