package wrapperPattern;

public class Wrapper {

}

// ³éÏóÀà
abstract class Girl {
	String description = "no";
	
	public String getDescription() {
		return description;
	}
}

class UsaGirl extends Girl{
	public UsaGirl() {
		description = "USA Girl";
	}
}

class CnGirl extends Girl{
	public CnGirl() {
		description = "CN Girl";
	}
}

// ×°ÊÎÆ÷
abstract class GirlDecorator extends Girl{
	public abstract String getDescription();
}

class GoldHair extends GirlDecorator{

	private Girl girl;
	
	public GoldHair(Girl girl) {
		this.girl = girl;
	}
	
	@Override
	public String getDescription() {
		return girl.getDescription()+"+gold hair";
	}
	
}

class TallGirl extends GirlDecorator{
	private Girl girl;

	public TallGirl(Girl girl) {
		this.girl = girl;
	}

	@Override
	public String getDescription() {
		return girl.getDescription()+"+ very tall";
	}
}



