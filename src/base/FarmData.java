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
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
				farmCanvas.jlbDay2.setText(day + " 일");
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
				if(farmData.getCrop("FertilizedEgg") >= 1) {
					farmData.setCrop("FertilizedEgg", 1, false);
					isPlace = true;
					
					farmCanvas.jlbTamago.setIcon(new ImageIcon(farmCanvas.poultryImage_egg.getSubimage(0, 0, 100, 100).getScaledInstance(120 * farmCanvas.resolution / 80, 120 * farmCanvas.resolution / 80, Image.SCALE_SMOOTH)));
					farmCanvas.jlbPoultryText2.setText("유정란: " + farmData.getCrop("FertilizedEgg"));
					level = 0;
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
				level = -1;
				isPlace = false;
				chick = false;
				
				farmData.setCrop("Chick", 1, true);
				farmCanvas.jlbPoultryText4.setText("병아리: " + farmData.getCrop("Chick"));
				
				int index = farmData.chick.size();
				farmData.chick.add(new Chick(index, true));
				JLabel jlbTemp = new JLabel();
				int x = farmData.chick.get(index).x;
				int y = farmData.chick.get(index).y;
				if(farmData.chick.get(index).getPosition()) {
					jlbTemp.setBounds((400 + 80 + x - y / 2) * farmCanvas.resolution / 80, (435 + y) * farmCanvas.resolution / 80, farmCanvas.size_chick, farmCanvas.size_chick);
				} else {
					jlbTemp.setBounds((680 + x + y / 8) * farmCanvas.resolution / 80, (435 + y) * farmCanvas.resolution / 80, farmCanvas.size_chick, farmCanvas.size_chick);
				}
				farmCanvas.jlbChick.add(jlbTemp);
				farmData.chick.get(index).worker.start();
				farmCanvas.add(jlbTemp, 0);
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
	
	class Chick implements Runnable{
		Thread worker;
		Random rand = new Random();
		
		int index;
		int x, y, position;
		int vx, vy;
		int action;
		int growthTime;
		int demandGrowthTime;
		
		boolean reachedFood;
		boolean isChick;
		
		public void run() {
			while(farmCanvas.run) {
				try {
					move();
					if(action == 1 && getFood() > 0) {
						if(!reachedFood) {
							if(position == 0) {
								vx = 1;
							} else {
								vx = -1;
							}
							x += vx;
							check();
							farmCanvas.refreshChick(index, isChick);
							Thread.sleep(100);
						} else {
							vx = 0;
							Thread.sleep(500);
							action++;
							farmCanvas.refreshChick(index, isChick);
							checkGrow();
							if(worker == null)
								return;
							Thread.sleep(500);
							action = 0;
							farmCanvas.refreshChick(index, isChick);
							Thread.sleep(100);
						}
					} else if (action == 1) {
						action = 0;
						vx = 0;
						vy = 0;
					}
					
					x += vx;
					y += vy;
					
					check();
					
					farmCanvas.refreshChick(index, isChick);
					
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		Chick(int index, boolean isChick) {
			position = rand.nextInt(2);
			if(position == 0) {
				x = 45;
				y = 75;
			} else {
				x = 30;
				y = 75;
			}
			action = 0;
			growthTime = 0;
			demandGrowthTime = 5;
			reachedFood = false;
			
			this.index = index;
			this.isChick = isChick;
			
			worker = new Thread(this);
		}
		
		Chick(int index, boolean isChick, int[] temp) {
			position = temp[0];
			x = temp[1];
			y = temp[2];
			action = temp[3];
			growthTime = 0;
			demandGrowthTime = 5;
			reachedFood = false;
			
			this.index = index;
			this.isChick = isChick;
			
			worker = new Thread(this);
		}

		void check() {
			reachedFood = false;
			
			if (x <= 0) {
				x = 0;
				vx = 1;
				if (position == 1)
					reachedFood = true;
			} else if (position == 0) {
				if (x >= 90) {
					x = 90;
					vx = -1;
					reachedFood = true;
				}
			} else if (position == 1) {
				if (x >= 60) {
					x = 60;
					vx = -1;
				}
			}
			
			if (y <= 0) {
				y = 0;
				vy = 1;
			} else if (y >= 155) {
				y = 155;
				vy = -1;
			}
		}
		
		void checkGrow() {
			if(reachedFood && getFood() > 0) {
				setFood(1, false);
				growthTime++;
				farmCanvas.refreshCoin();
				if(growthTime >= demandGrowthTime) {
					if(isChick) {
						setCrop("Chick", 1, false);
						farmCanvas.jlbPoultryText4.setText("병아리: " + getCrop("Chick"));
						farmCanvas.remove(farmCanvas.jlbChick.get(index));
						farmCanvas.jlbChick.remove(index);
						
						int[] temp = new int[] {position, x, y, action}; 
						
						chick.remove(index);
						for(int i = 0; i < chick.size(); i++) {
							chick.get(i).setIndex(i);
						}

						index = chicken.size();
						setCrop("Chicken", 1, true);
						farmCanvas.jlbPoultryText5.setText("닭: " + getCrop("Chicken"));
						createChicken(index, temp);
						JLabel jlbTemp = new JLabel(farmCanvas.poultryImageIcon_chicken_normal);
						int x = chicken.get(index).x;
						int y = chicken.get(index).y;
						if(chicken.get(index).getPosition()) {
							jlbTemp.setBounds((470 + x - y / 2) * farmCanvas.resolution / 80, (350 + y) * farmCanvas.resolution / 80, farmCanvas.size_chicken, farmCanvas.size_chicken);
						} else {
							jlbTemp.setBounds((645 + x + y / 8) * farmCanvas.resolution / 80, (350 + y) * farmCanvas.resolution / 80, farmCanvas.size_chicken, farmCanvas.size_chicken);
						}
						farmCanvas.jlbChicken.add(jlbTemp);
						chicken.get(index).worker.start();
						if(farmCanvas.pa_poultryFarm)
							farmCanvas.add(jlbTemp, 0);
						
						worker = null;
					} else {
						setCrop("UnfertilizedEgg", 1, true);
						farmCanvas.jlbPoultryText3.setText("무정란: " + getCrop("UnfertilizedEgg"));
						growthTime = 0;
					}
				}
			}
		}
		
		private void setIndex(int i) {
			index = i;
		}

		void move() {
			int i = rand.nextInt(9);
			switch(i) {
			case 0:
				if(vx == -1)
					vx = 0;
				else {
					vx = 1;
					action = 4;
				}
				break;
			case 1:
				if(vx == 1)
					vx = 0;
				else {
					vx = -1;
					action = 3;
				} 
				break;
			case 2:
				if(vy == -1)
					vy = 0;
				vy = 1;
				break;
			case 3:
				if(vy == 1)
					vy = 0;
				vy = -1;
				break;
			case 4:
				vx = 0;
				vy = 0;
				break;
			case 5:
				vx = 0;
				break;
			case 6:
				vy = 0;
				break;
			case 7:
				vx = 0;
				vy = 0;
				action = 1;
				break;
			case 8:
				if(vx == 0 && vy == 0)
					action = 1;
				break;
			default:
				vx = 0;
				vy = 0;
				action = 0;
				break;
			}
		}
		
		boolean getPosition() {
			if(position == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	protected InGameTime time;
	protected Egg egg;
	
	private FarmCanvas farmCanvas;
	
	private Map<String, Integer> crop = new HashMap<>();
	private Map<String, Integer> cropTime = new HashMap<>();
	private ArrayList<int[]> field;
	protected ArrayList<Chick> chick = new ArrayList<>();
	protected ArrayList<Chick> chicken = new ArrayList<>();
	private int coin = 0;
	private int food = 0;
	
	public FarmData(FarmCanvas farmCanvas) {
		this.farmCanvas = farmCanvas;
		time = new InGameTime();
		egg = new Egg(farmCanvas, this);

		setting();
		callData();
	}
	
	public void setting() {
		coin = 50;
		
		crop.put("Potato", 0);
		crop.put("PotatoSeed", 0);
		crop.put("Carrot", 0);
		crop.put("CarrotSeed", 0);
		crop.put("Beetroot", 0);
		crop.put("BeetrootSeed", 0);
		crop.put("SweetPotato", 0);
		crop.put("SweetPotatoSeed", 0);
		crop.put("Daikon", 0);
		crop.put("DaikonSeed", 0);
		
		crop.put("FertilizedEgg", 0);
		crop.put("UnfertilizedEgg", 0);
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
		cropTime.put("SweetPotato0", 30);
		cropTime.put("SweetPotato1", 30);
		cropTime.put("SweetPotato2", 60);
		cropTime.put("SweetPotato3", 90);
		cropTime.put("SweetPotato", 210);
		cropTime.put("Daikon0", 40);
		cropTime.put("Daikon1", 40);
		cropTime.put("Daikon2", 60);
		cropTime.put("Daikon3", 80);
		cropTime.put("Daikon", 220);
		
		field = new ArrayList<>();
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
	        food = ois.readInt();

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
	        
	        oos.writeInt(egg.time);
	        oos.writeInt(egg.level);
	        oos.writeBoolean(egg.isPlace);
	        oos.writeBoolean(egg.chick);
	        
	        oos.writeInt(coin);
	        oos.writeInt(food);

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
		else {
			food -= value;
			if (food <= 0) {
				if(farmCanvas.jlbPoultry != null)
					farmCanvas.jlbPoultry.setIcon(new ImageIcon(farmCanvas.poultryImage_farm.getScaledInstance(500 * farmCanvas.resolution / 80, 500 * farmCanvas.resolution / 80, Image.SCALE_SMOOTH)));
			}
		}
	}
	
	public void setFood(int value) {
		food = value;
	}
	
	public void createChick(int index) {
		chick.add(new Chick(index, true));
	}
	
	public void createChicken(int index) {
		chicken.add(new Chick(index, false));
	}
	
	public void createChicken(int index, int[] temp) {
		chicken.add(new Chick(index, false, temp));
	}
}