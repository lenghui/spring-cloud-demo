package observePattern;

public class MainTest {
	public static void main(String[] args) {
		VedioSite vedioSite = new VedioSite();
		vedioSite.registerObserver(new VedioFans("����"));
		vedioSite.registerObserver(new VedioFans("��÷÷"));
		vedioSite.registerObserver(new VedioFans("��÷"));
		vedioSite.addVedio("bat man");
		
	}
}