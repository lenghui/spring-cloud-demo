package adapterPattern;

public class MainTest {
	public static void main(String[] args) {
		EnPluginInterface enPlugin = new EnPlugin();
		CnPluginInterface cnPlugin = new PluginAdapter(enPlugin);
		Home home = new Home(cnPlugin);
		home.charge();
	}
}
