package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FarmData {
	protected Map<String, Integer> crop = new HashMap<>();
	protected ArrayList<int[]> field = new ArrayList<>();
	protected int coin = 0;
	
	public FarmData() {
		setting();
		callData();
	}
	
	private void setting() {
		coin = 0;
		
		crop.put("Potato", 0);
		crop.put("Carrot", 0);
		crop.put("Beetroot", 0);
		
		field.add(new int[] {0, 0});
		field.add(new int[] {0, 0});
		field.add(new int[] {0, 0});
		field.add(new int[] {0, 0});
		field.add(new int[] {0, 0});
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

	public int[] getField(int i) {
		return field.get(i);
	}
	
	public void setField(int i, int[] value) { //[0 작물번호, 1 성장단계]
		field.set(i, value);
	}
	
	public void putField(int[] value) { 
		field.add(value);
	}
	
	public String getCoin() {
		return coin + "";
	}
}