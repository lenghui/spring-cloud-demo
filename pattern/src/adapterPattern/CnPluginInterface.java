package adapterPattern;

// �����ͷ
interface CnPluginInterface {
	void chargeWith2Point();
}

// ʵ�ֹ����ͷ�ķ���
class CnPlugin implements CnPluginInterface{

	@Override
	public void chargeWith2Point() {
		System.out.println("charge with cnplugin");
	}
	
}


// �ڹ����ڳ��
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
