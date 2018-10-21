package singletonPattern;

public class Singleton {
	public static void main(String[] args) {
		Wife wife = Wife.INSTANCE;
		System.out.println(wife);
	}
}

// ����ģʽ
class Apple {
	private static final Apple apple = new Apple();
	
	private Apple() {
	}

	public static Apple getApple() {
		return apple;
	}
	
}

// ����ģʽ
class Bird {
	private static Bird bird;
	
	private Bird() {};
	
	public static Bird getBird() {
		if(bird == null) {
			return bird = new Bird();
		}else {
			return bird;
		}
	}
}


enum Wife{
	INSTANCE;
}







