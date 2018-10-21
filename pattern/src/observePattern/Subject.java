package observePattern;

import java.util.ArrayList;
import java.util.List;

// 主题接口
public interface Subject {
	void registerObserver(Observer observer);
	
	void removeObserver(Observer observer);
	
	void notifyAllObserver();
	
}

// 观察者接口
interface Observer {
	void update(Subject subject);
}

// 视屏网站实现接口
class VedioSite implements Subject{

	// 观察者列表，以及更新视屏列表
	private List<Observer> observerList;
	
	private List<String> vedioList;
	
	public VedioSite() {
		observerList = new ArrayList<Observer>();
		vedioList = new ArrayList<String>();
	}
	
	@Override
	public void registerObserver(Observer observer) {
		observerList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		vedioList.remove(observer);
	}

	@Override
	public void notifyAllObserver() {
		for(Observer o : observerList) {
			o.update(this);
		}
	}
	
	public void addVedio(String vedio) {
		this.vedioList.add(vedio);
		notifyAllObserver();
	}
	
	 public List<String> getVideos() {
	        return vedioList;
	    }

	    public String toString(){
	        return vedioList.toString();
	    }

}

// 追剧者
class VedioFans implements Observer{
	private String name;
	
	public VedioFans(String name) {
		this.name = name;
	}

	@Override
	public void update(Subject subject) {
		System.out.println(this.name+", new vedio are available!");
		System.out.println(subject);
	}
	
}

