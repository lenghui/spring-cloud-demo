package emu;

interface IConstants {
	String MON = "Mon";
	String TUE = "Tue";
}


enum EnumTest{
	MON("����һ");
	
	private String value;
	
	private EnumTest(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}


