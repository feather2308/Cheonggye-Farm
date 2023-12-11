package base;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import java.io.BufferedInputStream;
import java.io.IOException;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FarmCanvas extends JPanel implements Runnable, MouseListener {
	protected MyLauncher myLauncher;
	protected FarmData farmData = new FarmData(this);

	protected Font font, font_count;

	// cursor
	Map<String, Cursor> cursor = new HashMap<>();
	
	protected Cursor customCursor_chicken;
	protected Cursor customCursor_potato;
	protected Cursor customCursor_carrot;
	protected Cursor customCursor_beetroot;

	protected final String cursor_chicken = "/resource/cursor/cursor_chicken.png";
	protected final String cursor_potato = "/resource/cursor/cursor_potato.png";
	protected final String cursor_carrot = "/resource/cursor/cursor_carrot.png";
	protected final String cursor_beetroot = "/resource/cursor/cursor_beetroot.png";
	protected final String mainLobby_background = "/resource/mainLobby/background.png";
	protected final String mainLobby_chicken = "/resource/mainLobby/chicken.png";
	protected final String mainLobby_singsing = "/resource/mainLobby/singsing.png";
	protected final String mainLobby_buttonstart = "/resource/mainLobby/buttonstart.png";
	protected final String mainLobby_buttondesc = "/resource/mainLobby/buttondesc.png";
	protected final String mainLobby_buttonsett = "/resource/mainLobby/buttonsett.png";
	protected final String mainLobby_cloud = "/resource/mainLobby/cloud.png";
	protected final String inGame_starcoin = "/resource/inGame/starcoin.png";
	protected final String inGame_sprout = "/resource/inGame/sprout.png";
	protected final String inGame_wateringcan = "/resource/inGame/wateringcan.png";
	protected final String inGame_apple = "/resource/inGame/apple.png";
	protected final String inGame_fertilizer = "/resource/inGame/fertilizer.png";
	protected final String inGame_shovel = "/resource/inGame/shovel.png";
	protected final String inGame_background = "/resource/inGame/background.png";
	protected final String inGame_background_dawn = "/resource/inGame/background_dawn.png";
	protected final String inGame_background_sunset = "/resource/inGame/background_sunset.png";
	protected final String inGame_background_evening = "/resource/inGame/background_evening.png";
	protected final String inGame_btn = "/resource/inGame/btn.png";
	protected final String inGame_shop = "/resource/inGame/shop.png";
	protected final String inGame_chickenhouse = "/resource/inGame/chickenhouse.png";
	protected final String inGame_back = "/resource/inGame/back.png";
	protected final String inGame_cloud1 = "/resource/inGame/cloud1.png";
	protected final String inGame_cloud2 = "/resource/inGame/cloud2.png";
	protected final String inGame_sign = "/resource/inGame/sign.png";
	protected final String crop_potato = "/resource/inGame/crop/potato.png";
	protected final String crop_carrot = "/resource/inGame/crop/carrot.png";
	protected final String crop_beetroot = "/resource/inGame/crop/beetroot.png";
	protected final String poultry_farm = "/resource/inGame/poultry/poultryfarm.png";
	protected final String poultry_farmfood = "/resource/inGame/poultry/poultryfarmfood.png";
	protected final String poultry_x = "/resource/inGame/poultry/x.png";
	protected final String poultry_btn = "/resource/inGame/poultry/btn.png";
	protected final String poultry_egg = "/resource/inGame/poultry/egg.png";
	protected final String poultry_chicken = "/resource/inGame/poultry/chicken.png";
	protected final String poultry_hatchery = "/resource/inGame/poultry/hatchery.png";
	
	protected final String[] mainLobby_logo = {
			"/resource/mainLobby/logo1.png",
			"/resource/mainLobby/logo2.png",
			"/resource/mainLobby/logo3.png" };
	protected final String[] mainLobby_cloud1 = {
			"/resource/mainLobby/cloud1_0.png",
			"/resource/mainLobby/cloud1_1.png",
			"/resource/mainLobby/cloud1_2.png" };
	protected final String[] mainLobby_cloud2 = {
			"/resource/mainLobby/cloud2_0.png",
			"/resource/mainLobby/cloud2_1.png",
			"/resource/mainLobby/cloud2_2.png" };
	
	protected Image mainLobbyImage_background;
	protected Image mainLobbyImage_chicken;
	protected Image mainLobbyImage_singsing;
	protected Image inGameImage_background;
	protected Image inGameImage_background_dawn;
	protected Image inGameImage_background_sunset;
	protected Image inGameImage_background_evening;
	protected Image inGameImage_starcoin;
	
	protected BufferedImage cursorImage_chicken;
	protected BufferedImage cursorImage_potato;
	protected BufferedImage cursorImage_carrot;
	protected BufferedImage cursorImage_beetroot;
	protected BufferedImage mainLobbyImage_buttonstart;
	protected BufferedImage mainLobbyImage_buttondesc;
	protected BufferedImage mainLobbyImage_buttonsett;
	protected BufferedImage mainLobbyImage_cloud;
	protected BufferedImage inGameImage_sprout;
	protected BufferedImage inGameImage_wateringcan;
	protected BufferedImage inGameImage_apple;
	protected BufferedImage inGameImage_fertilizer;
	protected BufferedImage	inGameImage_shovel;
	protected BufferedImage inGameImage_btn;
	protected BufferedImage inGameImage_shop;
	protected BufferedImage inGameImage_chickenhouse;
	protected BufferedImage inGameImage_back;
	protected BufferedImage inGameImage_cloud1;
	protected BufferedImage inGameImage_cloud2;
	protected BufferedImage inGameImage_sign;
	protected BufferedImage cropImage_potato;
	protected BufferedImage cropImage_carrot;
	protected BufferedImage cropImage_beetroot;
	protected BufferedImage poultryImage_farm;
	protected BufferedImage poultryImage_farmfood;
	protected BufferedImage poultryImage_x;
	protected BufferedImage poultryImage_btn;
	protected BufferedImage poultryImage_egg;
	protected BufferedImage poultryImage_chicken;
	protected BufferedImage poultryImage_hatchery;
	
	protected Image[] mainLobbyImage_logo;
	protected Image[] mainLobbyImage_cloud1;
	protected Image[] mainLobbyImage_cloud2;

	// 그림 상태
	protected int mainLobby_logo_index = new Random().nextInt(0, 3);
	protected int mainLobby_cloud_stat = 0;
	protected int mainLobby_cloud_index = 0;
	
	protected Thread worker;
	protected Point mouseClick = new Point();
	protected Point mouseCursor = new Point();

	protected final String inGame_bat = "/resource/inGame/bat.png";
	protected BufferedImage inGameImage_bat;
	
	// 더블버퍼링용 변수
	protected Graphics bufferGraphics = null;
	protected Image offscreen;

	protected final int[] mouseClickEffectSize = { 2, 4, 6, 8 };

	protected int mouseClickEffect = -1;
	protected int resolution;

	// paint able
	protected boolean pa_mainLobby = true;
	protected boolean pa_gameDesc = false;
	protected boolean pa_inGame = false;
	protected boolean pa_bottomInfo = false;
	protected boolean pa_shop = false;
	protected boolean pa_poultryFarm = false;

	// paint component
	protected boolean pa_mainLobbyComponent = true;
	protected boolean pa_gameDescComponent = false;
	protected boolean pa_inGameComponent = false;
	protected boolean pa_shopComponent = false;
	protected boolean pa_poultryFarmComponent = false;

	// loading image
	protected boolean la_mainLobby = true;
	protected boolean la_inGame = false;

	JLabel jlbStarCoinValue;
	JLabel jlbTimeText;
	JLabel jlbShop;
	JLabel jlbShopBack;
	JLabel jlbChickenHouse;
	JLabel jlbCloud1, jlbCloud2;
	JLabel jlbPicturePotato, jlbLorePotato, jlbLore2Potato, jlbLore3Potato, jlbBuyPotato, jlbSellPotato;
	JLabel jlbPictureCarrot, jlbLoreCarrot, jlbLore2Carrot, jlbLore3Carrot, jlbBuyCarrot, jlbSellCarrot;
	JLabel jlbPictureBeetroot, jlbLoreBeetroot, jlbLore2Beetroot, jlbLore3Beetroot, jlbBuyBeetroot, jlbSellBeetroot;
	
	ArrayList<JLabel> jlbCropField, jlbCropText, jlbCropTime, jlbBat;
	Map<Integer, BufferedImage> cropImage;

	// 기준점
	JLabel jlbPoint, jlbPointField;
	// 작물 라벨 - 감자, 당근, 비트
	JLabel jlbPotato, jlbPotatoText, jlbPotatoCount, jlbPotatoClick;
	JLabel jlbCarrot, jlbCarrotText, jlbCarrotCount, jlbCarrotClick;
	JLabel jlbBeetroot, jlbBeetrootText, jlbBeetrootCount, jlbBeetrootClick;
	// 삽, 호였던 것 -> 수정해야 함
	JLabel jlbShovel, jlbShovelText, jlbShovelClick;
	JLabel jlbHoe, jlbHoeText, jlbHoeClick;
	// 뒤로가기
	JLabel jlbBack, jlbBackText, jlbBackClick;

	int x_back_sh, x_back_sp;
	int y_image, y_text, y_count, y_click;
	int x_potato, x_carrot, x_beetroot;
	int x_shovel, x_hoe;
	int jlb_size, jlb_click_x_size, jlb_click_y_size;
	int count, sprout_camera_max, field_camera_max;
	int currentCrop = 0;
	int cloud_x = -getWidth();

	public FarmCanvas(MyLauncher myLauncher, int resolution) {
		this.myLauncher = myLauncher;
		this.resolution = resolution;
		addMouseListener(this);

		// 폰트
		font = myLauncher.font.deriveFont(18f * resolution / 80);
		font_count = myLauncher.font.deriveFont(15f * resolution / 80);
	}

	ScheduledExecutorService scheduler;

	public void start() {
		worker = new Thread(this);
		worker.start();

		// 스레드 풀을 생성합니다.
		scheduler = Executors.newScheduledThreadPool(1);

		// 타이머 작업을 정의합니다.
		Runnable task = new Runnable() {
			
			public void run() {
				if(pa_inGame) {
					try {
						for (int i = 0; i < farmData.getField().size(); i++) {
							int[] temp = farmData.getField(i);
							if (temp[0] != 0) {
								temp[2]++;
								farmData.setField(i, temp);
								farmData.checkField(i);
							}
						}
						farmData.time.add(1);
						refreshTime();
						System.out.println(LocalTime.now());
						}
					catch(Exception e) {
						System.out.println(e);
					}
				}
			}
		};

		// 초기 지연시간, 주기, 시간 단위를 설정하여 타이머 작업을 스케줄합니다.
		scheduler.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);

		requestFocus();
		repaint();
	}

	public void stop() {
		// 몇 초 동안 기다린 후 스레드 풀을 종료합니다.
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduler.shutdown();
	}

	public void run() {
		while (true) {
			try {
				if (mouseClickEffect < 4 && mouseClickEffect != -1) {
					mouseClickEffect++;
				}
				if (pa_mainLobby) {
					if (mainLobby_cloud_stat < 30)
						mainLobby_cloud_stat++;
					else
						mainLobby_cloud_stat = 0;
				}
				if (pa_inGame) {
					if(jlbPointField != null) {
						if(getWidth() - cloud_x - jlbPointField.getX() < 0) {
							cloud_x = -getWidth();
						}
					}
					cloud_x++;
					setCloud();
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	public void paint(Graphics g) {
		offscreen = createImage(getSize().width, getSize().height);
		bufferGraphics = offscreen.getGraphics();

		bufferGraphics.setColor(Color.white);
		bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

		if (pa_mainLobby) {
			try {
				paintMainLobby();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (pa_gameDesc) {
			paintGameDesc();
		}
		
		if (pa_inGame) {
			try {
				paintInGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (pa_shop) {
			try {
				paintShop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (pa_poultryFarm) {
			try {
				paintPoultryFarm();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		paintComponents(bufferGraphics);

		// 마우스 클릭 이펙트
		bufferGraphics.setColor(Color.BLACK);
		if (mouseClickEffect < mouseClickEffectSize.length && mouseClickEffect >= 0)
			bufferGraphics.drawOval(mouseClick.x - mouseClickEffectSize[mouseClickEffect] / 2,
					mouseClick.y - mouseClickEffectSize[mouseClickEffect] / 2, mouseClickEffectSize[mouseClickEffect],
					mouseClickEffectSize[mouseClickEffect]);

		g.drawImage(offscreen, 0, 0, null);
	}

	private void loadingMainLobby() throws IOException {
		mainLobbyImage_background = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_background)));
//      mainLobbyImage_logo =              ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo)));
		mainLobbyImage_chicken = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_chicken)));
		mainLobbyImage_singsing = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_singsing)));
		mainLobbyImage_buttonstart = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttonstart)));
		mainLobbyImage_buttondesc = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttondesc)));
		mainLobbyImage_buttonsett = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttonsett)));
		mainLobbyImage_cloud = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud)));
		mainLobbyImage_logo = new Image[] {
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[0]))),
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[1]))),
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[2]))) };
		mainLobbyImage_cloud1 = new Image[] {
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[0]))),
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[1]))),
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[2]))) };
		mainLobbyImage_cloud2 = new Image[] {
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[0]))),
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[1]))),
				ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[2]))) };
		cursorImage_chicken = ImageIO.read(getClass().getResourceAsStream(cursor_chicken));
		cursorImage_potato = ImageIO.read(getClass().getResourceAsStream(cursor_potato));
		cursorImage_carrot = ImageIO.read(getClass().getResourceAsStream(cursor_carrot));
		cursorImage_beetroot = ImageIO.read(getClass().getResourceAsStream(cursor_beetroot));
		customCursor_chicken = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_chicken, new Point(0, 0),
				"Custom Cursor");
		customCursor_potato = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_potato, new Point(0, 0),
				"Custom Cursor");
		customCursor_carrot = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_carrot, new Point(0, 0),
				"Custom Cursor");
		customCursor_beetroot = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_beetroot, new Point(0, 0),
				"Custom Cursor");

		cursor.put("chicken", customCursor_chicken);
		cursor.put("potato", customCursor_potato);
		cursor.put("carrot", customCursor_carrot);
		cursor.put("beetroot", customCursor_beetroot);
		
		inGameImage_shovel = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_shovel)));

		cropImage_potato = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_potato)));
		cropImage_carrot = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_carrot)));
		cropImage_beetroot = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_beetroot)));
		
		poultryImage_x = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_x)));
		poultryImage_egg = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_egg)));
	}

	private void loadingInGame() throws IOException {
		inGameImage_bat = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_bat)));
		inGameImage_btn = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_btn)));
		inGameImage_background = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background)));
		inGameImage_background_dawn = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background_dawn)));
		inGameImage_background_sunset = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background_sunset)));
		inGameImage_background_evening = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background_evening)));
		inGameImage_starcoin = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_starcoin)));
		inGameImage_sprout = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_sprout)));
		inGameImage_wateringcan = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_wateringcan)));
		inGameImage_fertilizer = ImageIO
				.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_fertilizer)));
		inGameImage_apple = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_apple)));

		inGameImage_shop = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_shop)));
		inGameImage_chickenhouse = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_chickenhouse)));
		inGameImage_back = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_back)));
		
		inGameImage_cloud1 = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_cloud1)));
		inGameImage_cloud2 = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_cloud2)));
		inGameImage_sign = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_sign)));
		
		cropImage = new HashMap<>();
		cropImage.put(1, cropImage_potato);
		cropImage.put(2, cropImage_carrot);
		cropImage.put(3, cropImage_beetroot);
		
		jlbPointField = new JLabel();
		jlbPointField.setBounds(0, 0, 0, 0);
		
		poultryImage_farm = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_farm)));
		poultryImage_farmfood = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_farmfood)));
		poultryImage_btn = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_btn)));
		poultryImage_chicken = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_chicken)));
		poultryImage_hatchery = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_hatchery)));
	}

	private void loadingField() throws IOException {
		// 이동 제어용 라벨
		int x = jlbPointField.getX();
		
		jlbBat = new ArrayList<>();
		jlbCropField = new ArrayList<>();
		jlbCropText = new ArrayList<>();
		jlbCropTime = new ArrayList<>();
		for (int i = 0; i < farmData.getField().size(); i++) {
			ImageIcon jlbImage = null;
			if (farmData.getField(i)[0] != 0)
				jlbImage = new ImageIcon(
						cropImage.get(farmData.getField(i)[0]).getSubimage(100 * farmData.getField(i)[1], 0, 100, 100));
			JLabel jlbTemp = new JLabel(jlbImage);
			jlbTemp.setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 - 60 * resolution / 80, 90 * resolution / 80, 90 * resolution / 80);
			add(jlbTemp);
			jlbCropField.add(jlbTemp);
			
			jlbTemp = null;
			if(farmData.getField(i)[0] != 0)
				jlbTemp = new JLabel(getCropName(farmData.getField(i)[0], false, true));
			else jlbTemp = new JLabel("밭");
			jlbTemp.setFont(font);
			jlbTemp.setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 + 35 * resolution / 80, 90 * resolution / 80, 20 * resolution / 80);
			jlbTemp.setHorizontalAlignment(SwingConstants.CENTER);
			add(jlbTemp);
			jlbCropText.add(jlbTemp);
			
			jlbTemp = null;
			if(farmData.getField(i)[0] != 0 && farmData.getField(i)[1] != 4)
				jlbTemp = new JLabel((int)((float) farmData.getField(i)[2] / farmData.getCropTime(getCropName(farmData.getField(i)[0], false, false), farmData.getField(i)[1]) * 100) + "% 성장");
			else if(farmData.getField(i)[0] == 4) jlbTemp = new JLabel("수확"); else jlbTemp = new JLabel("씨앗 심기");
			jlbTemp.setFont(font);
			jlbTemp.setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 + 60 * resolution / 80, 90 * resolution / 80, 20 * resolution / 80);
			jlbTemp.setHorizontalAlignment(SwingConstants.CENTER);
			add(jlbTemp);
			jlbCropTime.add(jlbTemp);
			
			jlbImage = new ImageIcon(inGameImage_bat.getScaledInstance(95 * resolution / 80, 95 * resolution / 80, Image.SCALE_SMOOTH));
			jlbTemp = new JLabel(jlbImage);
			jlbTemp.setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 - 60 * resolution / 80, 90 * resolution / 80, 90 * resolution / 80);
			add(jlbTemp);
			jlbBat.add(jlbTemp);
		}
	}

	private void paintMainLobby() throws IOException {
			if (la_mainLobby) {
				loadingMainLobby();
				la_mainLobby = false;
			}
	
			if (pa_mainLobbyComponent) {
				paintMainLobbyComponent();
				pa_mainLobbyComponent = false;
			}
	
			// 배경
			bufferGraphics.drawImage(mainLobbyImage_background, 0, 0, getWidth(), getHeight(), null);
	
			// 로고
			bufferGraphics.drawImage(mainLobbyImage_logo[mainLobby_logo_index],
					getWidth() / 2 - 600 * resolution / 80 / 2 - 1, 0, 600 * resolution / 80, 300 * resolution / 80, null);
	
			// 치킨
			bufferGraphics.drawImage(mainLobbyImage_chicken, 20 * resolution / 80 - 1, getHeight() - 275 * resolution / 80,
					270 * resolution / 80, 240 * resolution / 80, null);
	
			// 싱싱채소
			bufferGraphics.drawImage(mainLobbyImage_singsing, getWidth() - 280 * resolution / 80 - 1,
					getHeight() - 250 * resolution / 80, 250 * resolution / 80, 200 * resolution / 80, null);
	
			switch (mainLobby_cloud_stat) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				mainLobby_cloud_index = 0;
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				mainLobby_cloud_index = 1;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				mainLobby_cloud_index = 2;
				break;
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
				mainLobby_cloud_index = 3;
				break;
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
				mainLobby_cloud_index = 4;
				break;
			case 25:
			case 26:
			case 27:
			case 28:
			case 29:
				mainLobby_cloud_index = 5;
				break;
			}
			// 구름1
			bufferGraphics.drawImage(
					mainLobbyImage_cloud.getSubimage(220 * mainLobby_cloud_index, 0, 220, 130).getScaledInstance(220, 130,
							Image.SCALE_SMOOTH),
					50 * resolution / 80 - 1, 50 * resolution / 80, 220 * resolution / 80, 130 * resolution / 80, null);
	
			// 구름2
			bufferGraphics.drawImage(
					mainLobbyImage_cloud.getSubimage(230 * mainLobby_cloud_index, 130, 230, 140).getScaledInstance(230, 140,
							Image.SCALE_SMOOTH),
					getWidth() - 300 * resolution / 80 - 1, 65 * resolution / 80, 230 * resolution / 80,
					140 * resolution / 80, null);
	
	//      //중앙점
	//      bufferGraphics.setColor(Color.black);
	//      bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
		}

	private void paintMainLobbyComponent() throws IOException {
		this.removeAll();
		this.setLayout(null);
		setCursor("chicken");

		Point btnSize = new Point();
		btnSize.x = 250;
		btnSize.y = 80;

		JButton btnStart = new JButton(new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 0, 352, 112)
				.getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
		btnStart.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				btnStart.setIcon(
						new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 125 * 2, 352, 112).getScaledInstance(
								btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {
					la_inGame = true;
					pa_mainLobby = false;
					pa_inGame = true;
					pa_inGameComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = btnStart.getX() + e.getPoint().x;
				mouseClick.y = btnStart.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				btnStart.setIcon(
						new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 125 * 1, 352, 112).getScaledInstance(
								btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				btnStart.setIcon(new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 0, 352, 112).getScaledInstance(
						btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
//      btnStart.setFont(font);
		btnStart.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
				getHeight() / 2 + (btnSize.y - 65) * resolution / 80, btnSize.x * resolution / 80,
				btnSize.y * resolution / 80);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		add(btnStart);

		JButton btnDesc = new JButton(new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 0, 352, 112)
				.getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
		btnDesc.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				btnDesc.setIcon(
						new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 125 * 2, 352, 112).getScaledInstance(
								btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {
					pa_gameDesc = true;
					pa_gameDescComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = btnDesc.getX() + e.getPoint().x;
				mouseClick.y = btnDesc.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				btnDesc.setIcon(
						new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 125 * 1, 352, 112).getScaledInstance(
								btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				btnDesc.setIcon(new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 0, 352, 112).getScaledInstance(
						btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
//      btnDesc.setFont(font);
		btnDesc.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
				getHeight() / 2 + (btnSize.y + 35) * resolution / 80, btnSize.x * resolution / 80,
				btnSize.y * resolution / 80);
		btnDesc.setBorderPainted(false);
		btnDesc.setContentAreaFilled(false);
		add(btnDesc);

		JButton btnSett = new JButton(new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 0, 352, 112)
				.getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
		btnSett.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				btnSett.setIcon(
						new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 125 * 2, 352, 112).getScaledInstance(
								btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {

				}
				mouseClickEffect = 0;
				mouseClick.x = btnSett.getX() + e.getPoint().x;
				mouseClick.y = btnSett.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				btnSett.setIcon(
						new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 125 * 1, 352, 112).getScaledInstance(
								btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				btnSett.setIcon(new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 0, 352, 112).getScaledInstance(
						btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
//      btnSett.setFont(font);
		btnSett.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
				getHeight() / 2 + (btnSize.y + 135) * resolution / 80, btnSize.x * resolution / 80,
				btnSize.y * resolution / 80);
		btnSett.setBorderPainted(false);
		btnSett.setContentAreaFilled(false);
		add(btnSett);
	}

	private void paintGameDesc() {
		if(pa_gameDescComponent) {
			paintGameDescComponent();
			pa_gameDescComponent = false;
		}
	}
	
	private void paintGameDescComponent() {
		JLabel jlbBack = new JLabel();
		JLabel jlbPanel = new JLabel();
		JLabel jlbBackBtn = new JLabel(new ImageIcon(poultryImage_x.getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		JLabel[] jlbText = new JLabel[] {null, null, null, null, null, null};
		JLabel[] jlbImage = new JLabel[] {null, null, null, null, null};
		
		jlbBack.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = jlbBack.getX() + e.getPoint().x;
				mouseClick.y = jlbBack.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		
		jlbBackBtn.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					pa_gameDesc = false;
					pa_mainLobbyComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBackBtn.getX() + e.getPoint().x;
				mouseClick.y = jlbBackBtn.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});

		jlbBack.setBounds(0, 0, getWidth(), getHeight());
		add(jlbBack, 0);
		
		jlbPanel.setBackground(Color.white);
		jlbPanel.setOpaque(true);
		jlbPanel.setBorder(new LineBorder(Color.black, 3));
		jlbPanel.setBounds(90 * resolution / 80, 50 * resolution / 80, 1100 * resolution / 80, 620 * resolution / 80);
		add(jlbPanel, 0);
		
		jlbBackBtn.setBorder(new LineBorder(Color.black, 3));
		jlbBackBtn.setBounds(getWidth() - 116 * resolution / 80, 26 * resolution / 80, 50 * resolution / 80, 50 * resolution / 80);
		add(jlbBackBtn, 0);
		
		jlbText[0] = new JLabel("청계 농장 게임설명");
		jlbText[0].setHorizontalAlignment(SwingConstants.CENTER);
		jlbText[0].setFont(font);
		jlbText[0].setBounds(100 * resolution / 80, 80 * resolution / 80, 1080 * resolution / 80, 30 * resolution / 80);
		add(jlbText[0], 0);
		jlbText[1] = new JLabel("밭을 추가하려면 삽 버튼을 누른 후 밭 갈기 버튼을 누르면 밭을 추가할 수 있습니다.");
		jlbText[2] = new JLabel("상점에서 감자 씨앗을 사서 씨앗 버튼을 누른 후 밭을 클릭하면 감자를 심을 수 있습니다.");
		jlbText[3] = new JLabel("상점에서 당근 씨앗을 사서 씨앗 버튼을 누른 후 밭을 클릭하면 당근을 심을 수 있습니다.");
		jlbText[4] = new JLabel("상점에서 비트 씨앗을 사서 씨앗 버튼을 누른 후 밭을 클릭하면 비트를 심을 수 있습니다.");
		jlbText[5] = new JLabel("상점에서 알을 구매하고 양계장에서 알을 부화시켜 병아리를 키울 수 있습니다.");
		
		jlbImage[0] = new JLabel(new ImageIcon(inGameImage_shovel.getSubimage(0, 0, 236, 236).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbImage[1] = new JLabel(new ImageIcon(cropImage_potato.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbImage[2] = new JLabel(new ImageIcon(cropImage_carrot.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbImage[3] = new JLabel(new ImageIcon(cropImage_beetroot.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbImage[4] = new JLabel(new ImageIcon(poultryImage_egg.getSubimage(200, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		
		for(int i = 1; i < jlbText.length; i++) {
			jlbText[i].setFont(font);
			jlbText[i].setBounds(210 * resolution / 80, 25 * resolution / 80 + 110 * i * resolution / 80, 860 * resolution / 80, 30 * resolution / 80);
			add(jlbText[i], 0);
		}
		
		for(int i = 0; i < jlbImage.length; i++) {
			jlbImage[i].setBounds(100 * resolution / 80, 100 * resolution / 80 + 110 * i * resolution / 80, 100 * resolution / 80, 100 * resolution / 80);
			add(jlbImage[i], 0);
		}
	}
	
	private void paintInGame() throws IOException {
		setValue();

		if (la_inGame) {
			loadingInGame();
			loadingField();
			refreshField();
			la_inGame = false;
		}
		if (pa_inGameComponent) {
			paintInGameComponent();
			pa_inGameComponent = false;
		}
		
		switch(farmData.time.hour) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 21:
		case 22:
		case 23:
		case 24:
			bufferGraphics.drawImage(inGameImage_background_dawn, 0, 0, getWidth(), getHeight(), null);
			break;
		case 5:
		case 6:
		case 17:
		case 18:
			bufferGraphics.drawImage(inGameImage_background_sunset, 0, 0, getWidth(), getHeight(), null);
			break;
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
			bufferGraphics.drawImage(inGameImage_background, 0, 0, getWidth(), getHeight(), null);
			break;
		case 19:
		case 20:
			bufferGraphics.drawImage(inGameImage_background_evening, 0, 0, getWidth(), getHeight(), null);
			break;
		default:
			bufferGraphics.drawImage(inGameImage_background, 0, 0, getWidth(), getHeight(), null);
			break;
		}

//      //중앙점
//      bufferGraphics.setColor(Color.black);
//      bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
	}

	private void paintInGameComponent() throws IOException {
		this.removeAll();
		this.setLayout(null);

//      CircleButton btnHelp = new CircleButton("?");
//      btnHelp.addMouseListener(new MouseListener() {
//         @Override
//         public void mouseClicked(MouseEvent e) {
//            
//         }
//
//         public void mousePressed(MouseEvent e) {
//            
//         }
//
//         public void mouseReleased(MouseEvent e) {
//            mouseClickEffect = 0;
//            mouseClick.x = btnHelp.getBounds().x + e.getPoint().x;
//            mouseClick.y = btnHelp.getBounds().y + e.getPoint().y;
//            repaint();
//         }
//
//         public void mouseEntered(MouseEvent e) {
//            btnHelp.setBackground(Color.LIGHT_GRAY);
//         }
//
//         public void mouseExited(MouseEvent e) {
//            btnHelp.setBackground(Color.white);
//         }
//      });
////      btnStart.setFont(font);
//      btnHelp.setBounds(getWidth() - (25 + 50) * resolution / 80 - 1,
//                    10 * resolution / 80,
//                    50 * resolution / 80,
//                    50 * resolution / 80);
//      btnHelp.setBackground(Color.white);
//      add(btnHelp);

		paintStarCoinComponent();
		paintFieldComponent();

		int labelImageSize = 200;
		int labelImageGap = 40;

		// 삽
		JLabel jlbShovel = new JLabel(new ImageIcon(inGameImage_shovel.getSubimage(0, 0, 236, 236).getScaledInstance(
				labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbShovel.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				jlbShovel.setIcon(new ImageIcon(inGameImage_shovel.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {
					paintShovelComponent();
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbShovel.getX() + e.getPoint().x;
				mouseClick.y = jlbShovel.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				jlbShovel.setIcon(new ImageIcon(inGameImage_shovel.getSubimage(236, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				jlbShovel.setIcon(new ImageIcon(inGameImage_shovel.getSubimage(0, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbShovel.setBounds(
				getWidth() / 2 - (labelImageSize / 2 + labelImageSize * 2 + labelImageGap * 2) * resolution / 80 - 1,
				getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1, labelImageSize * resolution / 80,
				labelImageSize * resolution / 80);
		add(jlbShovel);

		// 새싹
		JLabel jlbSprout = new JLabel(new ImageIcon(inGameImage_sprout.getSubimage(0, 0, 236, 236).getScaledInstance(
				labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbSprout.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				jlbSprout.setIcon(new ImageIcon(inGameImage_sprout.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {
					paintSproutComponent();
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSprout.getX() + e.getPoint().x;
				mouseClick.y = jlbSprout.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				jlbSprout.setIcon(new ImageIcon(inGameImage_sprout.getSubimage(236, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				jlbSprout.setIcon(new ImageIcon(inGameImage_sprout.getSubimage(0, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbSprout.setBounds(
				getWidth() / 2 - (labelImageSize / 2 + labelImageSize * 1 + labelImageGap * 1) * resolution / 80 - 1,
				getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1, labelImageSize * resolution / 80,
				labelImageSize * resolution / 80);
		add(jlbSprout);

		// 물뿌리개
		JLabel jlbWateringCan = new JLabel(
				new ImageIcon(inGameImage_wateringcan.getSubimage(0, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbWateringCan.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				jlbWateringCan.setIcon(new ImageIcon(inGameImage_wateringcan.getSubimage(236 * 2, 0, 236, 236)
						.getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80,
								Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {
					paintWateringCanComponent();
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbWateringCan.getX() + e.getPoint().x;
				mouseClick.y = jlbWateringCan.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				jlbWateringCan.setIcon(new ImageIcon(inGameImage_wateringcan.getSubimage(236, 0, 236, 236)
						.getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80,
								Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				jlbWateringCan.setIcon(new ImageIcon(inGameImage_wateringcan.getSubimage(0, 0, 236, 236)
						.getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80,
								Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbWateringCan.setBounds(getWidth() / 2 - (labelImageSize / 2) * resolution / 80 - 1,
				getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1, labelImageSize * resolution / 80,
				labelImageSize * resolution / 80);
		add(jlbWateringCan);

		// 비료
		JLabel jlbFertilizer = new JLabel(
				new ImageIcon(inGameImage_fertilizer.getSubimage(0, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbFertilizer.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_fertilizer.getSubimage(236 * 2, 0, 236, 236)
						.getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80,
								Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {

				}
				mouseClickEffect = 0;
				mouseClick.x = jlbFertilizer.getX() + e.getPoint().x;
				mouseClick.y = jlbFertilizer.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_fertilizer.getSubimage(236, 0, 236, 236)
						.getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80,
								Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_fertilizer.getSubimage(0, 0, 236, 236)
						.getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80,
								Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbFertilizer.setBounds(getWidth() / 2 + (labelImageSize / 2 + labelImageGap * 1) * resolution / 80 - 1,
				getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1, labelImageSize * resolution / 80,
				labelImageSize * resolution / 80);
		add(jlbFertilizer);

		// 사과
		JLabel jlbApple = new JLabel(new ImageIcon(inGameImage_apple.getSubimage(0, 0, 236, 236).getScaledInstance(
				labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbApple.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				// 클릭 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {

				}
				mouseClickEffect = 0;
				mouseClick.x = jlbApple.getX() + e.getPoint().x;
				mouseClick.y = jlbApple.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				// 오버랩 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(236, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				// 기본 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(0, 0, 236, 236).getScaledInstance(
						labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}

		});
		jlbApple.setBounds(
				getWidth() / 2 + (labelImageSize / 2 + labelImageSize + labelImageGap * 2) * resolution / 80 - 1,
				getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1, labelImageSize * resolution / 80,
				labelImageSize * resolution / 80);
		add(jlbApple);
	}

	private void paintStarCoinComponent() {
		// 지갑 숫자
		jlbStarCoinValue = new JLabel(farmData.getCoin() + "");
		jlbStarCoinValue.setFont(font);
		jlbStarCoinValue.setHorizontalAlignment(SwingConstants.LEFT);
		jlbStarCoinValue.setBounds(getWidth() - 235 * resolution / 80 - 1, 20 * resolution / 80, 160 * resolution / 80,
				50 * resolution / 80);
		add(jlbStarCoinValue);

		// 지갑
		JLabel jlbStarCoin = new JLabel(new ImageIcon(inGameImage_starcoin.getScaledInstance(250 * resolution / 80,
				115 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbStarCoin.setBounds(getWidth() - 260 * resolution / 80 - 1, 0, 250 * resolution / 80, 115 * resolution / 80);
		add(jlbStarCoin);
		
		// 시간 이미지
		JLabel jlbTime = new JLabel();
		jlbTime.setBounds(10 * resolution / 80, 10 * resolution / 80, 100 * resolution / 80, 40 * resolution / 80);
		jlbTime.setBorder(new LineBorder(Color.black, 1));
		jlbTime.setBackground(new Color(255, 255, 255, 120));
		jlbTime.setOpaque(true);
		
		// 시간 텍스트
		jlbTimeText = new JLabel(farmData.time.hour + "시 " + farmData.time.minute + "분");
		jlbTimeText.setFont(font);
		jlbTimeText.setBounds(15 * resolution / 80, 15 * resolution / 80, 90 * resolution / 80, 30 * resolution / 80);
		jlbTimeText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbTimeText.setBorder(new LineBorder(Color.black, 1));
		add(jlbTimeText);
		add(jlbTime);
	}

	private void paintFieldComponent() {
		jlbCloud1 = new JLabel(new ImageIcon(inGameImage_cloud1.getScaledInstance(225 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCloud1.setBounds(jlbPointField.getX() + cloud_x, 20 * resolution / 80, 225 * resolution / 80, 100 * resolution / 80);
		add(jlbCloud1);
		
		jlbCloud2 = new JLabel(new ImageIcon(inGameImage_cloud2.getScaledInstance(235 * resolution / 80, 95 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCloud2.setBounds(jlbPointField.getX() + cloud_x + getWidth() / 2, 100 * resolution / 80, 235 * resolution / 80, 95 * resolution / 80);
		add(jlbCloud2);
		
		jlbChickenHouse = new JLabel(new ImageIcon(inGameImage_chickenhouse.getSubimage(0, 35, 168, 98).getScaledInstance(168 * resolution / 80, 98 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbChickenHouse.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					pa_poultryFarm = true;
					pa_poultryFarmComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbChickenHouse.getX() + e.getPoint().x;
				mouseClick.y = jlbChickenHouse.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});
		jlbChickenHouse.setBounds(jlbPointField.getX() + 250 * resolution / 80, getHeight() / 2 - 165 * resolution / 80, 168 * resolution / 80, 98 * resolution / 80);
		add(jlbChickenHouse);
		
		jlbShop = new JLabel(new ImageIcon(inGameImage_shop.getScaledInstance(200 * resolution / 80,
				200 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbShop.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				press = true;
			}

			public void mouseReleased(MouseEvent e) {
				if (press) {
					setCursor("chicken");
					currentCrop = 0;
					pa_inGame = false;
					pa_bottomInfo = false;
					pa_shop = true;
					pa_inGameComponent = false;
					pa_shopComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbShop.getX() + e.getPoint().x;
				mouseClick.y = jlbShop.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});
		jlbShop.setBounds(jlbPointField.getX() + (50) * resolution / 80, (getHeight() - 100) / 2 - 70 * resolution / 80, 200 * resolution / 80, 200 * resolution / 80);
		add(jlbShop);

		add(jlbShop);
		for (int i = 0; i < jlbCropField.size(); i++) {
			add(jlbCropField.get(i));
			add(jlbCropText.get(i));
			add(jlbCropTime.get(i));
			add(jlbBat.get(i));
		}
		
		JLabel jlbCameraReset = new JLabel(new ImageIcon(inGameImage_sign.getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCameraReset.addMouseListener(new MouseListener(){
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					jlbPointField.setBounds(0, 0, 0, 0);
					setComponentBoundField(new int[] {0, 0, 0}, null);
				}
				
				mouseClickEffect = 0;
				mouseClick.x = jlbCameraReset.getX() + e.getPoint().x;
				mouseClick.y = jlbCameraReset.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				press = false;
			}
			});
		jlbCameraReset.setBounds(getWidth() - 150 * resolution / 80, getHeight() / 2 - 160 * resolution / 80, 100 * resolution / 80, 100 * resolution / 80);
		add(jlbCameraReset);
		
		// [0 - 마우스 x축 움직인 정도, 1 - 마우스 초기 x좌표, 2 - 컴포넌트 초기 x좌표]
		int[] currentCamera = new int[] { 0, 0, 0 };

		JLabel mouseControl = new JLabel();
		mouseControl.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				currentCamera[1] = e.getX();
				currentCamera[2] = jlbPointField.getX();
			}

			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = mouseControl.getX() + e.getPoint().x;
				mouseClick.y = mouseControl.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
		mouseControl.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				currentCamera[0] = e.getX() - currentCamera[1];
				setComponentBoundField(currentCamera, e);
			}

			public void mouseMoved(MouseEvent e) {
			}
		});
		mouseControl.setBounds(0, getHeight() / 2 - 130 * resolution / 80, getWidth(), 230 * resolution / 80);
		add(mouseControl);
	}

	/*
		 * 삽 시작
		 */
	
		private void paintShovelComponent() {
			this.removeAll();
			paintStarCoinComponent();
			paintFieldComponent();
			
			JLabel jlbBat = new JLabel("밭 갈기");
			jlbBat.setFont(font);
			jlbBat.setHorizontalAlignment(SwingConstants.CENTER);
			jlbBat.addMouseListener(new MouseListener() {
				boolean press = false;
				public void mouseClicked(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
					press = true;
				}
				public void mouseReleased(MouseEvent e) {
					if (press) {
						farmData.putField(new int[] {0, 0, 0});
						try {
							loadingField();
							refreshField();
							paintShovelComponent();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					
					mouseClickEffect = 0;
					mouseClick.x = jlbBat.getX() + e.getPoint().x;
					mouseClick.y = jlbBat.getY() + e.getPoint().y;
					repaint();
				}
				public void mouseEntered(MouseEvent e) {
				}
				public void mouseExited(MouseEvent e) {
					press = false;
				}
			});
			jlbBat.setBounds(10 * resolution / 80, getHeight() - 160 * resolution / 80, 100 * resolution / 80, 30 * resolution / 80);
			jlbBat.setBorder(new LineBorder(Color.black, 1));
			add(jlbBat);
	
			JLabel jlbShovelBack = new JLabel("뒤로가기");
			jlbShovelBack.setFont(font);
			jlbShovelBack.setHorizontalAlignment(SwingConstants.CENTER);
			jlbShovelBack.addMouseListener(new MouseListener() {
				boolean press = false;
				public void mouseClicked(MouseEvent e) {
				}
	
				public void mousePressed(MouseEvent e) {
					press = true;
				}
	
				public void mouseReleased(MouseEvent e) {
					if (press) {
						try {
							paintInGameComponent();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
	
					mouseClickEffect = 0;
					mouseClick.x = jlbShovelBack.getX() + e.getPoint().x;
					mouseClick.y = jlbShovelBack.getY() + e.getPoint().y;
					repaint();
				}
	
				public void mouseEntered(MouseEvent e) {
				}
	
				public void mouseExited(MouseEvent e) {
					press = false;
				}
			});
			jlbShovelBack.setBounds(getWidth() / 2 - 50 * resolution / 80 - 1, getHeight() - 160 * resolution / 80, 100 * resolution / 80,
					30 * resolution / 80);
			jlbShovelBack.setBorder(new LineBorder(Color.black, 1));
			add(jlbShovelBack);
	
	//      jlbShovel = new JLabel("");
	//      jlbShovel.setBounds(x_shovel, y_image, jlb_size, jlb_size);
	//      add(jlbShovel);
	//      
	//      jlbShovelText = new JLabel("땅 파기");
	//      jlbShovelText.setFont(font);
	//      jlbShovelText.setHorizontalAlignment(SwingConstants.CENTER);
	//      jlbShovelText.setBounds(x_shovel, y_text, jlb_size, jlb_size);
	//      add(jlbShovelText);
	//      
	//      jlbShovelClick = new JLabel();
	//      jlbShovelClick.addMouseListener(new MouseListener() {
	//         public void mouseClicked(MouseEvent e) {
	//         }
	//         public void mousePressed(MouseEvent e) {
	//         }
	//         public void mouseReleased(MouseEvent e) {
	//            mouseClickEffect = 0;
	//            mouseClick.x = jlbShovelClick.getX() + e.getPoint().x;
	//            mouseClick.y = jlbShovelClick.getY() + e.getPoint().y;
	//            repaint();
	//         }
	//         public void mouseEntered(MouseEvent e) {
	//         }
	//         public void mouseExited(MouseEvent e) {
	//         }
	//      });
	//      jlbShovelClick.setBounds(x_shovel - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
	//      jlbShovelClick.setBorder(new LineBorder(Color.black, 1));
	//      add(jlbShovelClick);
	//      
	//      jlbHoe = new JLabel("");
	//      jlbHoe.setBounds(x_hoe, y_image, jlb_size, jlb_size);
	//      add(jlbHoe);
	//      
	//      jlbHoeText = new JLabel("밭 갈기");
	//      jlbHoeText.setFont(font);
	//      jlbHoeText.setHorizontalAlignment(SwingConstants.CENTER);
	//      jlbHoeText.setBounds(x_hoe, y_text, jlb_size, jlb_size);
	//      add(jlbHoeText);
	//      
	//      jlbHoeClick = new JLabel();
	//      jlbHoeClick.addMouseListener(new MouseListener() {
	//         public void mouseClicked(MouseEvent e) {
	//         }
	//         public void mousePressed(MouseEvent e) {
	//         }
	//         public void mouseReleased(MouseEvent e) {
	//            mouseClickEffect = 0;
	//            mouseClick.x = jlbHoeClick.getX() + e.getPoint().x;
	//            mouseClick.y = jlbHoeClick.getY() + e.getPoint().y;
	//            repaint();
	//         }
	//         public void mouseEntered(MouseEvent e) {
	//         }
	//         public void mouseExited(MouseEvent e) {
	//         }
	//      });
	//      jlbHoeClick.setBounds(x_hoe - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
	//      jlbHoeClick.setBorder(new LineBorder(Color.black, 1));
	//      add(jlbHoeClick);
	//      
	//      //뒤로가기
	////      jlbBack = new JLabel(new ImageIcon(cropImage_back.getSubimage(0, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
	//      jlbBack = new JLabel();
	//      jlbBack.setBounds(x_back_sh, y_image, jlb_size, jlb_size);
	//      add(jlbBack);
	//      
	//      jlbBackText = new JLabel("뒤로가기");
	//      jlbBackText.setFont(font);
	//      jlbBackText.setHorizontalAlignment(SwingConstants.CENTER);
	//      jlbBackText.setBounds(x_back_sh, y_text, jlb_size, jlb_size);
	//      add(jlbBackText);
	//      
	//      jlbBackClick = new JLabel();
	//      jlbBackClick.addMouseListener(new MouseListener() {
	//         public void mouseClicked(MouseEvent e) {
	//         }
	//         public void mousePressed(MouseEvent e) {
	//            jlbBackClickPress = true;
	//         }
	//         public void mouseReleased(MouseEvent e) {
	//            if(jlbBackClickPress) {
	//               try {
	//                  paintInGame_Component();
	//               } catch (IOException e1) {
	//                  e1.printStackTrace();
	//               }
	//            }
	//            mouseClickEffect = 0;
	//            mouseClick.x = jlbBackClick.getX() + e.getPoint().x;
	//            mouseClick.y = jlbBackClick.getY() + e.getPoint().y;
	//            repaint();
	//         }
	//         public void mouseEntered(MouseEvent e) {
	//         }
	//         public void mouseExited(MouseEvent e) {
	//            jlbBackClickPress = false;
	//         }
	//      });
	//      jlbBackClick.setBounds(x_back_sh - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
	//      jlbBackClick.setBorder(new LineBorder(Color.black, 1));
	//      add(jlbBackClick);
		}

	/*
	 * 삽 종료
	 */
	
	/*
	 * 새싹 시작
	 */
	
	private void paintSproutComponent() {
		this.removeAll();
		paintStarCoinComponent();
		paintFieldComponent();
	
		// 이동 제어용 라벨
		jlbPoint = new JLabel();
		jlbPoint.setBounds(0, 0, 0, 0);
	
		// 감자
		jlbPotato = new JLabel(new ImageIcon(cropImage_potato.getSubimage(500, 0, 100, 100)
				.getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPotato.setBounds(x_potato, y_image, jlb_size, jlb_size);
		add(jlbPotato);
	
		jlbPotatoText = new JLabel("씨감자");
		jlbPotatoText.setFont(font);
		jlbPotatoText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPotatoText.setBounds(x_potato, y_text, jlb_size, jlb_size);
		add(jlbPotatoText);
	
		jlbPotatoCount = new JLabel("보유개수: " + farmData.getCrop("PotatoSeed") + "개");
		jlbPotatoCount.setFont(font_count);
		jlbPotatoCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbPotatoCount.setBounds(x_potato, y_count, jlb_size, jlb_size);
		add(jlbPotatoCount);
	
		jlbPotatoClick = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
		jlbPotatoClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbPotatoClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbPotatoClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
					setCursor("potato");
					currentCrop = 1;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbPotatoClick.getX() + e.getPoint().x;
				mouseClick.y = jlbPotatoClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbPotatoClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbPotatoClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbPotatoClick.setBounds(x_potato - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbPotatoClick);
	
		// 당근
		jlbCarrot = new JLabel(new ImageIcon(cropImage_carrot.getSubimage(500, 0, 100, 100)
				.getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCarrot.setBounds(x_carrot, y_image, jlb_size, jlb_size);
		add(jlbCarrot);
	
		jlbCarrotText = new JLabel("당근 씨앗");
		jlbCarrotText.setFont(font);
		jlbCarrotText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCarrotText.setBounds(x_carrot, y_text, jlb_size, jlb_size);
		add(jlbCarrotText);
	
		jlbCarrotCount = new JLabel("보유개수: " + farmData.getCrop("CarrotSeed") + "개");
		jlbCarrotCount.setFont(font_count);
		jlbCarrotCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbCarrotCount.setBounds(x_carrot, y_count, jlb_size, jlb_size);
		add(jlbCarrotCount);
	
		jlbCarrotClick = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
		jlbCarrotClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbCarrotClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbCarrotClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
					setCursor("carrot");
					currentCrop = 2;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbCarrotClick.getX() + e.getPoint().x;
				mouseClick.y = jlbCarrotClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbCarrotClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbCarrotClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbCarrotClick.setBounds(x_carrot - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbCarrotClick);
	
		// 비트
		jlbBeetroot = new JLabel(new ImageIcon(cropImage_beetroot.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBeetroot.setBounds(x_beetroot, y_image, jlb_size, jlb_size);
		add(jlbBeetroot);
	
		jlbBeetrootText = new JLabel("비트 씨앗");
		jlbBeetrootText.setFont(font);
		jlbBeetrootText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBeetrootText.setBounds(x_beetroot, y_text, jlb_size, jlb_size);
		add(jlbBeetrootText);
	
		jlbBeetrootCount = new JLabel("보유개수: " + farmData.getCrop("BeetrootSeed") + "개");
		jlbBeetrootCount.setFont(font_count);
		jlbBeetrootCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbBeetrootCount.setBounds(x_beetroot, y_count, jlb_size, jlb_size);
		add(jlbBeetrootCount);
	
		jlbBeetrootClick = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
		jlbBeetrootClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBeetrootClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBeetrootClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
					setCursor("beetroot");
					currentCrop = 3;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBeetrootClick.getX() + e.getPoint().x;
				mouseClick.y = jlbBeetrootClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBeetrootClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBeetrootClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbBeetrootClick.setBounds(x_beetroot - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbBeetrootClick);
	
		// 뒤로가기
		jlbBack = new JLabel(new ImageIcon(inGameImage_back.getScaledInstance(75 * resolution / 80, 75 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBack.setBounds(x_back_sp, y_image, jlb_size, jlb_size);
		add(jlbBack);
	
		jlbBackText = new JLabel("뒤로가기");
		jlbBackText.setFont(font);
		jlbBackText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBackText.setBounds(x_back_sp, y_text, jlb_size, jlb_size);
		add(jlbBackText);
	
		jlbBackClick = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
		jlbBackClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBackClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					setCursor("chicken");
					currentCrop = 0;
					try {
						paintInGameComponent();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBackClick.getX() + e.getPoint().x;
				mouseClick.y = jlbBackClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBackClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBackClick.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(jlb_click_x_size, jlb_click_y_size, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbBackClick.setBounds(x_back_sp - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbBackClick);
	
		// [0 - 마우스 x축 움직인 정도, 1 - 마우스 초기 x좌표, 2 - 컴포넌트 초기 x좌표]
		int[] currentCamera = new int[] { 0, 0, 0 };
	
		JLabel mouseControl = new JLabel();
		mouseControl.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				currentCamera[1] = e.getX();
				currentCamera[2] = jlbPoint.getX();
			}
	
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = mouseControl.getX() + e.getPoint().x;
				mouseClick.y = mouseControl.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
			}
	
			public void mouseExited(MouseEvent e) {
			}
		});
		mouseControl.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				currentCamera[0] = e.getX() - currentCamera[1];
				setComponentBoundSprout(currentCamera, e);
			}
	
			public void mouseMoved(MouseEvent e) {
			}
		});
		mouseControl.setBounds(0, getHeight() / 2 + 113 * resolution / 80, getWidth(), 220 * resolution / 80);
		add(mouseControl);
	}

	/*
	 * 새싹 종료
	 */
	
	/*
	 * 물 뿌리개 시작
	 */
	private void paintWateringCanComponent() {
		this.removeAll();
		paintStarCoinComponent();
		paintFieldComponent();
	
		JLabel jlbWateringCan = new JLabel("뒤로가기");
		jlbWateringCan.setFont(font);
		jlbWateringCan.setHorizontalAlignment(SwingConstants.CENTER);
		jlbWateringCan.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					try {
						paintInGameComponent();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
	
				mouseClickEffect = 0;
				mouseClick.x = jlbWateringCan.getX() + e.getPoint().x;
				mouseClick.y = jlbWateringCan.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
			}
	
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});
		jlbWateringCan.setBounds(getWidth() / 2 - 50 * resolution / 80 - 1, getHeight() - 160 * resolution / 80, 100,
				30);
		jlbWateringCan.setBorder(new LineBorder(Color.black, 1));
		add(jlbWateringCan);
	}

	private void paintShop() throws IOException {
		if (pa_shopComponent) {
			paintShopComponent();
			pa_shopComponent = false;
		}
	
	}

	private void paintShopComponent() throws IOException {
		this.removeAll();
		this.setLayout(null);
	
		paintStarCoinComponent();
	
		jlbShopBack = new JLabel(new ImageIcon(inGameImage_back.getScaledInstance(75 * resolution / 80, 75 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbShopBack.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					pa_shop = false;
					pa_inGame = true;
					pa_inGameComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbShopBack.getX() + e.getPoint().x;
				mouseClick.y = jlbShopBack.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
			}
	
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});
		jlbShopBack.setBorder(new LineBorder(Color.black, 1));
		jlbShopBack.setFont(font);
		jlbShopBack.setHorizontalAlignment(SwingConstants.CENTER);
		jlbShopBack.setBounds(getWidth() - 130 * resolution / 80, getHeight() - 155 * resolution / 80, 100 * resolution / 80, 100 * resolution / 80);
		add(jlbShopBack);
	
		jlbPotato = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(320 * resolution / 80, 440 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPotato.setBounds(50 * resolution / 80, 60 * resolution / 80, 320 * resolution / 80, 440 * resolution / 80);
	
		jlbPicturePotato = new JLabel(new ImageIcon(cropImage_potato.getSubimage(500, 0, 100, 100)
				.getScaledInstance(200 * resolution / 80, 200 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPicturePotato.setBounds(60 * resolution / 80, 70 * resolution / 80, 300 * resolution / 80, 300 * resolution / 80);
		add(jlbPicturePotato);
		jlbLorePotato = new JLabel("감자");
		jlbLorePotato.setFont(font);
		jlbLorePotato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLorePotato.setBounds(60 * resolution / 80, 380 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLorePotato);
		jlbLore2Potato = new JLabel("씨감자 " + farmData.getCrop("PotatoSeed") + "개, 감자 " + farmData.getCrop("Potato") + "개");
		jlbLore2Potato.setFont(font);
		jlbLore2Potato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Potato.setBounds(60 * resolution / 80, 410 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLore2Potato);
		jlbLore3Potato = new JLabel("구입가: 10G          판매가: 8G");
		jlbLore3Potato.setFont(font);
		jlbLore3Potato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Potato.setBounds(60 * resolution / 80, 440 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLore3Potato);
	
		JLabel jlbBuyPotatoText = new JLabel("씨감자 구매");
		jlbBuyPotatoText.setFont(font);
		jlbBuyPotatoText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBuyPotatoText.setBounds(60 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbBuyPotatoText);
		
		jlbBuyPotato = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBuyPotato.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
					if(farmData.getCoin() >= 10) {
						farmData.setCrop("PotatoSeed", 1, true);
						farmData.setCoin(10, false);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBuyPotato.getX() + e.getPoint().x;
				mouseClick.y = jlbBuyPotato.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBuyPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbBuyPotato.setBounds(60 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbBuyPotato);
		
		JLabel jlbSellPotatoText = new JLabel("감자 판매");
		jlbSellPotatoText.setFont(font);
		jlbSellPotatoText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbSellPotatoText.setBounds(220 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbSellPotatoText);
	
		jlbSellPotato = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbSellPotato.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
					if(farmData.getCrop("Potato") > 0) {
						farmData.setCrop("Potato", 1, false);
						farmData.setCoin(8, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellPotato.getX() + e.getPoint().x;
				mouseClick.y = jlbSellPotato.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellPotato.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbSellPotato.setBounds(220 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbSellPotato);
		
		add(jlbPotato);
		
		jlbCarrot = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(320 * resolution / 80, 440 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCarrot.setBounds(390 * resolution / 80, 60 * resolution / 80, 320 * resolution / 80, 440 * resolution / 80);
	
		jlbPictureCarrot = new JLabel(new ImageIcon(cropImage_carrot.getSubimage(500, 0, 100, 100)
				.getScaledInstance(200 * resolution / 80, 200 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPictureCarrot.setBounds(400 * resolution / 80, 70 * resolution / 80, 300 * resolution / 80, 300 * resolution / 80);
		add(jlbPictureCarrot);
		jlbLoreCarrot = new JLabel("당근");
		jlbLoreCarrot.setFont(font);
		jlbLoreCarrot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLoreCarrot.setBounds(400 * resolution / 80, 380 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLoreCarrot);
		jlbLore2Carrot = new JLabel("당근씨앗 " + farmData.getCrop("CarrotSeed") + "개, 당근 " + farmData.getCrop("Carrot") + "개");
		jlbLore2Carrot.setFont(font);
		jlbLore2Carrot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Carrot.setBounds(400 * resolution / 80, 410 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLore2Carrot);
		jlbLore3Carrot = new JLabel("구입가: 20G          판매가: 15G");
		jlbLore3Carrot.setFont(font);
		jlbLore3Carrot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Carrot.setBounds(400 * resolution / 80, 440 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLore3Carrot);
	
		JLabel jlbBuyCarrotText = new JLabel("당근씨앗 구매");
		jlbBuyCarrotText.setFont(font);
		jlbBuyCarrotText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBuyCarrotText.setBounds(400 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbBuyCarrotText);
		
		jlbBuyCarrot = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBuyCarrot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
					if(farmData.getCoin() >= 20) {
						farmData.setCrop("CarrotSeed", 1, true);
						farmData.setCoin(20, false);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBuyCarrot.getX() + e.getPoint().x;
				mouseClick.y = jlbBuyCarrot.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBuyCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbBuyCarrot.setBounds(400 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbBuyCarrot);
	
		JLabel jlbSellCarrotText = new JLabel("당근 판매");
		jlbSellCarrotText.setFont(font);
		jlbSellCarrotText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbSellCarrotText.setBounds(560 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbSellCarrotText);
		
		jlbSellCarrot = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbSellCarrot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
					if(farmData.getCrop("Carrot") > 0) {
						farmData.setCrop("Carrot", 1, false);
						farmData.setCoin(15, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellCarrot.getX() + e.getPoint().x;
				mouseClick.y = jlbSellCarrot.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellCarrot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbSellCarrot.setBounds(560 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbSellCarrot);
		
		add(jlbCarrot);
		
		jlbBeetroot = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 0, 400, 400).getScaledInstance(320 * resolution / 80, 440 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBeetroot.setBounds(730 * resolution / 80, 60 * resolution / 80, 320 * resolution / 80, 440 * resolution / 80);
	
		jlbPictureBeetroot = new JLabel(new ImageIcon(cropImage_beetroot.getSubimage(500, 0, 100, 100)
				.getScaledInstance(200 * resolution / 80, 200 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPictureBeetroot.setBounds(740 * resolution / 80, 70 * resolution / 80, 300 * resolution / 80, 300 * resolution / 80);
		add(jlbPictureBeetroot);
		jlbLoreBeetroot = new JLabel("비트");
		jlbLoreBeetroot.setFont(font);
		jlbLoreBeetroot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLoreBeetroot.setBounds(740 * resolution / 80, 380 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLoreBeetroot);
		jlbLore2Beetroot = new JLabel("비트씨앗 " + farmData.getCrop("BeetrootSeed") + "개, 비트 " + farmData.getCrop("Beetroot") + "개");
		jlbLore2Beetroot.setFont(font);
		jlbLore2Beetroot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Beetroot.setBounds(740 * resolution / 80, 410 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLore2Beetroot);
		jlbLore3Beetroot = new JLabel("구입가: 40G          판매가: 35G");
		jlbLore3Beetroot.setFont(font);
		jlbLore3Beetroot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Beetroot.setBounds(740 * resolution / 80, 440 * resolution / 80, 300 * resolution / 80, 20 * resolution / 80);
		add(jlbLore3Beetroot);
	
		JLabel jlbBuyBeetrootText = new JLabel("비트씨앗 구매");
		jlbBuyBeetrootText.setFont(font);
		jlbBuyBeetrootText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBuyBeetrootText.setBounds(740 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbBuyBeetrootText);
		
		jlbBuyBeetroot = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBuyBeetroot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
					if(farmData.getCoin() >= 40) {
						farmData.setCrop("BeetrootSeed", 1, true);
						farmData.setCoin(40, false);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBuyBeetroot.getX() + e.getPoint().x;
				mouseClick.y = jlbBuyBeetroot.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBuyBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbBuyBeetroot.setBounds(740 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbBuyBeetroot);
	
		JLabel jlbSellBeetrootText = new JLabel("비트 판매");
		jlbSellBeetrootText.setFont(font);
		jlbSellBeetrootText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbSellBeetrootText.setBounds(900 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbSellBeetrootText);
		
		jlbSellBeetroot = new JLabel(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbSellBeetroot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(800, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
					if(farmData.getCrop("Beetroot") > 0) {
						farmData.setCrop("Beetroot", 1, false);
						farmData.setCoin(35, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellBeetroot.getX() + e.getPoint().x;
				mouseClick.y = jlbSellBeetroot.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(400, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellBeetroot.setIcon(new ImageIcon(inGameImage_btn.getSubimage(0, 100, 400, 200).getScaledInstance(140 * resolution / 80, 20 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbSellBeetroot.setBounds(900 * resolution / 80, 470 * resolution / 80, 140 * resolution / 80, 20 * resolution / 80);
		add(jlbSellBeetroot);
		
		add(jlbBeetroot);
	}

	/*
	 * 물 뿌리개 종료
	 */
	
	private void paintPoultryFarm() throws IOException {
		if(pa_poultryFarmComponent) {
			paintPoultryFarmComponent();
			pa_poultryFarmComponent = false;
		}
	}

	private void paintPoultryFarmComponent() throws IOException {		
		JLabel jlbOpaque = new JLabel();
		jlbOpaque.setBackground(new Color(0, 0, 0, 125));
		jlbOpaque.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = jlbOpaque.getX() + e.getPoint().x;
				mouseClick.y = jlbOpaque.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		jlbOpaque.setOpaque(true);
		jlbOpaque.setBounds(0, 0, getWidth(), getHeight());
		add(jlbOpaque, 0);
		
		JLabel jlbPoultry = new JLabel(new ImageIcon(poultryImage_farm.getScaledInstance(500 * resolution / 80, 500 * resolution / 80, Image.SCALE_SMOOTH)));
		if(farmData.getFood() > 0) jlbPoultry.setIcon(new ImageIcon(poultryImage_farmfood.getScaledInstance(500 * resolution / 80, 500 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPoultry.setBounds(getWidth() / 2 - 250 * resolution / 80 - 1, getHeight() / 2 - 250 * resolution / 80 - 1, 500 * resolution / 80, 500 * resolution / 80);
		jlbPoultry.setBorder(new LineBorder(Color.black, 5));
		add(jlbPoultry, 0);
		
		JLabel jlbEggBackground = new JLabel(new ImageIcon(poultryImage_btn.getSubimage(0, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbEggBackground.setBounds(100 * resolution / 80, getHeight() / 2 - 250 * resolution / 80, 250 * resolution / 80, 100 * resolution / 80);
		add(jlbEggBackground, 0);
		
		JLabel jlbEgg = new JLabel("알 놓기");
		jlbEgg.setFont(font);
		jlbEgg.setHorizontalAlignment(SwingConstants.CENTER);
		jlbEgg.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbEggBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(500, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					jlbEggBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(250, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbEgg.getX() + e.getPoint().x;
				mouseClick.y = jlbEgg.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				jlbEggBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(250, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				jlbEggBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(0, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbEgg.setBounds(100 * resolution / 80, getHeight() / 2 - 250 * resolution / 80, 250 * resolution / 80, 100 * resolution / 80);
		add(jlbEgg, 0);
		
		JLabel jlbFoodBackground = new JLabel(new ImageIcon(poultryImage_btn.getSubimage(0, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbFoodBackground.setBounds(100 * resolution / 80, getHeight() / 2 - 130 * resolution / 80, 250 * resolution / 80, 100 * resolution / 80);
		add(jlbFoodBackground, 0);
		
		JLabel jlbFood = new JLabel("밥 주기");
		jlbFood.setFont(font);
		jlbFood.setHorizontalAlignment(SwingConstants.CENTER);
		jlbFood.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbFoodBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(500, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					if(farmData.getFood() == 0) {
						jlbPoultry.setIcon(new ImageIcon(poultryImage_farmfood.getScaledInstance(500 * resolution / 80, 500 * resolution / 80, Image.SCALE_SMOOTH)));
					}
					farmData.setFood(10, true);
					jlbFoodBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(250, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbFood.getX() + e.getPoint().x;
				mouseClick.y = jlbFood.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				jlbFoodBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(250, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				jlbFoodBackground.setIcon(new ImageIcon(poultryImage_btn.getSubimage(0, 0, 250, 100).getScaledInstance(250 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
				press = false;
			}
		});
		jlbFood.setBounds(100 * resolution / 80, getHeight() / 2 - 130 * resolution / 80, 250 * resolution / 80, 100 * resolution / 80);
		add(jlbFood, 0);
	
		JLabel jlbHatchery = new JLabel(new ImageIcon(poultryImage_hatchery.getScaledInstance(250 * resolution / 80, 240 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbHatchery.setBorder(new LineBorder(Color.black, 3));
		jlbHatchery.setBounds(100 * resolution / 80, getHeight() / 2 - 10 * resolution / 80, 250 * resolution / 80, 240 * resolution / 80);
		add(jlbHatchery, 0);
		
		JLabel jlbTamago = new JLabel(new ImageIcon(poultryImage_egg.getSubimage(0, 0, 100, 100).getScaledInstance(120 * resolution / 80, 120 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbTamago.setBounds(165 * resolution / 80, getHeight() / 2 + 85 * resolution / 80, 120 * resolution / 80, 120 * resolution / 80);
		add(jlbTamago, 0);
		
		JLabel jlbX = new JLabel(new ImageIcon(poultryImage_x.getScaledInstance(30 * resolution / 80, 30 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbX.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					pa_poultryFarm = false;
					pa_inGameComponent = true;
					farmData.saveData();
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbX.getX() + e.getPoint().x;
				mouseClick.y = jlbX.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});
		jlbX.setBounds(getWidth() / 2 + 235 * resolution / 80 - 1, getHeight() / 2 - 265 * resolution / 80, 30 * resolution / 80, 30 * resolution / 80);
		jlbX.setBorder(new LineBorder(Color.black, 2));
		add(jlbX, 0);
	}

	private void refreshCoin() {
		jlbStarCoinValue.setText(farmData.getCoin() + "");
		if(jlbLore2Potato != null)
			jlbLore2Potato.setText("씨감자 " + farmData.getCrop("PotatoSeed") + "개, 감자 " + farmData.getCrop("Potato") + "개");
		if(jlbLore2Carrot != null)
			jlbLore2Carrot.setText("당근씨앗 " + farmData.getCrop("CarrotSeed") + "개, 당근 " + farmData.getCrop("Carrot") + "개");
		if(jlbLore2Beetroot != null)
			jlbLore2Beetroot.setText("비트씨앗 " + farmData.getCrop("BeetrootSeed") + "개, 비트 " + farmData.getCrop("Beetroot") + "개");
	}

	public void refreshField() {
		for (int i = 0; i < jlbCropField.size(); i++) {
	        int[] fieldData = farmData.getField(i);
	        ImageIcon jlbImage = null;
	
	        if (fieldData[0] != 0) {
	            jlbImage = new ImageIcon(
	            		cropImage.get(fieldData[0]).getSubimage(100 * fieldData[1], 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH));
	        }
	
	        JLabel jlbTemp = jlbCropField.get(i);
	        jlbTemp.setIcon(jlbImage);
	
	        if (jlbTemp.getMouseListeners().length == 0) {
	            jlbTemp.addMouseListener(new FieldMouseListener(i));
	        }
	
	        JLabel jlbTextTemp = jlbCropText.get(i);
	        jlbTextTemp.setText(getCropName(fieldData[0], false, true));
	
	        JLabel jlbTimeTemp = jlbCropTime.get(i);
	        refreshTime(jlbTimeTemp, fieldData);
		}	
	}
	
	private void refreshTime(JLabel jlbTimeTemp, int[] fieldData) {
	    if (fieldData[0] != 0 && fieldData[1] != 4) {
	        int growthPercentage = (int) ((float) fieldData[2] / farmData.getCropTime(getCropName(fieldData[0], false, false), fieldData[1]) * 100);
	        jlbTimeTemp.setText(growthPercentage + "% 성장");
	    } else if (fieldData[1] == 4) {
	        jlbTimeTemp.setText("수확");
	    } else {
	        jlbTimeTemp.setText("씨앗 심기");
	    }
	}
	
	public void refreshTime() {
		if(jlbTimeText != null) {
			jlbTimeText.setText(farmData.time.hour + "시 " + farmData.time.minute + "분");
		}
	}

	private void refreshCount() {
		if (jlbPotatoCount != null) {
			jlbPotatoCount.setText("보유개수: " + farmData.getCrop("PotatoSeed") + "개");
			jlbCarrotCount.setText("보유개수: " + farmData.getCrop("CarrotSeed") + "개");
			jlbBeetrootCount.setText("보유개수: " + farmData.getCrop("BeetrootSeed") + "개");
		}
	}

	private void setValue() {
		// x
		// 삽
		count = 0;
		x_shovel = (50 + 125 * count) * resolution / 80;
		count++;
		x_hoe = (50 + 125 * count) * resolution / 80;
		count++;
		x_back_sh = (50 + 125 * count) * resolution / 80;
		// 새싹
		count = 0;
		x_potato = (50 + 125 * count) * resolution / 80;
		count++;
		x_carrot = (50 + 125 * count) * resolution / 80;
		count++;
		x_beetroot = (50 + 125 * count) * resolution / 80;
		count++;
		x_back_sp = (50 + 125 * count) * resolution / 80;
		// 걍 변수
		sprout_camera_max = (1280 - (50 * 2 + 125 * count + 100)) * resolution / 80;
		field_camera_max = (1280 - (300 + 110 * (farmData.getField().size()))) * resolution / 80;
		
		// y
		y_image = getHeight() / 2 + 130 * resolution / 80;
		y_text = getHeight() / 2 + 180 * resolution / 80;
		y_count = getHeight() / 2 + 210 * resolution / 80;
		y_click = getHeight() / 2 + 130 * resolution / 80;

		// 사이즈
		jlb_size = 100 * resolution / 80;
		jlb_click_x_size = 120 * resolution / 80;
		jlb_click_y_size = 180 * resolution / 80;
	}

	/*
	 * 삽 시작
	 */

	

	/*
	 * 삽 종료
	 */

	/*
	 * 새싹 시작
	 */

	

	/*
	 * 새싹 종료
	 */
	
	private void setCloud() {
		if(jlbPointField != null && jlbCloud1 != null && jlbCloud2 != null) {
			jlbCloud1.setBounds(jlbPointField.getX() + cloud_x, 20 * resolution / 80, 225 * resolution / 80, 100 * resolution / 80);
			jlbCloud2.setBounds(jlbPointField.getX() + cloud_x + getWidth() / 2, 100 * resolution / 80, 235 * resolution / 80, 95 * resolution / 80);
		}
	}

	// 카메라 무빙~
	private void setComponentBoundField(int[] currentCamera, MouseEvent e) {
		if(e != null) {
			if (jlbPointField.getX() > 0 || currentCamera[2] + currentCamera[0] > 0) {
				jlbPointField.setBounds(0, 0, 0, 0);
				currentCamera[1] = e.getX();
				currentCamera[2] = 0;
			} else if (jlbPointField.getX() < field_camera_max || currentCamera[2] + currentCamera[0] < field_camera_max) {
				if (field_camera_max > 0) {
					jlbPointField.setBounds(0, 0, 0, 0);
					currentCamera[1] = e.getX();
					currentCamera[2] = 0;
				} else {
					jlbPointField.setBounds(field_camera_max, 0, 0, 0);
					currentCamera[1] = e.getX();
					currentCamera[2] = field_camera_max;
				}
			} else {
				jlbPointField.setBounds(currentCamera[2] + currentCamera[0], 0, 0, 0);
			}
		}
		int x = jlbPointField.getX();
	
		jlbChickenHouse.setBounds(x + 250 * resolution / 80, getHeight() / 2 - 165 * resolution / 80, 168 * resolution / 80, 98 * resolution / 80);
		jlbShop.setBounds(x + (50) * resolution / 80, (getHeight() - 100) / 2 - 70 * resolution / 80, 200 * resolution / 80, 200 * resolution / 80);
		setCloud();
		for (int i = 0; i < jlbCropField.size(); i++) {
			jlbCropField.get(i).setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 - 60 * resolution / 80, 90 * resolution / 80, 90 * resolution / 80);
			jlbCropText.get(i).setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 + 35 * resolution / 80, 90 * resolution / 80, 20 * resolution / 80);
			jlbCropTime.get(i).setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 + 60 * resolution / 80, 90 * resolution / 80, 20 * resolution / 80);
			jlbBat.get(i).setBounds(x + (280 + 110 * i) * resolution / 80, getHeight() / 2 - 60 * resolution / 80, 90 * resolution / 80, 90 * resolution / 80);
		}
	}

	// 카메라 무빙~
	private void setComponentBoundSprout(int[] currentCamera, MouseEvent e) {
		if (jlbPoint.getX() > 0 || currentCamera[2] + currentCamera[0] > 0) {
			jlbPoint.setBounds(0, 0, 0, 0);
			currentCamera[1] = e.getX();
			currentCamera[2] = 0;
		} else if (jlbPoint.getX() < sprout_camera_max || currentCamera[2] + currentCamera[0] < sprout_camera_max) {
			if (sprout_camera_max > 0) {
				jlbPoint.setBounds(0, 0, 0, 0);
				currentCamera[1] = e.getX();
				currentCamera[2] = 0;
			} else {
				jlbPoint.setBounds(sprout_camera_max, 0, 0, 0);
				currentCamera[1] = e.getX();
				currentCamera[2] = sprout_camera_max;
			}
		} else {
			jlbPoint.setBounds(currentCamera[2] + currentCamera[0], 0, 0, 0);
		}

		int x = jlbPoint.getX();

		jlbPotato.setBounds(x + x_potato, y_image, jlb_size, jlb_size);
		jlbPotatoText.setBounds(x + x_potato, y_text, jlb_size, jlb_size);
		jlbPotatoCount.setBounds(x + x_potato, y_count, jlb_size, jlb_size);
		jlbPotatoClick.setBounds(x + x_potato - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbCarrot.setBounds(x + x_carrot, y_image, jlb_size, jlb_size);
		jlbCarrotText.setBounds(x + x_carrot, y_text, jlb_size, jlb_size);
		jlbCarrotCount.setBounds(x + x_carrot, y_count, jlb_size, jlb_size);
		jlbBeetroot.setBounds(x + x_beetroot, y_image, jlb_size, jlb_size);
		jlbBeetrootText.setBounds(x + x_beetroot, y_text, jlb_size, jlb_size);
		jlbBeetrootCount.setBounds(x + x_beetroot, y_count, jlb_size, jlb_size);

		jlbBack.setBounds(x + x_back_sp, y_image, jlb_size, jlb_size);
		jlbBackText.setBounds(x + x_back_sp, y_text, jlb_size, jlb_size);
		jlbBackClick.setBounds(x + x_back_sp - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
	}

	/*
	 * 물 뿌리개 종료
	 */
	
	private void setCursor(String key) {
		this.setCursor(cursor.get(key));
	}

	public String getCropName(int id, boolean seed, boolean kor) {
		if (kor) {
			switch (id) {
			case 0:
				return "밭";
			case 1:
				return "감자";
			case 2:
				return "당근";
			case 3:
				return "비트";
			}
		}
		if (seed) {
			switch (id) {
			case 1:
				return "PotatoSeed";
			case 2:
				return "CarrotSeed";
			case 3:
				return "BeetrootSeed";
			}
		}
		switch (id) {
		case 0:
			return "Bat";
		case 1:
			return "Potato";
		case 2:
			return "Carrot";
		case 3:
			return "Beetroot";
		}
		return "";
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		mouseClickEffect = 0;
		mouseClick.x = e.getPoint().x;
		mouseClick.y = e.getPoint().y;
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		repaint();
	}

	public void mouseExited(MouseEvent e) {
	}

	private class FieldMouseListener implements MouseListener {
	    private final int index;
	    boolean press = false;
	
	    public FieldMouseListener(int index) {
	        this.index = index;
	    }
	    
		public void mouseClicked(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			press = true;
		}
	    public void mouseReleased(MouseEvent e) {
	    	if(press) {
		        int[] fieldData = farmData.getField(index);
	
		        if (fieldData[1] == 4 && fieldData[0] != 0) {
		            harvestCrop(index);
		        } else if (fieldData[0] == 0) {
		            plantCrop(index);
		        }
	
		        mouseClickEffect = 0;
		        mouseClick.x = jlbCropField.get(index).getX() + e.getPoint().x;
		        mouseClick.y = jlbCropField.get(index).getY() + e.getPoint().y;
		        repaint();
	    	}
	    }
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
			press = false;
		}
	    
	    private void harvestCrop(int index) {
	        farmData.setCrop(getCropName(farmData.getField(index)[0], false, false), 2, true);
	        farmData.setField(index, new int[]{0, 0, 0, 0});
	        refreshCount();
	        refreshField();
	        jlbCropField.get(index).removeMouseListener(this);
	    }
	
	    private void plantCrop(int index) {
	        if (currentCrop != 0 && farmData.getCrop(getCropName(currentCrop, true, false)) > 0) {
	            farmData.setCrop(getCropName(currentCrop, true, false), 1, false);
	            System.out.println("씨앗 소모");
	            farmData.setField(index, new int[]{currentCrop, 0, 0, 0});
	            refreshCount();
	            refreshField();
	            jlbCropField.get(index).removeMouseListener(this);
	        }
	    }
	}
}