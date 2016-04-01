package utils;

import java.util.ArrayList;
import java.util.List;

public class Callback {

	private List<Runnable> callbacks = new ArrayList<>();
	
	public void addCallback(Runnable run){
		callbacks.add(run);
	}
	
	public void runAll(){
		for(Runnable r : callbacks){
			r.run();
		}
	}
	
}
