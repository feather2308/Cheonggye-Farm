package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FarmData {
	class InGameTime {
		int day;
		int hour, minute;
		
		public InGameTime() {
			day = 0;
			hour = 0;
			minute = 0;
		}
		
		protected void add(int time) {
			minute += time;
			check();
		}
		
		private void check() {
			if(minute >= 60) {
				hour += 1;
				minute = 0;
			}
			if(hour >= 24) {
				day += 1;
				hour = 0;
			}
		}
	}

	protected InGameTime time;
	
	private FarmCanvas farmCanvas;
	
	private Map<String, Integer> crop = new HashMap<>();
	private ArrayList<int[]> field = new ArrayList<>();
	private int coin = 0;
	
	public FarmData(FarmCanvas farmCanvas) {
		this.farmCanvas = farmCanvas;
		time = new InGameTime();
		
		setting();
		callData();
	}
	
	private void setting() {
		coin = 500;
		
		crop.put("Potato", 0);
		crop.put("PotatoSeed", 0);
		crop.put("Carrot", 0);
		crop.put("CarrotSeed", 0);
		crop.put("Beetroot", 0);
		crop.put("BeetrootSeed", 0);
		
		field.add(new int[] {0, 0, 0, 0});
		field.add(new int[] {0, 0, 0, 0});
		field.add(new int[] {0, 0, 0, 0});
		field.add(new int[] {0, 0, 0, 0});
		field.add(new int[] {0, 0, 0, 0});
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

	public ArrayList<int[]> getField() {
		return field;
	}
	
	public int[] getField(int i) {
		return field.get(i);
	}
	
	public void setField(int i, int[] value) { //[0 작물번호, 1 성장단계, 2 성장시간, 3 성장목표시간]
		field.set(i, value);
	}
	
	public void putField(int[] value) { 
		field.add(value);
	}
	
	public void checkField(int i) {
		int[] temp = field.get(i);
		if(temp[2] >= getCropTime(farmCanvas.getCropName(temp[0], false), temp[1]) && temp[1] != 4) {
			temp[1]++;
			temp[2] = 0;
			field.set(i, temp);
		}
		farmCanvas.refreshField();
	}
	
	private int getCropTime(String name, int level) {
		return 10;
	}
	
	public int getCoin() {
		return coin;
	}
	
	public void setCoin(int value, boolean plus) {
		if(plus) coin += value;
		else coin -= value;
	}
}