

/**
 * 贷款用途枚举类
 * 
 * @author Administrator
 *
 */
public enum PurposeDef {
	COS("整形美容"),
	DEC("房屋装修"),
	EDU("教育培训"),
	FLI("手机数码"),
	FMY("日常消费"),
	HEA("健康医疗"),
	MAR("结婚庆典"),
	SALE("家用电器"),
	STO("家具家居"),
	TRA("外出旅游"),
	HOU("房屋租赁");

	private String purposeDefDesc;
	private PurposeDef(String purposeDefDesc){
		this.purposeDefDesc = purposeDefDesc;
	}
	
	public String getProductOriginDesc(){
		return purposeDefDesc;
	}
}
