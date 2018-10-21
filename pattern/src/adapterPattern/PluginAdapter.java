package adapterPattern;

// Ӣ��Ĳ�ͷ�ӿ�
interface EnPluginInterface {
	void chargeWithEn3Point();
}

// ʵ��Ӣ���ͷ�ķ���
class EnPlugin implements EnPluginInterface{

	@Override
	public void chargeWithEn3Point() {
		System.out.println("charge with enplugin");
	}
	
}

// ������
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

