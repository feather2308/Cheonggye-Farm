package base;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class FarmData {
	class InGameTime {
		int day;
		int hour, minute;
		
		public InGameTime() {
			reset();
		}
		
		protected void reset() {
			day = 0;
			hour = 7;
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

	class Egg {
		FarmCanvas farmCanvas;
		FarmData farmData;
		
		TamagoMouseListener tamagoMouseListener;
		
		int time, level;
		boolean isPlace, chick;
		
		public Egg(FarmCanvas farmCanvas, FarmData farmData) {
			this.farmCanvas = farmCanvas;
			this.farmData = farmData;
			
			tamagoMouseListener = new TamagoMouseListener();
			
			time = 0;
			level = -1;
			isPlace = false;
			chick = false;
		}
		
		void setEgg() {
			if(!isPlace && !chick) {
				if(farmData.getCrop("Egg") >= 1) {
					farmData.setCrop("Egg", 1, false);
					isPlace = true;
					
					farmCanvas.jlbTamago.setIcon(new ImageIcon(farmCanvas.poultryImage_egg.getSubimage(0, 0, 100, 100).getScaledInstance(120 * farmCanvas.resolution / 80, 120 * farmCanvas.resolution / 80, Image.SCALE_SMOOTH)));
				}
			}
		}
		
		void addTime() {
			if(isPlace) {
				time++;
				check();
			}
		}
		
		void check() {
			if(time >= 100 && level < 1) {
				farmCanvas.jlbTamago.setIcon(new ImageIcon(farmCanvas.poultryImage_egg.getSubimage(100, 0, 100, 100).getScaledInstance(120 * farmCanvas.resolution / 80, 120 * farmCanvas.resolution / 80, Image.SCALE_SMOOTH)));
				level = 1;
			} else if(time >= 200 && level < 2) {
				farmCanvas.jlbTamago.setIcon(new ImageIcon(farmCanvas.poultryImage_egg.getSubimage(200, 0, 100, 100).getScaledInstance(120 * farmCanvas.resolution / 80, 120 * farmCanvas.resolution / 80, Image.SCALE_SMOOTH)));
				level = 2;
			}
			
			if(time >= 200) {
				farmCanvas.jlbTamago.addMouseListener(tamagoMouseListener);
				isPlace = false;
				chick = true;
			}
		}
		
		void getChick() {
			if(chick) {
				farmCanvas.jlbTamago.removeMouseListener(tamagoMouseListener);
				
				time = 0;
				level = 0;
				isPlace = false;
				chick = false;
				
				farmData.setCrop("Chick", 1, true);
			}
		}
		
		class TamagoMouseListener implements MouseListener {
			boolean press = false;
			
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					farmCanvas.jlbTamago.setIcon(null);
					getChick();
				}
				farmCanvas.mouseClickEffect = 0;
				farmCanvas.mouseClick.x = farmCanvas.jlbTamago.getX() + e.getPoint().x;
				farmCanvas.mouseClick.y = farmCanvas.jlbTamago.getY() + e.getPoint().y;
				farmCanvas.repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		}
	}
	
	protected InGameTime time;
	protected Egg egg;
	
	private FarmCanvas farmCanvas;
	
	private Map<String, Integer> crop = new HashMap<>();
	private Map<String, Integer> cropTime = new HashMap<>();
	private ArrayList<int[]> field = new ArrayList<>();
	private int coin = 0;
	private int food = 0;
	
	public FarmData(FarmCanvas farmCanvas) {
		this.farmCanvas = farmCanvas;
		time = new InGameTime();
		egg = new Egg(farmCanvas, this);

		setting();
		callData();
	}
	
	private void setting() {
		coin = 50;
		
		crop.put("Potato", 0);
		crop.put("PotatoSeed", 0);
		crop.put("Carrot", 0);
		crop.put("CarrotSeed", 0);
		crop.put("Beetroot", 0);
		crop.put("BeetrootSeed", 0);
		crop.put("Egg", 5);
		crop.put("Chick", 0);
		crop.put("Chicken", 0);
		
		cropTime.put("Potato0", 10);
		cropTime.put("Potato1", 10);
		cropTime.put("Potato2", 20);
		cropTime.put("Potato3", 30);
		cropTime.put("Potato", 70);
		cropTime.put("Carrot0", 15);
		cropTime.put("Carrot1", 15);
		cropTime.put("Carrot2", 30);
		cropTime.put("Carrot3", 45);
		cropTime.put("Carrot", 105);
		cropTime.put("Beetroot0", 20);
		cropTime.put("Beetroot1", 20);
		cropTime.put("Beetroot2", 40);
		cropTime.put("Beetroot3", 60);
		cropTime.put("Beetroot", 140);
		
		addField();
		addField();
		addField();
	}

	@SuppressWarnings("unchecked")
	private void callData() {
	    // 데이터를 불러올 파일의 경로 및 파일 객체 생성
	    String filePath = "farm_data.dat";
	    File file = new File(filePath);

	    // 파일이 존재하지 않으면 기본 설정으로 돌아감
	    if (!file.exists()) {
	        return;
	    }

	    try {
	        // 파일 읽기
	        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

	        // 저장된 데이터 읽기
	        crop = (HashMap<String, Integer>) ois.readObject();
	        field = (ArrayList<int[]>) ois.readObject();
	        
	        time.day = ois.readInt();
	        time.hour = ois.readInt();
	        time.minute = ois.readInt();
	        
	        egg.time = ois.readInt();
	        egg.level = ois.readInt();
	        egg.isPlace = ois.readBoolean();
	        egg.chick = ois.readBoolean();
	        
	        coin = ois.readInt();

	        ois.close();
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public void saveData() {
	    // 데이터를 저장할 파일의 경로 및 파일 객체 생성
	    String filePath = "farm_data.dat";
	    File file = new File(filePath);

	    try {
	        // 파일 쓰기
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

	        // 저장할 데이터 쓰기
	        oos.writeObject(crop);
	        oos.writeObject(field);
	        oos.writeInt(time.day);
	        oos.writeInt(time.hour);
	        oos.writeInt(time.minute);
	        oos.writeInt(coin);

	        oos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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

	public void addField() {
		field.add(new int[] {0, 0, 0, 0, 0});
	}
	
	public ArrayList<int[]> getField() {
		return field;
	}
	
	public int[] getField(int i) {
		return field.get(i);
	}
	
	public void setField(int i, int[] value) { //[0 작물번호, 1 성장단계, 2 성장시간, 3 물시간, 4 비료여부]
		field.set(i, value);
	}
	
	public void setField(int i) {
		field.set(i, new int[] {0, 0, 0, 0, 0});
	}
	
	public void checkField(int i) {
		int[] temp = field.get(i);
		if(temp[2] >= getCropLeftTime(farmCanvas.getCropName(temp[0], false, false), temp[1]) && temp[1] != 4) {
			temp[1]++;
			temp[2] = 0;
			field.set(i, temp);
		}
		farmCanvas.refreshField();
	}
	
	public int getCropLeftTime(String name, int level) {
		try {
			return cropTime.get(name + Integer.toString(level));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int getCropLeftTime(String name) {
		try {
			return cropTime.get(name);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int getCropTime(String name, int level) {
		int sum = 0;
		for(int i = 0; i < level; i++) {
			sum += cropTime.get(name + Integer.toString(i));
		}
		return sum;
	}
	
	public int getCoin() {
		return coin;
	}
	
	public void setCoin(int value, boolean plus) {
		if(plus) coin += value;
		else coin -= value;
	}
	
	public int getFood() {
		return food;
	}
	
	public void setFood(int value, boolean plus) {
		if(plus) food += value;
		else food -= value;
	}
}