package adapterPattern;

// 英标的插头接口
interface EnPluginInterface {
	void chargeWithEn3Point();
}

// 实现英标插头的方法
class EnPlugin implements EnPluginInterface{

	@Override
	public void chargeWithEn3Point() {
		System.out.println("charge with enplugin");
	}
	
}

// 适配器
public class PluginAdapter implements CnPluginInterface{

	private EnPluginInterface enPlugin;
	
	public PluginAdapter(EnPluginInterface enPlugin) {
		this.enPlugin = enPlugin;
	}
	
	@Override
	public void chargeWith2Point() {
		enPlugin.chargeWithEn3Point();
	}
	
}

