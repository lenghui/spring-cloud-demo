package adapterPattern;

// 国标插头
interface CnPluginInterface {
	void chargeWith2Point();
}

// 实现国标插头的方法
class CnPlugin implements CnPluginInterface{

	@Override
	public void chargeWith2Point() {
		System.out.println("charge with cnplugin");
	}
	
}


// 在国家内充电
class Home {
	private CnPluginInterface cnPlugin;
	
	public Home() {
		
	}
	
	public Home(CnPluginInterface cnPlugin) {
		this.cnPlugin = cnPlugin;
	}
	
	public void charge() {
		cnPlugin.chargeWith2Point();
	}
	
}
