package factoyrPattern;

public class HumanFactory{
	
	
	public Human createHuman(String manType) {
		if(manType.equals("male")) {
			return new Male();
		}else if (manType.equals("female")) {
			return new Female();
		}else {
			System.out.println("请输入正确类型");
		}
		
		return null;
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

