package observePattern;

public class MainTest {
	public static void main(String[] args) {
		VedioSite vedioSite = new VedioSite();
		vedioSite.registerObserver(new VedioFans("ÀîÀÚ"));
		vedioSite.registerObserver(new VedioFans("º«Ã·Ã·"));
		vedioSite.registerObserver(new VedioFans("Âí¶¬Ã·"));
		vedioSite.addVedio("bat man");
		
	}
}