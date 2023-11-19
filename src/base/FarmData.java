package base;

import java.util.HashMap;
import java.util.Map;

public class FarmData {
	private Map<String, Integer> crop = new HashMap<>();
	
	public FarmData() {
		setting();
		callData();
	}
	
	private void setting() {
		crop.put("Potato", 0);
		crop.put("Carrot", 0);
		crop.put("Beetroot", 0);
	}

	private void callData() {
		// TODO Auto-generated method stub
		
	}
	
	public int getCrop(String name) {
		return crop.get(name);
	}
	
	public void setCrop(String name, int count) {
		crop.put(name, count);
	}
	
	public void setCrop(String name, int count, boolean plus) {
		if(plus) crop.put(name, crop.get(name) + count);
		else crop.put(name, crop.get(name) - count);
	}
}