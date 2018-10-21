package factoyrPattern;

class MainTest{
	public static void main(String[] args) {
		Human male = StaticMultiFactory.creatMale();
		male.eat();
		male.sleep();
		male.beat();
	}
}

/**
 * 静态工厂
 * 
 * @author Administrator
 *
 */
class StaticMultiFactory{
	public static Human creatMale() {
		return new Male();
	}
	
	public static Human createFamale() {
		return new Female();
	}
}

/**
 * 普通工厂
 * @author Administrator
 *
 */
class HumanFactory{
	
	public static void main(String[] args) {
		HumanFactory humanFactory = new HumanFactory();
		Human male = humanFactory.createHuman("male");
		male.eat();
		male.sleep();
		male.beat();
	}
	public Human createHuman(String manType) {
		if(manType.equals("male")) {
			return new Male();
		}else if (manType.equals("female")) {
			return new Female();
		}else {
			System.out.println("参数输入错误！");
		}
		
		return null;
	}

}


/**
 * 多工厂
 * @author Administrator
 *
 */
class MultiFactory{
	public Human creatMale() {
		return new Male();
	}
	
	public Human createFamale() {
		return new Female();
	}
}

class Male implements Human{

	@Override
	public void eat() {
		System.out.println("male can eat!");
	}

	@Override
	public void sleep() {
		System.out.println("male can sleep!");
	}

	@Override
	public void beat() {
		System.out.println("male can beat");
	}
	
}


class Female implements Human{

	@Override
	public void eat() {
		System.out.println("famale can eat!");
	}

	@Override
	public void sleep() {
		System.out.println("famale can sleep!");
	}

	@Override
	public void beat() {
		System.out.println("famale can beat");
	}
	
}

