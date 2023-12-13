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
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FarmCanvas extends JPanel implements Runnable, MouseListener {
	protected MyLauncher myLauncher;
	protected FarmData farmData = new FarmData(this);

	protected Font font, font_count;

	// cursor
	Map<String, Cursor> cursor = new HashMap<>();
	
	protected Cursor customCursor_chicken;
	protected Cursor customCursor_wateringcan;
	protected Cursor customCursor_potato;
	protected Cursor customCursor_carrot;
	protected Cursor customCursor_beetroot;
	protected Cursor customCursor_sweetpotato;
	protected Cursor customCursor_daikon;

	protected final String cursor_chicken = "/resource/cursor/cursor_chicken.png";
	protected final String cursor_wateringcan = "/resource/cursor/cursor_wateringcan.png";
	protected final String cursor_potato = "/resource/cursor/cursor_potato.png";
	protected final String cursor_carrot = "/resource/cursor/cursor_carrot.png";
	protected final String cursor_beetroot = "/resource/cursor/cursor_beetroot.png";
	protected final String cursor_sweetpotato = "/resource/cursor/cursor_sweetpotato.png";
	protected final String cursor_daikon = "/resource/cursor/cursor_daikon.png";
	protected final String mainLobby_background = "/resource/mainLobby/background.png";
	protected final String mainLobby_chicken = "/resource/mainLobby/chicken.png";
	protected final String mainLobby_singsing = "/resource/mainLobby/singsing.png";
	protected final String mainLobby_buttonstart = "/resource/mainLobby/buttonstart.png";
	protected final String mainLobby_buttondesc = "/resource/mainLobby/buttondesc.png";
	protected final String mainLobby_buttonsett = "/resource/mainLobby/buttonsett.png";
	protected final String mainLobby_cloud = "/resource/mainLobby/cloud.png";
	protected final String inGame_starcoin = "/resource/inGame/starcoin.png";
	protected final String inGame_shovel = "/resource/inGame/shovel.png";
	protected final String inGame_hoe = "/resource/inGame/hoe.png";
	protected final String inGame_sprout = "/resource/inGame/sprout.png";
	protected final String inGame_wateringcan = "/resource/inGame/wateringcan.png";
	protected final String inGame_apple = "/resource/inGame/apple.png";
	protected final String inGame_fertilizer = "/resource/inGame/fertilizer.png";
	protected final String inGame_fertilizerset = "/resource/inGame/fertilizerset.png";
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
	protected final String crop_sweetpotato = "/resource/inGame/crop/sweetpotato.png";
	protected final String crop_daikon = "/resource/inGame/crop/daikon.png";
	protected final String poultry_farm = "/resource/inGame/poultry/poultryfarm.png";
	protected final String poultry_farmfood = "/resource/inGame/poultry/poultryfarmfood.png";
	protected final String poultry_x = "/resource/inGame/poultry/x.png";
	protected final String poultry_btn = "/resource/inGame/poultry/btn.png";
	protected final String poultry_egg = "/resource/inGame/poultry/egg.png";
	protected final String poultry_chicken = "/resource/inGame/poultry/chicken.png";
	protected final String poultry_hatchery = "/resource/inGame/poultry/hatchery.png";
	protected final String shop_item = "/resource/inGame/shop/item.png";
	protected final String shop_btnbuy = "/resource/inGame/shop/btnbuy.png";
	protected final String shop_btnsell = "/resource/inGame/shop/btnsell.png";
	
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
	protected BufferedImage cursorImage_wateringcan;
	protected BufferedImage cursorImage_potato;
	protected BufferedImage cursorImage_carrot;
	protected BufferedImage cursorImage_beetroot;
	protected BufferedImage cursorImage_sweetpotato;
	protected BufferedImage cursorImage_daikon;
	protected BufferedImage mainLobbyImage_buttonstart;
	protected BufferedImage mainLobbyImage_buttondesc;
	protected BufferedImage mainLobbyImage_buttonsett;
	protected BufferedImage mainLobbyImage_cloud;
	protected BufferedImage	inGameImage_shovel;
	protected BufferedImage inGameImage_hoe;
	protected BufferedImage inGameImage_sprout;
	protected BufferedImage inGameImage_wateringcan;
	protected BufferedImage inGameImage_apple;
	protected BufferedImage inGameImage_fertilizer;
	protected BufferedImage inGameImage_fertilizerset;
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
	protected BufferedImage cropImage_sweetpotato;
	protected BufferedImage cropImage_daikon;
	protected BufferedImage poultryImage_farm;
	protected BufferedImage poultryImage_farmfood;
	protected BufferedImage poultryImage_x;
	protected BufferedImage poultryImage_btn;
	protected BufferedImage poultryImage_egg;
	protected BufferedImage poultryImage_chicken;
	protected BufferedImage poultryImage_hatchery;
	protected BufferedImage shopImage_item;
	protected BufferedImage shopImage_btnbuy;
	protected BufferedImage shopImage_btnsell;
	
	protected Image[] mainLobbyImage_logo;
	protected Image[] mainLobbyImage_cloud1;
	protected Image[] mainLobbyImage_cloud2;

	protected ImageIcon inGameImageIcon_btn_normal;
	protected ImageIcon inGameImageIcon_btn_overlap;
	protected ImageIcon inGameImageIcon_btn_pressed;
	protected ImageIcon shopImageIcon_item;
	protected ImageIcon shopImageIcon_btnbuy_normal;
	protected ImageIcon shopImageIcon_btnbuy_overlap;
	protected ImageIcon shopImageIcon_btnbuy_pressed;
	protected ImageIcon shopImageIcon_btnsell_normal;
	protected ImageIcon shopImageIcon_btnsell_overlap;
	protected ImageIcon shopImageIcon_btnsell_pressed;
	
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
	protected boolean pa_inGame = false;
	protected boolean pa_bottomInfo = false;
	protected boolean pa_shop = false;
	protected boolean pa_poultryFarm = false;

	// paint component
	protected boolean pa_mainLobbyComponent = true;
	protected boolean pa_inGameComponent = false;
	protected boolean pa_shopSeedComponent = false;
	protected boolean pa_shopEggComponent = false;
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
	JLabel jlbPictureSweetPotato, jlbLoreSweetPotato, jlbLore2SweetPotato, jlbLore3SweetPotato, jlbBuySweetPotato, jlbSellSweetPotato;
	JLabel jlbPictureDaikon, jlbLoreDaikon, jlbLore2Daikon, jlbLore3Daikon, jlbBuyDaikon, jlbSellDaikon;
	
	JLabel jlbEgg, jlbPictureEgg, jlbLoreEgg, jlbLore2Egg, jlbLore3Egg, jlbBuyEgg, jlbSellEgg;
	
	ArrayList<JLabel> jlbCropField, jlbCropText, jlbCropTime, jlbBat;
	Map<Integer, BufferedImage> cropImage;

	// 기준점
	JLabel jlbPoint, jlbPointField;
	// 식물
	JLabel jlbPotato, jlbPotatoText, jlbPotatoCount, jlbPotatoClick;
	JLabel jlbCarrot, jlbCarrotText, jlbCarrotCount, jlbCarrotClick;
	JLabel jlbBeetroot, jlbBeetrootText, jlbBeetrootCount, jlbBeetrootClick;
	JLabel jlbSweetPotato, jlbSweetPotatoText, jlbSweetPotatoCount, jlbSweetPotatoClick;
	JLabel jlbDaikon, jlbDaikonText, jlbDaikonCount, jlbDaikonClick;
	JLabel jlbSproutBack, jlbSproutBackText, jlbSproutBackClick;
	// 삽
	JLabel jlbHoeImage, jlbHoeText, jlbHoeCost, jlbHoeClick;
	JLabel jlbShovelBack, jlbShovelBackText, jlbShovelBackClick;
	// 물뿌리개
	JLabel jlbWateringCanBack, jlbWateringCanBackText, jlbWateringCanBackClick;
	// 비료
	JLabel jlbCheapFertilizerImage, jlbCheapFertilizerText, jlbCheapFertilizerCost, jlbCheapFertilizerClick;
	JLabel jlbNormalFertilizerImage, jlbNormalFertilizerText, jlbNormalFertilizerCost, jlbNormalFertilizerClick;
	JLabel jlbAdvancedFertilizerImage, jlbAdvancedFertilizerText, jlbAdvancedFertilizerCost, jlbAdvancedFertilizerClick;
	JLabel jlbPremiumFertilizerImage, jlbPremiumFertilizerText, jlbPremiumFertilizerCost, jlbPremiumFertilizerClick;
	JLabel jlbFertilizerBack, jlbFertilizerBackText, jlbFertilizerBackClick;

	JLabel jlbTamago;
	
	int x_back_sh, x_back_sp, x_back_fe;
	int y_image, y_text, y_count, y_click;
	int x_potato, x_carrot, x_beetroot, x_sweetpotato, x_daikon;
	int x_shovel, x_hoe;
	int x_cheap, x_normal, x_advanced, x_premium;
	int jlb_size, jlb_click_x_size, jlb_click_y_size;
	int count, sprout_camera_max, shovel_camera_max, fertilizer_camera_max, field_camera_max;
	int currentCrop = 0;
	int cloud_x = -getWidth();
	
	int shop_camera_max;
	int x_shop_1, x_shop_1_image, x_shop_1_btnbuy, x_shop_1_btnsell;
	int x_shop_2, x_shop_2_image, x_shop_2_btnbuy, x_shop_2_btnsell;
	int x_shop_3, x_shop_3_image, x_shop_3_btnbuy, x_shop_3_btnsell;
	int x_shop_4, x_shop_4_image, x_shop_4_btnbuy, x_shop_4_btnsell;
	int x_shop_5, x_shop_5_image, x_shop_5_btnbuy, x_shop_5_btnsell;
	
	int y_shop_item, y_shop_image, y_shop_lore, y_shop_lore2, y_shop_lore3, y_shop_btn;
	
	int size_shop_x, size_shop_item_y, size_shop_image, size_shop_lore_y;
	int size_shop_btn_x, size_shop_btn_y;

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
							if (temp[0] != 0 && temp[3] > 0) {
								temp[2]++;
								temp[3]--;
								farmData.setField(i, temp);
								farmData.checkField(i);
							}
						}
						farmData.egg.addTime();
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
		cursorImage_wateringcan = ImageIO.read(getClass().getResourceAsStream(cursor_wateringcan));
		
		cursorImage_potato = ImageIO.read(getClass().getResourceAsStream(cursor_potato));
		cursorImage_carrot = ImageIO.read(getClass().getResourceAsStream(cursor_carrot));
		cursorImage_beetroot = ImageIO.read(getClass().getResourceAsStream(cursor_beetroot));
		cursorImage_sweetpotato = ImageIO.read(getClass().getResourceAsStream(cursor_sweetpotato));
		cursorImage_daikon = ImageIO.read(getClass().getResourceAsStream(cursor_daikon));
		
		customCursor_chicken = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_chicken, new Point(0, 0),
				"Custom Cursor");
		customCursor_wateringcan = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_wateringcan, new Point(0, 0),
				"Custom Cursor");
		
		customCursor_potato = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_potato, new Point(0, 0),
				"Custom Cursor");
		customCursor_carrot = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_carrot, new Point(0, 0),
				"Custom Cursor");
		customCursor_beetroot = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_beetroot, new Point(0, 0),
				"Custom Cursor");
		customCursor_sweetpotato = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_sweetpotato, new Point(0, 0),
				"Custom Cursor");
		customCursor_daikon = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_daikon, new Point(0, 0),
				"Custom Cursor");

		cursor.put("chicken", customCursor_chicken);
		cursor.put("wateringcan", customCursor_wateringcan);
		
		cursor.put("potato", customCursor_potato);
		cursor.put("carrot", customCursor_carrot);
		cursor.put("beetroot", customCursor_beetroot);
		cursor.put("sweetpotato", customCursor_sweetpotato);
		cursor.put("daikon", customCursor_daikon);
		
		inGameImage_shovel = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_shovel)));

		cropImage_potato = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_potato)));
		cropImage_carrot = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_carrot)));
		cropImage_beetroot = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_beetroot)));
		cropImage_sweetpotato = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_sweetpotato)));
		cropImage_daikon = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_daikon)));
		
		poultryImage_x = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_x)));
		poultryImage_egg = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_egg)));
	}

	private void loadingInGame() throws IOException {
		inGameImage_bat = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_bat)));
		inGameImage_btn = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_btn)));
		inGameImage_background = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background)));
		inGameImage_background_dawn = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background_dawn)));
		inGameImage_background_sunset = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background_sunset)));
		inGameImage_background_evening = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background_evening)));
		inGameImage_starcoin = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_starcoin)));
		inGameImage_hoe = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_hoe)));
		inGameImage_sprout = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_sprout)));
		inGameImage_wateringcan = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_wateringcan)));
		inGameImage_fertilizer = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_fertilizer)));
		inGameImage_fertilizerset = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_fertilizerset)));
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
		cropImage.put(4, cropImage_sweetpotato);
		cropImage.put(5, cropImage_daikon);
		
		jlbPointField = new JLabel();
		jlbPointField.setBounds(0, 0, 0, 0);
		
		poultryImage_farm = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_farm)));
		poultryImage_farmfood = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_farmfood)));
		poultryImage_btn = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_btn)));
		poultryImage_chicken = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_chicken)));
		poultryImage_hatchery = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(poultry_hatchery)));
	
		shopImage_item = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(shop_item)));
		shopImage_btnbuy = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(shop_btnbuy)));
		shopImage_btnsell = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(shop_btnsell)));
		
		inGameImageIcon_btn_normal = new ImageIcon(inGameImage_btn.getSubimage(0, 0, 120, 180).getScaledInstance(120 * resolution / 80, 180 * resolution / 80, Image.SCALE_SMOOTH));
		inGameImageIcon_btn_overlap = new ImageIcon(inGameImage_btn.getSubimage(120, 0, 120, 180).getScaledInstance(120 * resolution / 80, 180 * resolution / 80, Image.SCALE_SMOOTH));
		inGameImageIcon_btn_pressed = new ImageIcon(inGameImage_btn.getSubimage(240, 0, 120, 180).getScaledInstance(120 * resolution / 80, 180 * resolution / 80, Image.SCALE_SMOOTH));
		
		shopImageIcon_item = new ImageIcon(shopImage_item.getScaledInstance(250 * resolution / 80, 300 * resolution / 80, Image.SCALE_SMOOTH));
		
		shopImageIcon_btnbuy_normal = new ImageIcon(shopImage_btnbuy.getSubimage(0, 0, 220, 100).getScaledInstance(110 * resolution / 80, 50 * resolution / 80, Image.SCALE_SMOOTH));
		shopImageIcon_btnbuy_overlap = new ImageIcon(shopImage_btnbuy.getSubimage(220, 0, 220, 100).getScaledInstance(110 * resolution / 80, 50 * resolution / 80, Image.SCALE_SMOOTH));
		shopImageIcon_btnbuy_pressed = new ImageIcon(shopImage_btnbuy.getSubimage(440, 0, 220, 100).getScaledInstance(110 * resolution / 80, 50 * resolution / 80, Image.SCALE_SMOOTH));
		shopImageIcon_btnsell_normal = new ImageIcon(shopImage_btnsell.getSubimage(0, 0, 220, 100).getScaledInstance(110 * resolution / 80, 50 * resolution / 80, Image.SCALE_SMOOTH));
		shopImageIcon_btnsell_overlap = new ImageIcon(shopImage_btnsell.getSubimage(220, 0, 220, 100).getScaledInstance(110 * resolution / 80, 50 * resolution / 80, Image.SCALE_SMOOTH));
		shopImageIcon_btnsell_pressed = new ImageIcon(shopImage_btnsell.getSubimage(440, 0, 220, 100).getScaledInstance(110 * resolution / 80, 50 * resolution / 80, Image.SCALE_SMOOTH));
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
		}

	private void paintMainLobbyComponent() throws IOException {
		this.removeAll();
		this.setLayout(null);
		setCursor("chicken");

		Point btnSize = new Point();
		btnSize.x = 250;
		btnSize.y = 80;

		JLabel btnStart = new JLabel(new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 0, 352, 112)
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
		add(btnStart);

		JLabel btnDesc = new JLabel(new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 0, 352, 112)
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
					paintGameDescComponent();
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
		add(btnDesc);

		JLabel btnSett = new JLabel(new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 0, 352, 112)
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
					paintGameSettComponent();
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
		add(btnSett);
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
	
	private void paintGameSettComponent() {
		JLabel jlbBack = new JLabel();
		JLabel jlbPanel = new JLabel();
		JLabel jlbBackBtn = new JLabel(new ImageIcon(poultryImage_x.getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		
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
		jlbPanel.setBounds(getWidth() / 2 - 201 * resolution / 80, 100 * resolution / 80, 400 * resolution / 80, 500 * resolution / 80);
		add(jlbPanel, 0);
		
		jlbBackBtn.setBorder(new LineBorder(Color.black, 3));
		jlbBackBtn.setBounds(getWidth() /2 + 174 * resolution / 80, 76 * resolution / 80, 50 * resolution / 80, 50 * resolution / 80);
		add(jlbBackBtn, 0);
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
	}

	private void paintInGameComponent() throws IOException {
		this.removeAll();
		this.setLayout(null);

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
					setCursor("wateringcan");
					currentCrop = -2;
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
					paintFertilizerComponent();
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
					paintAppleComponent();
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
					setCursor("chicken");
					currentCrop = 0;
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
					pa_shopSeedComponent = true;
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

	private void paintShovelComponent() {
		this.removeAll();
		paintStarCoinComponent();
		paintFieldComponent();

		jlbHoeImage = new JLabel(new ImageIcon(inGameImage_hoe.getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbHoeImage.setBounds(x_hoe, y_image, jlb_size, jlb_size);
		add(jlbHoeImage);
		
		jlbHoeText = new JLabel("밭 갈기");
		jlbHoeText.setFont(font);
		jlbHoeText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbHoeText.setBounds(x_hoe, y_text, jlb_size, jlb_size);
		add(jlbHoeText);
		
		jlbHoeCost = new JLabel("100G 소모");
		jlbHoeCost.setFont(font_count);
		jlbHoeCost.setHorizontalAlignment(SwingConstants.CENTER);
		jlbHoeCost.setBounds(x_hoe, y_count, jlb_size, jlb_size);
		add(jlbHoeCost);
		
		jlbHoeClick = new JLabel(inGameImageIcon_btn_normal);
		jlbHoeClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbHoeClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbHoeClick.setIcon(inGameImageIcon_btn_overlap);
					if(farmData.getCoin() >= 100) {
						farmData.addField();
						farmData.setCoin(100, false);
						refreshCoin();
						try {
							loadingField();
							refreshField();
							paintShovelComponent();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbHoeClick.getX() + e.getPoint().x;
				mouseClick.y = jlbHoeClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				jlbHoeClick.setIcon(inGameImageIcon_btn_overlap);
			}
			public void mouseExited(MouseEvent e) {
				jlbHoeClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbHoeClick.setBounds(x_hoe - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbHoeClick);

		jlbShovelBack = new JLabel(new ImageIcon(inGameImage_back.getScaledInstance(75 * resolution / 80, 75 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbShovelBack.setBounds(x_back_sh, y_image, jlb_size, jlb_size);
		add(jlbShovelBack);
	
		jlbShovelBackText = new JLabel("뒤로가기");
		jlbShovelBackText.setFont(font);
		jlbShovelBackText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbShovelBackText.setBounds(x_back_sh, y_text, jlb_size, jlb_size);
		add(jlbShovelBackText);
	
		jlbShovelBackClick = new JLabel(inGameImageIcon_btn_normal);
		jlbShovelBackClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				jlbShovelBackClick.setIcon(inGameImageIcon_btn_pressed);
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
				mouseClick.x = jlbShovelBackClick.getX() + e.getPoint().x;
				mouseClick.y = jlbShovelBackClick.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				jlbShovelBackClick.setIcon(inGameImageIcon_btn_overlap);
			}

			public void mouseExited(MouseEvent e) {
				jlbShovelBackClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbShovelBackClick.setBounds(x_back_sh - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbShovelBackClick);
		
		// 이동 제어용 라벨
		jlbPoint = new JLabel();
		jlbPoint.setBounds(0, 0, 0, 0);
		
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
				setComponentBoundShovel(currentCamera, e);
			}
	
			public void mouseMoved(MouseEvent e) {
			}
		});
		mouseControl.setBounds(0, getHeight() / 2 + 113 * resolution / 80, getWidth(), 220 * resolution / 80);
		add(mouseControl);
	}

	private void paintSproutComponent() {
		this.removeAll();
		paintStarCoinComponent();
		paintFieldComponent();
	
		// 감자
		jlbPotato = new JLabel(new ImageIcon(cropImage_potato.getSubimage(500, 0, 100, 100).getScaledInstance(jlb_size, jlb_size, Image.SCALE_SMOOTH)));
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
	
		jlbPotatoClick = new JLabel(inGameImageIcon_btn_normal);
		jlbPotatoClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbPotatoClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbPotatoClick.setIcon(inGameImageIcon_btn_overlap);
					setCursor("potato");
					currentCrop = 1;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbPotatoClick.getX() + e.getPoint().x;
				mouseClick.y = jlbPotatoClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbPotatoClick.setIcon(inGameImageIcon_btn_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbPotatoClick.setIcon(inGameImageIcon_btn_normal);
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
	
		jlbCarrotClick = new JLabel(inGameImageIcon_btn_normal);
		jlbCarrotClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbCarrotClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbCarrotClick.setIcon(inGameImageIcon_btn_overlap);
					setCursor("carrot");
					currentCrop = 2;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbCarrotClick.getX() + e.getPoint().x;
				mouseClick.y = jlbCarrotClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbCarrotClick.setIcon(inGameImageIcon_btn_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbCarrotClick.setIcon(inGameImageIcon_btn_normal);
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
	
		jlbBeetrootClick = new JLabel(inGameImageIcon_btn_normal);
		jlbBeetrootClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBeetrootClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBeetrootClick.setIcon(inGameImageIcon_btn_overlap);
					setCursor("beetroot");
					currentCrop = 3;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBeetrootClick.getX() + e.getPoint().x;
				mouseClick.y = jlbBeetrootClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBeetrootClick.setIcon(inGameImageIcon_btn_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBeetrootClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbBeetrootClick.setBounds(x_beetroot - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbBeetrootClick);
		
		// 고구마
		jlbSweetPotato = new JLabel(new ImageIcon(cropImage_sweetpotato.getSubimage(500, 0, 100, 100).getScaledInstance(jlb_size, jlb_size, Image.SCALE_SMOOTH)));
		jlbSweetPotato.setBounds(x_sweetpotato, y_image, jlb_size, jlb_size);
		add(jlbSweetPotato);
	
		jlbSweetPotatoText = new JLabel("씨고구마");
		jlbSweetPotatoText.setFont(font);
		jlbSweetPotatoText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbSweetPotatoText.setBounds(x_sweetpotato, y_text, jlb_size, jlb_size);
		add(jlbSweetPotatoText);
	
		jlbSweetPotatoCount = new JLabel("보유개수: " + farmData.getCrop("SweetPotatoSeed") + "개");
		jlbSweetPotatoCount.setFont(font_count);
		jlbSweetPotatoCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbSweetPotatoCount.setBounds(x_sweetpotato, y_count, jlb_size, jlb_size);
		add(jlbSweetPotatoCount);
	
		jlbSweetPotatoClick = new JLabel(inGameImageIcon_btn_normal);
		jlbSweetPotatoClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSweetPotatoClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSweetPotatoClick.setIcon(inGameImageIcon_btn_overlap);
					setCursor("sweetpotato");
					currentCrop = 4;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSweetPotatoClick.getX() + e.getPoint().x;
				mouseClick.y = jlbSweetPotatoClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSweetPotatoClick.setIcon(inGameImageIcon_btn_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSweetPotatoClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbSweetPotatoClick.setBounds(x_sweetpotato - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbSweetPotatoClick);
	
		// 무
		jlbDaikon = new JLabel(new ImageIcon(cropImage_daikon.getSubimage(500, 0, 100, 100).getScaledInstance(jlb_size, jlb_size, Image.SCALE_SMOOTH)));
		jlbDaikon.setBounds(x_daikon, y_image, jlb_size, jlb_size);
		add(jlbDaikon);
	
		jlbDaikonText = new JLabel("무 씨앗");
		jlbDaikonText.setFont(font);
		jlbDaikonText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbDaikonText.setBounds(x_daikon, y_text, jlb_size, jlb_size);
		add(jlbDaikonText);
	
		jlbDaikonCount = new JLabel("보유개수: " + farmData.getCrop("DaikonSeed") + "개");
		jlbDaikonCount.setFont(font_count);
		jlbDaikonCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbDaikonCount.setBounds(x_daikon, y_count, jlb_size, jlb_size);
		add(jlbDaikonCount);
	
		jlbDaikonClick = new JLabel(inGameImageIcon_btn_normal);
		jlbDaikonClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbDaikonClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbDaikonClick.setIcon(inGameImageIcon_btn_overlap);
					setCursor("daikon");
					currentCrop = 5;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbPotatoClick.getX() + e.getPoint().x;
				mouseClick.y = jlbPotatoClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbDaikonClick.setIcon(inGameImageIcon_btn_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbDaikonClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbDaikonClick.setBounds(x_daikon - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbDaikonClick);
		
		// 뒤로가기
		jlbSproutBack = new JLabel(new ImageIcon(inGameImage_back.getScaledInstance(75 * resolution / 80, 75 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbSproutBack.setBounds(x_back_sp, y_image, jlb_size, jlb_size);
		add(jlbSproutBack);
	
		jlbSproutBackText = new JLabel("뒤로가기");
		jlbSproutBackText.setFont(font);
		jlbSproutBackText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbSproutBackText.setBounds(x_back_sp, y_text, jlb_size, jlb_size);
		add(jlbSproutBackText);
	
		jlbSproutBackClick = new JLabel(inGameImageIcon_btn_normal);
		jlbSproutBackClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSproutBackClick.setIcon(inGameImageIcon_btn_pressed);
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
				mouseClick.x = jlbSproutBackClick.getX() + e.getPoint().x;
				mouseClick.y = jlbSproutBackClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSproutBackClick.setIcon(inGameImageIcon_btn_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSproutBackClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbSproutBackClick.setBounds(x_back_sp - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbSproutBackClick);
	
		// 이동 제어용 라벨
		jlbPoint = new JLabel();
		jlbPoint.setBounds(0, 0, 0, 0);
		
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

	private void paintWateringCanComponent() {
		this.removeAll();
		paintStarCoinComponent();
		paintFieldComponent();
	
		jlbWateringCanBack = new JLabel(new ImageIcon(inGameImage_back.getScaledInstance(75 * resolution / 80, 75 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbWateringCanBack.setBounds(getWidth() / 2 - jlb_size / 2 - 1, y_image, jlb_size, jlb_size);
		add(jlbWateringCanBack);
	
		jlbWateringCanBackText = new JLabel("뒤로가기");
		jlbWateringCanBackText.setFont(font);
		jlbWateringCanBackText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbWateringCanBackText.setBounds(getWidth() / 2 - jlb_size / 2 - 1, y_text, jlb_size, jlb_size);
		add(jlbWateringCanBackText);
	
		jlbWateringCanBackClick = new JLabel(inGameImageIcon_btn_normal);
		jlbWateringCanBackClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbWateringCanBackClick.setIcon(inGameImageIcon_btn_pressed);
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
				mouseClick.x = jlbWateringCanBackClick.getX() + e.getPoint().x;
				mouseClick.y = jlbWateringCanBackClick.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbWateringCanBackClick.setIcon(inGameImageIcon_btn_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbWateringCanBackClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbWateringCanBackClick.setBounds(getWidth() / 2 - jlb_size / 2 - 1 - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbWateringCanBackClick);
	}
	private void paintFertilizerComponent() {
		this.removeAll();
		paintStarCoinComponent();
		paintFieldComponent();

		jlbCheapFertilizerImage = new JLabel(new ImageIcon(inGameImage_fertilizerset.getSubimage(0, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCheapFertilizerImage.setBounds(x_cheap, y_image, jlb_size, jlb_size);
		add(jlbCheapFertilizerImage);
		
		jlbCheapFertilizerText = new JLabel("싸구려 비료");
		jlbCheapFertilizerText.setFont(font);
		jlbCheapFertilizerText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCheapFertilizerText.setBounds(x_cheap, y_text, jlb_size, jlb_size);
		add(jlbCheapFertilizerText);
		
		jlbCheapFertilizerCost = new JLabel("5G 소모");
		jlbCheapFertilizerCost.setFont(font_count);
		jlbCheapFertilizerCost.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCheapFertilizerCost.setBounds(x_cheap, y_count, jlb_size, jlb_size);
		add(jlbCheapFertilizerCost);
		
		jlbCheapFertilizerClick = new JLabel(inGameImageIcon_btn_normal);
		jlbCheapFertilizerClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbCheapFertilizerClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if (press) {
					setCursor("cheapfertilizer");
					currentCrop = -3;
					jlbCheapFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbCheapFertilizerClick.getX() + e.getPoint().x;
				mouseClick.y = jlbCheapFertilizerClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				jlbCheapFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
			}
			public void mouseExited(MouseEvent e) {
				jlbCheapFertilizerClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbCheapFertilizerClick.setBounds(x_cheap - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbCheapFertilizerClick);
		
		jlbNormalFertilizerImage = new JLabel(new ImageIcon(inGameImage_fertilizerset.getSubimage(100, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbNormalFertilizerImage.setBounds(x_normal, y_image, jlb_size, jlb_size);
		add(jlbNormalFertilizerImage);
		
		jlbNormalFertilizerText = new JLabel("일반 비료");
		jlbNormalFertilizerText.setFont(font);
		jlbNormalFertilizerText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbNormalFertilizerText.setBounds(x_normal, y_text, jlb_size, jlb_size);
		add(jlbNormalFertilizerText);
		
		jlbNormalFertilizerCost = new JLabel("10G 소모");
		jlbNormalFertilizerCost.setFont(font_count);
		jlbNormalFertilizerCost.setHorizontalAlignment(SwingConstants.CENTER);
		jlbNormalFertilizerCost.setBounds(x_normal, y_count, jlb_size, jlb_size);
		add(jlbNormalFertilizerCost);
		
		jlbNormalFertilizerClick = new JLabel(inGameImageIcon_btn_normal);
		jlbNormalFertilizerClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbNormalFertilizerClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if (press) {
					setCursor("normalfertilizer");
					currentCrop = -4;
					jlbNormalFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbNormalFertilizerClick.getX() + e.getPoint().x;
				mouseClick.y = jlbNormalFertilizerClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				jlbNormalFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
			}
			public void mouseExited(MouseEvent e) {
				jlbNormalFertilizerClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbNormalFertilizerClick.setBounds(x_normal - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbNormalFertilizerClick);
		
		jlbAdvancedFertilizerImage = new JLabel(new ImageIcon(inGameImage_fertilizerset.getSubimage(200, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbAdvancedFertilizerImage.setBounds(x_advanced, y_image, jlb_size, jlb_size);
		add(jlbAdvancedFertilizerImage);
		
		jlbAdvancedFertilizerText = new JLabel("고급 비료");
		jlbAdvancedFertilizerText.setFont(font);
		jlbAdvancedFertilizerText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbAdvancedFertilizerText.setBounds(x_advanced, y_text, jlb_size, jlb_size);
		add(jlbAdvancedFertilizerText);
		
		jlbAdvancedFertilizerCost = new JLabel("50G 소모");
		jlbAdvancedFertilizerCost.setFont(font_count);
		jlbAdvancedFertilizerCost.setHorizontalAlignment(SwingConstants.CENTER);
		jlbAdvancedFertilizerCost.setBounds(x_advanced, y_count, jlb_size, jlb_size);
		add(jlbAdvancedFertilizerCost);
		
		jlbAdvancedFertilizerClick = new JLabel(inGameImageIcon_btn_normal);
		jlbAdvancedFertilizerClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbAdvancedFertilizerClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if (press) {
					setCursor("advancedfertilizer");
					currentCrop = -5;
					jlbAdvancedFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbAdvancedFertilizerClick.getX() + e.getPoint().x;
				mouseClick.y = jlbAdvancedFertilizerClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				jlbAdvancedFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
			}
			public void mouseExited(MouseEvent e) {
				jlbAdvancedFertilizerClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbAdvancedFertilizerClick.setBounds(x_advanced - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbAdvancedFertilizerClick);
		
		jlbPremiumFertilizerImage = new JLabel(new ImageIcon(inGameImage_fertilizerset.getSubimage(300, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPremiumFertilizerImage.setBounds(x_premium, y_image, jlb_size, jlb_size);
		add(jlbPremiumFertilizerImage);
		
		jlbPremiumFertilizerText = new JLabel("프리미엄 비료");
		jlbPremiumFertilizerText.setFont(font);
		jlbPremiumFertilizerText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPremiumFertilizerText.setBounds(x_premium, y_text, jlb_size, jlb_size);
		add(jlbPremiumFertilizerText);
		
		jlbPremiumFertilizerCost = new JLabel("200G 소모");
		jlbPremiumFertilizerCost.setFont(font_count);
		jlbPremiumFertilizerCost.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPremiumFertilizerCost.setBounds(x_premium, y_count, jlb_size, jlb_size);
		add(jlbPremiumFertilizerCost);
		
		jlbPremiumFertilizerClick = new JLabel(inGameImageIcon_btn_normal);
		jlbPremiumFertilizerClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbPremiumFertilizerClick.setIcon(inGameImageIcon_btn_pressed);
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if (press) {
					setCursor("premiumfertilizer");
					currentCrop = -6;
					jlbPremiumFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbAdvancedFertilizerClick.getX() + e.getPoint().x;
				mouseClick.y = jlbAdvancedFertilizerClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				jlbPremiumFertilizerClick.setIcon(inGameImageIcon_btn_overlap);
			}
			public void mouseExited(MouseEvent e) {
				jlbPremiumFertilizerClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbPremiumFertilizerClick.setBounds(x_premium - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbPremiumFertilizerClick);

		jlbFertilizerBack = new JLabel(new ImageIcon(inGameImage_back.getScaledInstance(75 * resolution / 80, 75 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbFertilizerBack.setBounds(x_back_fe, y_image, jlb_size, jlb_size);
		add(jlbFertilizerBack);
	
		jlbFertilizerBackText = new JLabel("뒤로가기");
		jlbFertilizerBackText.setFont(font);
		jlbFertilizerBackText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbFertilizerBackText.setBounds(x_back_fe, y_text, jlb_size, jlb_size);
		add(jlbFertilizerBackText);
	
		jlbFertilizerBackClick = new JLabel(inGameImageIcon_btn_normal);
		jlbFertilizerBackClick.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				jlbFertilizerBackClick.setIcon(inGameImageIcon_btn_pressed);
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
				mouseClick.x = jlbFertilizerBackClick.getX() + e.getPoint().x;
				mouseClick.y = jlbFertilizerBackClick.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				jlbFertilizerBackClick.setIcon(inGameImageIcon_btn_overlap);
			}

			public void mouseExited(MouseEvent e) {
				jlbFertilizerBackClick.setIcon(inGameImageIcon_btn_normal);
				press = false;
			}
		});
		jlbFertilizerBackClick.setBounds(x_back_fe - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		add(jlbFertilizerBackClick);
		
		// 이동 제어용 라벨
		jlbPoint = new JLabel();
		jlbPoint.setBounds(0, 0, 0, 0);
		
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
				setComponentBoundFertilizer(currentCamera, e);
			}
	
			public void mouseMoved(MouseEvent e) {
			}
		});
		mouseControl.setBounds(0, getHeight() / 2 + 113 * resolution / 80, getWidth(), 220 * resolution / 80);
		add(mouseControl);
	}
	
	private void paintAppleComponent() {
		this.removeAll();
		paintStarCoinComponent();
		paintFieldComponent();
	}
	
	private void paintShop() throws IOException {
		if (pa_shopSeedComponent) {
			paintShopComponent();
			paintShopSeedComponent();
			pa_shopSeedComponent = false;
		}
		
		if (pa_shopEggComponent) {
			paintShopComponent();
			paintShopEggComponent();
			pa_shopEggComponent = false;
		}
	
	}

	private void paintShopComponent() throws IOException {
		this.removeAll();
		this.setLayout(null);
	
		paintStarCoinComponent();
		
		JLabel jlbTabSeed = new JLabel("seed");
		jlbTabSeed.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					pa_shopSeedComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbTabSeed.getX() + e.getPoint().x;
				mouseClick.y = jlbTabSeed.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});
		jlbTabSeed.setBounds(0, getHeight() - 70 * resolution / 80, 150 * resolution / 80, 50 * resolution / 80);
		add(jlbTabSeed);
		JLabel jlbTabEgg = new JLabel("egg");
		jlbTabEgg.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				press = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(press) {
					pa_shopEggComponent = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbTabEgg.getX() + e.getPoint().x;
				mouseClick.y = jlbTabEgg.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				press = false;
			}
		});
		jlbTabEgg.setBounds(150 * resolution / 80, getHeight() - 70 * resolution / 80, 150 * resolution / 80, 50 * resolution / 80);
		add(jlbTabEgg);

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
		jlbShopBack.setBounds(getWidth() - 150 * resolution / 80, getHeight() - 150 * resolution / 80, 100 * resolution / 80, 100 * resolution / 80);
		add(jlbShopBack);
	}
	
	private void paintShopSeedComponent() throws IOException {
		jlbPotato = new JLabel(shopImageIcon_item);
		jlbPotato.setBounds(x_shop_1, y_shop_item, size_shop_x, size_shop_item_y);
	
		jlbPicturePotato = new JLabel(new ImageIcon(cropImage_potato.getSubimage(500, 0, 100, 100)
				.getScaledInstance(size_shop_image, size_shop_image, Image.SCALE_SMOOTH)));
		jlbPicturePotato.setBounds(x_shop_1_image, y_shop_image, size_shop_image, size_shop_image);
		add(jlbPicturePotato);
		jlbLorePotato = new JLabel("감자");
		jlbLorePotato.setFont(font);
		jlbLorePotato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLorePotato.setBounds(x_shop_1, y_shop_lore, size_shop_x, size_shop_lore_y);
		add(jlbLorePotato);
		jlbLore2Potato = new JLabel("씨감자 " + farmData.getCrop("PotatoSeed") + "개, 감자 " + farmData.getCrop("Potato") + "개");
		jlbLore2Potato.setFont(font);
		jlbLore2Potato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Potato.setBounds(x_shop_1, y_shop_lore2, size_shop_x, size_shop_lore_y);
		add(jlbLore2Potato);
		jlbLore3Potato = new JLabel("구입가: 10G          판매가: 7G");
		jlbLore3Potato.setFont(font);
		jlbLore3Potato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Potato.setBounds(x_shop_1, y_shop_lore3, size_shop_x, size_shop_lore_y);
		add(jlbLore3Potato);
		
		jlbBuyPotato = new JLabel(shopImageIcon_btnbuy_normal);
		jlbBuyPotato.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyPotato.setIcon(shopImageIcon_btnbuy_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyPotato.setIcon(shopImageIcon_btnbuy_overlap);
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
				jlbBuyPotato.setIcon(shopImageIcon_btnbuy_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyPotato.setIcon(shopImageIcon_btnbuy_normal);
				press = false;
			}
		});
		jlbBuyPotato.setBounds(x_shop_1_btnbuy, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbBuyPotato);
	
		jlbSellPotato = new JLabel(shopImageIcon_btnsell_normal);
		jlbSellPotato.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellPotato.setIcon(shopImageIcon_btnsell_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellPotato.setIcon(shopImageIcon_btnsell_overlap);
					if(farmData.getCrop("Potato") > 0) {
						farmData.setCrop("Potato", 1, false);
						farmData.setCoin(7, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellPotato.getX() + e.getPoint().x;
				mouseClick.y = jlbSellPotato.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellPotato.setIcon(shopImageIcon_btnsell_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellPotato.setIcon(shopImageIcon_btnsell_normal);
				press = false;
			}
		});
		jlbSellPotato.setBounds(x_shop_1_btnsell, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbSellPotato);
		
		add(jlbPotato);
		
		jlbCarrot = new JLabel(shopImageIcon_item);
		jlbCarrot.setBounds(x_shop_2, y_shop_item, size_shop_x, size_shop_item_y);
	
		jlbPictureCarrot = new JLabel(new ImageIcon(cropImage_carrot.getSubimage(500, 0, 100, 100)
				.getScaledInstance(size_shop_image, size_shop_image, Image.SCALE_SMOOTH)));
		jlbPictureCarrot.setBounds(x_shop_2_image, y_shop_image, size_shop_image, size_shop_image);
		add(jlbPictureCarrot);
		jlbLoreCarrot = new JLabel("당근");
		jlbLoreCarrot.setFont(font);
		jlbLoreCarrot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLoreCarrot.setBounds(x_shop_2, y_shop_lore, size_shop_x, size_shop_lore_y);
		add(jlbLoreCarrot);
		jlbLore2Carrot = new JLabel("당근씨앗 " + farmData.getCrop("CarrotSeed") + "개, 당근 " + farmData.getCrop("Carrot") + "개");
		jlbLore2Carrot.setFont(font);
		jlbLore2Carrot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Carrot.setBounds(x_shop_2, y_shop_lore2, size_shop_x, size_shop_lore_y);
		add(jlbLore2Carrot);
		jlbLore3Carrot = new JLabel("구입가: 20G          판매가: 25G");
		jlbLore3Carrot.setFont(font);
		jlbLore3Carrot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Carrot.setBounds(x_shop_2, y_shop_lore3, size_shop_x, size_shop_lore_y);
		add(jlbLore3Carrot);
		
		jlbBuyCarrot = new JLabel(shopImageIcon_btnbuy_normal);
		jlbBuyCarrot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyCarrot.setIcon(shopImageIcon_btnbuy_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyCarrot.setIcon(shopImageIcon_btnbuy_overlap);
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
				jlbBuyCarrot.setIcon(shopImageIcon_btnbuy_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyCarrot.setIcon(shopImageIcon_btnbuy_normal);
				press = false;
			}
		});
		jlbBuyCarrot.setBounds(x_shop_2_btnbuy, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbBuyCarrot);
		
		jlbSellCarrot = new JLabel(shopImageIcon_btnsell_normal);
		jlbSellCarrot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellCarrot.setIcon(shopImageIcon_btnsell_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellCarrot.setIcon(shopImageIcon_btnsell_overlap);
					if(farmData.getCrop("Carrot") > 0) {
						farmData.setCrop("Carrot", 1, false);
						farmData.setCoin(25, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellCarrot.getX() + e.getPoint().x;
				mouseClick.y = jlbSellCarrot.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellCarrot.setIcon(shopImageIcon_btnsell_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellCarrot.setIcon(shopImageIcon_btnsell_normal);
				press = false;
			}
		});
		jlbSellCarrot.setBounds(x_shop_2_btnsell, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbSellCarrot);
		
		add(jlbCarrot);
		
		jlbBeetroot = new JLabel(shopImageIcon_item);
		jlbBeetroot.setBounds(x_shop_3, y_shop_item, size_shop_x, size_shop_item_y);
	
		jlbPictureBeetroot = new JLabel(new ImageIcon(cropImage_beetroot.getSubimage(500, 0, 100, 100)
				.getScaledInstance(size_shop_image, size_shop_image, Image.SCALE_SMOOTH)));
		jlbPictureBeetroot.setBounds(x_shop_3_image, y_shop_image, size_shop_image, size_shop_image);
		add(jlbPictureBeetroot);
		jlbLoreBeetroot = new JLabel("비트");
		jlbLoreBeetroot.setFont(font);
		jlbLoreBeetroot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLoreBeetroot.setBounds(x_shop_3, y_shop_lore, size_shop_x, size_shop_lore_y);
		add(jlbLoreBeetroot);
		jlbLore2Beetroot = new JLabel("비트씨앗 " + farmData.getCrop("BeetrootSeed") + "개, 비트 " + farmData.getCrop("Beetroot") + "개");
		jlbLore2Beetroot.setFont(font);
		jlbLore2Beetroot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Beetroot.setBounds(x_shop_3, y_shop_lore2, size_shop_x, size_shop_lore_y);
		add(jlbLore2Beetroot);
		jlbLore3Beetroot = new JLabel("구입가: 40G          판매가: 55G");
		jlbLore3Beetroot.setFont(font);
		jlbLore3Beetroot.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Beetroot.setBounds(x_shop_3, y_shop_lore3, size_shop_x, size_shop_lore_y);
		add(jlbLore3Beetroot);
		
		jlbBuyBeetroot = new JLabel(shopImageIcon_btnbuy_normal);
		jlbBuyBeetroot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyBeetroot.setIcon(shopImageIcon_btnbuy_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyBeetroot.setIcon(shopImageIcon_btnbuy_overlap);
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
				jlbBuyBeetroot.setIcon(shopImageIcon_btnbuy_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyBeetroot.setIcon(shopImageIcon_btnbuy_normal);
				press = false;
			}
		});
		jlbBuyBeetroot.setBounds(x_shop_3_btnbuy, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbBuyBeetroot);
		
		jlbSellBeetroot = new JLabel(shopImageIcon_btnsell_normal);
		jlbSellBeetroot.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellBeetroot.setIcon(shopImageIcon_btnsell_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellBeetroot.setIcon(shopImageIcon_btnsell_overlap);
					if(farmData.getCrop("Beetroot") > 0) {
						farmData.setCrop("Beetroot", 1, false);
						farmData.setCoin(55, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellBeetroot.getX() + e.getPoint().x;
				mouseClick.y = jlbSellBeetroot.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellBeetroot.setIcon(shopImageIcon_btnsell_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellBeetroot.setIcon(shopImageIcon_btnsell_normal);
				press = false;
			}
		});
		jlbSellBeetroot.setBounds(x_shop_3_btnsell, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbSellBeetroot);
		
		add(jlbBeetroot);
		
		jlbSweetPotato = new JLabel(shopImageIcon_item);
		jlbSweetPotato.setBounds(x_shop_4, y_shop_item, size_shop_x, size_shop_item_y);
	
		jlbPictureSweetPotato = new JLabel(new ImageIcon(cropImage_sweetpotato.getSubimage(500, 0, 100, 100)
				.getScaledInstance(size_shop_image, size_shop_image, Image.SCALE_SMOOTH)));
		jlbPictureSweetPotato.setBounds(x_shop_4_image, y_shop_image, size_shop_image, size_shop_image);
		add(jlbPictureSweetPotato);
		jlbLoreSweetPotato = new JLabel("고구마");
		jlbLoreSweetPotato.setFont(font);
		jlbLoreSweetPotato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLoreSweetPotato.setBounds(x_shop_4, y_shop_lore, size_shop_x, size_shop_lore_y);
		add(jlbLoreSweetPotato);
		jlbLore2SweetPotato = new JLabel("씨고구마 " + farmData.getCrop("SweetPotatoSeed") + "개, 고구마 " + farmData.getCrop("SweetPotato") + "개");
		jlbLore2SweetPotato.setFont(font);
		jlbLore2SweetPotato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2SweetPotato.setBounds(x_shop_4, y_shop_lore2, size_shop_x, size_shop_lore_y);
		add(jlbLore2SweetPotato);
		jlbLore3SweetPotato = new JLabel("구입가: 80G          판매가: 60G");
		jlbLore3SweetPotato.setFont(font);
		jlbLore3SweetPotato.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3SweetPotato.setBounds(x_shop_4, y_shop_lore3, size_shop_x, size_shop_lore_y);
		add(jlbLore3SweetPotato);
		
		jlbBuySweetPotato = new JLabel(shopImageIcon_btnbuy_normal);
		jlbBuySweetPotato.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuySweetPotato.setIcon(shopImageIcon_btnbuy_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuySweetPotato.setIcon(shopImageIcon_btnbuy_overlap);
					if(farmData.getCoin() >= 80) {
						farmData.setCrop("SweetPotatoSeed", 1, true);
						farmData.setCoin(80, false);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBuySweetPotato.getX() + e.getPoint().x;
				mouseClick.y = jlbBuySweetPotato.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBuySweetPotato.setIcon(shopImageIcon_btnbuy_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuySweetPotato.setIcon(shopImageIcon_btnbuy_normal);
				press = false;
			}
		});
		jlbBuySweetPotato.setBounds(x_shop_4_btnbuy, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbBuySweetPotato);
	
		jlbSellSweetPotato = new JLabel(shopImageIcon_btnsell_normal);
		jlbSellSweetPotato.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellSweetPotato.setIcon(shopImageIcon_btnsell_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellSweetPotato.setIcon(shopImageIcon_btnsell_overlap);
					if(farmData.getCrop("SweetPotato") > 0) {
						farmData.setCrop("SweetPotato", 1, false);
						farmData.setCoin(60, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellSweetPotato.getX() + e.getPoint().x;
				mouseClick.y = jlbSellSweetPotato.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellSweetPotato.setIcon(shopImageIcon_btnsell_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellSweetPotato.setIcon(shopImageIcon_btnsell_normal);
				press = false;
			}
		});
		jlbSellSweetPotato.setBounds(x_shop_4_btnsell, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbSellSweetPotato);
		
		add(jlbSweetPotato);
		
		jlbDaikon = new JLabel(shopImageIcon_item);
		jlbDaikon.setBounds(x_shop_5, y_shop_item, size_shop_x, size_shop_item_y);
	
		jlbPictureDaikon = new JLabel(new ImageIcon(cropImage_sweetpotato.getSubimage(500, 0, 100, 100)
				.getScaledInstance(size_shop_image, size_shop_image, Image.SCALE_SMOOTH)));
		jlbPictureDaikon.setBounds(x_shop_5_image, y_shop_image, size_shop_image, size_shop_image);
		add(jlbPictureDaikon);
		jlbLoreDaikon = new JLabel("무");
		jlbLoreDaikon.setFont(font);
		jlbLoreDaikon.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLoreDaikon.setBounds(x_shop_5, y_shop_lore, size_shop_x, size_shop_lore_y);
		add(jlbLoreDaikon);
		jlbLore2Daikon = new JLabel("무씨앗 " + farmData.getCrop("DaikonSeed") + "개, 무 " + farmData.getCrop("Daikon") + "개");
		jlbLore2Daikon.setFont(font);
		jlbLore2Daikon.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Daikon.setBounds(x_shop_5, y_shop_lore2, size_shop_x, size_shop_lore_y);
		add(jlbLore2Daikon);
		jlbLore3Daikon = new JLabel("구입가: 100G        판매가: 150G");
		jlbLore3Daikon.setFont(font);
		jlbLore3Daikon.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Daikon.setBounds(x_shop_5, y_shop_lore3, size_shop_x, size_shop_lore_y);
		add(jlbLore3Daikon);
		
		jlbBuyDaikon = new JLabel(shopImageIcon_btnbuy_normal);
		jlbBuyDaikon.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyDaikon.setIcon(shopImageIcon_btnbuy_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyDaikon.setIcon(shopImageIcon_btnbuy_overlap);
					if(farmData.getCoin() >= 100) {
						farmData.setCrop("DaikonSeed", 1, true);
						farmData.setCoin(100, false);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBuyDaikon.getX() + e.getPoint().x;
				mouseClick.y = jlbBuyDaikon.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBuyDaikon.setIcon(shopImageIcon_btnbuy_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyDaikon.setIcon(shopImageIcon_btnbuy_normal);
				press = false;
			}
		});
		jlbBuyDaikon.setBounds(x_shop_5_btnbuy, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbBuyDaikon);
	
		jlbSellDaikon = new JLabel(shopImageIcon_btnsell_normal);
		jlbSellDaikon.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellDaikon.setIcon(shopImageIcon_btnsell_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellDaikon.setIcon(shopImageIcon_btnsell_overlap);
					if(farmData.getCrop("Daikon") > 0) {
						farmData.setCrop("Daikon", 1, false);
						farmData.setCoin(150, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellDaikon.getX() + e.getPoint().x;
				mouseClick.y = jlbSellDaikon.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellDaikon.setIcon(shopImageIcon_btnsell_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellDaikon.setIcon(shopImageIcon_btnsell_normal);
				press = false;
			}
		});
		jlbSellDaikon.setBounds(x_shop_5_btnsell, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbSellDaikon);
		
		add(jlbDaikon);
	}

	private void paintShopEggComponent() {
		jlbEgg = new JLabel(shopImageIcon_item);
		jlbEgg.setBounds(x_shop_1, y_shop_item, size_shop_x, size_shop_item_y);
	
		jlbPictureEgg = new JLabel(new ImageIcon(poultryImage_egg.getSubimage(0, 0, 100, 100)
				.getScaledInstance(size_shop_image, size_shop_image, Image.SCALE_SMOOTH)));
		jlbPictureEgg.setBounds(x_shop_1_image, y_shop_image, size_shop_image, size_shop_image);
		add(jlbPictureEgg);
		jlbLoreEgg = new JLabel("달걀");
		jlbLoreEgg.setFont(font);
		jlbLoreEgg.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLoreEgg.setBounds(x_shop_1, y_shop_lore, size_shop_x, size_shop_lore_y);
		add(jlbLoreEgg);
		jlbLore2Egg = new JLabel("유정란 " + farmData.getCrop("FertilizedEgg") + "개, 무정란 " + farmData.getCrop("UnfertilizedEgg") + "개");
		jlbLore2Egg.setFont(font);
		jlbLore2Egg.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore2Egg.setBounds(x_shop_1, y_shop_lore2, size_shop_x, size_shop_lore_y);
		add(jlbLore2Egg);
		jlbLore3Egg = new JLabel("구입가: 100G          판매가: 10G");
		jlbLore3Egg.setFont(font);
		jlbLore3Egg.setHorizontalAlignment(SwingConstants.CENTER);
		jlbLore3Egg.setBounds(x_shop_1, y_shop_lore3, size_shop_x, size_shop_lore_y);
		add(jlbLore3Egg);
		
		jlbBuyEgg = new JLabel(shopImageIcon_btnbuy_normal);
		jlbBuyEgg.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbBuyEgg.setIcon(shopImageIcon_btnbuy_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbBuyEgg.setIcon(shopImageIcon_btnbuy_overlap);
					if(farmData.getCoin() >= 100) {
						farmData.setCrop("FertilizedEgg", 1, true);
						farmData.setCoin(100, false);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBuyEgg.getX() + e.getPoint().x;
				mouseClick.y = jlbBuyEgg.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbBuyEgg.setIcon(shopImageIcon_btnbuy_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbBuyEgg.setIcon(shopImageIcon_btnbuy_normal);
				press = false;
			}
		});
		jlbBuyEgg.setBounds(x_shop_1_btnbuy, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbBuyEgg);
	
		jlbSellEgg = new JLabel(shopImageIcon_btnsell_normal);
		jlbSellEgg.addMouseListener(new MouseListener() {
			boolean press = false;
			public void mouseClicked(MouseEvent e) {
			}
	
			public void mousePressed(MouseEvent e) {
				jlbSellEgg.setIcon(shopImageIcon_btnsell_pressed);
				press = true;
			}
	
			public void mouseReleased(MouseEvent e) {
				if (press) {
					jlbSellEgg.setIcon(shopImageIcon_btnsell_overlap);
					if(farmData.getCrop("UnfertilizedEgg") > 0) {
						farmData.setCrop("UnfertilizedEgg", 1, false);
						farmData.setCoin(10, true);
						refreshCoin();
					}
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSellEgg.getX() + e.getPoint().x;
				mouseClick.y = jlbSellEgg.getY() + e.getPoint().y;
				repaint();
			}
	
			public void mouseEntered(MouseEvent e) {
				jlbSellEgg.setIcon(shopImageIcon_btnsell_overlap);
			}
	
			public void mouseExited(MouseEvent e) {
				jlbSellEgg.setIcon(shopImageIcon_btnsell_normal);
				press = false;
			}
		});
		jlbSellEgg.setBounds(x_shop_1_btnsell, y_shop_btn, size_shop_btn_x, size_shop_btn_y);
		add(jlbSellEgg);
		
		add(jlbEgg);
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
					farmData.egg.setEgg();
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
		
		jlbTamago = new JLabel();
		switch(farmData.egg.level) {
		case 0:	
			jlbTamago.setIcon(new ImageIcon(poultryImage_egg.getSubimage(0, 0, 100, 100).getScaledInstance(120 * resolution / 80, 120 * resolution / 80, Image.SCALE_SMOOTH)));
			break;
		case 1:
			jlbTamago.setIcon(new ImageIcon(poultryImage_egg.getSubimage(100, 0, 100, 100).getScaledInstance(120 * resolution / 80, 120 * resolution / 80, Image.SCALE_SMOOTH)));
			break;
		case 2:
			jlbTamago.setIcon(new ImageIcon(poultryImage_egg.getSubimage(200, 0, 100, 100).getScaledInstance(120 * resolution / 80, 120 * resolution / 80, Image.SCALE_SMOOTH)));
			break;
		}
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
		if(jlbLore2SweetPotato != null)
			jlbLore2SweetPotato.setText("씨고구마 " + farmData.getCrop("SweetPotatoSeed") + "개, 고구마 " + farmData.getCrop("SweetPotato") + "개");
		if(jlbLore2Daikon != null)
			jlbLore2Daikon.setText("무씨앗 " + farmData.getCrop("DaikonSeed") + "개, 무 " + farmData.getCrop("Daikon") + "개");
		if(jlbLore2Egg != null)
			jlbLore2Egg.setText("유정란 " + farmData.getCrop("FertilizedEgg") + "개, 무정란 " + farmData.getCrop("UnfertilizedEgg") + "개");
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
	        refreshFieldText(jlbTimeTemp, fieldData);
		}	
	}
	
	private void refreshFieldText(JLabel jlbFieldText, int[] fieldData) {
	    if (fieldData[0] != 0 && fieldData[1] != 4 && fieldData[3] > 0) {
	        int growthPercentage = (int) ((float) (fieldData[2] + farmData.getCropTime(getCropName(fieldData[0], false, false), fieldData[1])) / farmData.getCropLeftTime(getCropName(fieldData[0], false, false)) * 100);
	        jlbFieldText.setText(growthPercentage + "% 성장");
	    } else if (fieldData[1] == 4) {
	        jlbFieldText.setText("수확");
	    } else if (fieldData[0] == 0) {
	    	jlbFieldText.setText("씨앗 심기");
	    } else {
	        jlbFieldText.setText("물 주기");
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
			jlbSweetPotatoCount.setText("보유개수: " + farmData.getCrop("SweetPotatoSeed") + "개");
			jlbDaikonCount.setText("보유개수: " + farmData.getCrop("DaikonSeed") + "개");
		}
	}

	private void setValue() {
		// 상점
		// x
		count = 0;
		x_shop_1 = (50 + 300 * count) * resolution / 80;
		x_shop_1_image = x_shop_1 + 50 * resolution / 80;
		x_shop_1_btnbuy = x_shop_1 + 10 * resolution / 80;
		x_shop_1_btnsell = x_shop_1 + 130 * resolution / 80;
		count++;
		x_shop_2 = (50 + 300 * count) * resolution / 80;
		x_shop_2_image = x_shop_2 + 50 * resolution / 80;
		x_shop_2_btnbuy = x_shop_2 + 10 * resolution / 80;
		x_shop_2_btnsell = x_shop_2 + 130 * resolution / 80;
		count++;
		x_shop_3 = (50 + 300 * count) * resolution / 80;
		x_shop_3_image = x_shop_3 + 50 * resolution / 80;
		x_shop_3_btnbuy = x_shop_3 + 10 * resolution / 80;
		x_shop_3_btnsell = x_shop_3 + 130 * resolution / 80;
		count++;
		x_shop_4 = (50 + 300 * count) * resolution / 80;
		x_shop_4_image = x_shop_4 + 50 * resolution / 80;
		x_shop_4_btnbuy = x_shop_4 + 10 * resolution / 80;
		x_shop_4_btnsell = x_shop_4 + 130 * resolution / 80;
		count++;
		x_shop_5 = (50 + 300 * count) * resolution / 80;
		x_shop_5_image = x_shop_5 + 50 * resolution / 80;
		x_shop_5_btnbuy = x_shop_5 + 10 * resolution / 80;
		x_shop_5_btnsell = x_shop_5 + 130 * resolution / 80;
		shop_camera_max = (1280 - (50 * 2 + 300 * count + 100)) * resolution / 80;
		
		// y
		y_shop_item = 110 * resolution / 80;
		y_shop_image = 135 * resolution / 80;
		y_shop_lore = 320 * resolution / 80;
		y_shop_lore2 = 350 * resolution / 80;
		y_shop_lore3 = 380 * resolution / 80;
		y_shop_btn = 420 * resolution / 80;
		
		//size
		size_shop_x = 250 * resolution / 80;
		size_shop_image = 150 * resolution / 80;
		size_shop_item_y = 300 * resolution / 80;
		size_shop_lore_y = 20 * resolution / 80;
		size_shop_btn_x = 110 * resolution / 80;
		size_shop_btn_y = 50 * resolution / 80;
		
		// 인게임
		// x
		// 새싹
		count = 0;
		x_potato = (50 + 125 * count) * resolution / 80;
		count++;
		x_carrot = (50 + 125 * count) * resolution / 80;
		count++;
		x_beetroot = (50 + 125 * count) * resolution / 80;
		count++;
		x_sweetpotato = (50 + 125 * count) * resolution / 80;
		count++;
		x_daikon = (50 + 125 * count) * resolution / 80;
		count++;
		x_back_sp = (50 + 125 * count) * resolution / 80;
		sprout_camera_max = (1280 - (50 * 2 + 125 * count + 100)) * resolution / 80;
		
		// 삽
		count = 0;
		x_hoe = (50 + 125 * count) * resolution / 80;
		count++;
		x_back_sh = (50 + 125 * count) * resolution / 80;
		shovel_camera_max = (1280 - (50 * 2 + 125 * count + 100)) * resolution / 80;
		
		// 비료
		count = 0;
		x_cheap = (50 + 125 * count) * resolution / 80;
		count++;
		x_normal = (50 + 125 * count) * resolution / 80;
		count++;
		x_advanced = (50 + 125 * count) * resolution / 80;
		count++;
		x_premium = (50 + 125 * count) * resolution / 80;
		count++;
		x_back_fe = (50 + 125 * count) * resolution / 80;
		fertilizer_camera_max = (1280 - (50 * 2 + 125 * count + 100)) * resolution / 80;
		
		// 필드 카메라
		field_camera_max = (1280 - (300 + 110 * (farmData.getField().size()))) * resolution / 80;
		
		// y
		y_image = getHeight() / 2 + 130 * resolution / 80;
		y_text = getHeight() / 2 + 200 * resolution / 80;
		y_count = getHeight() / 2 + 230 * resolution / 80;
		y_click = getHeight() / 2 + 130 * resolution / 80;

		// 사이즈
		jlb_size = 100 * resolution / 80;
		jlb_click_x_size = 120 * resolution / 80;
		jlb_click_y_size = 180 * resolution / 80;
	}
	
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
		jlbCarrotClick.setBounds(x + x_carrot - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbBeetroot.setBounds(x + x_beetroot, y_image, jlb_size, jlb_size);
		jlbBeetrootText.setBounds(x + x_beetroot, y_text, jlb_size, jlb_size);
		jlbBeetrootCount.setBounds(x + x_beetroot, y_count, jlb_size, jlb_size);
		jlbBeetrootClick.setBounds(x + x_beetroot - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbSweetPotato.setBounds(x + x_sweetpotato, y_image, jlb_size, jlb_size);
		jlbSweetPotatoText.setBounds(x + x_sweetpotato, y_text, jlb_size, jlb_size);
		jlbSweetPotatoCount.setBounds(x + x_sweetpotato, y_count, jlb_size, jlb_size);
		jlbSweetPotatoClick.setBounds(x + x_sweetpotato - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbDaikon.setBounds(x + x_daikon, y_image, jlb_size, jlb_size);
		jlbDaikonText.setBounds(x + x_daikon, y_text, jlb_size, jlb_size);
		jlbDaikonCount.setBounds(x + x_daikon, y_count, jlb_size, jlb_size);
		jlbDaikonClick.setBounds(x + x_daikon - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);

		jlbSproutBack.setBounds(x + x_back_sp, y_image, jlb_size, jlb_size);
		jlbSproutBackText.setBounds(x + x_back_sp, y_text, jlb_size, jlb_size);
		jlbSproutBackClick.setBounds(x + x_back_sp - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
	}
	
	private void setComponentBoundShovel(int[] currentCamera, MouseEvent e) {
		if (jlbPoint.getX() > 0 || currentCamera[2] + currentCamera[0] > 0) {
			jlbPoint.setBounds(0, 0, 0, 0);
			currentCamera[1] = e.getX();
			currentCamera[2] = 0;
		} else if (jlbPoint.getX() < shovel_camera_max || currentCamera[2] + currentCamera[0] < shovel_camera_max) {
			if (shovel_camera_max > 0) {
				jlbPoint.setBounds(0, 0, 0, 0);
				currentCamera[1] = e.getX();
				currentCamera[2] = 0;
			} else {
				jlbPoint.setBounds(shovel_camera_max, 0, 0, 0);
				currentCamera[1] = e.getX();
				currentCamera[2] = shovel_camera_max;
			}
		} else {
			jlbPoint.setBounds(currentCamera[2] + currentCamera[0], 0, 0, 0);
		}

		int x = jlbPoint.getX();

		jlbHoeImage.setBounds(x + x_hoe, y_image, jlb_size, jlb_size);
		jlbHoeText.setBounds(x + x_hoe, y_text, jlb_size, jlb_size);
		jlbHoeCost.setBounds(x + x_hoe, y_count, jlb_size, jlb_size);
		jlbHoeClick.setBounds(x + x_hoe - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);

		jlbShovelBack.setBounds(x + x_back_sh, y_image, jlb_size, jlb_size);
		jlbShovelBackText.setBounds(x + x_back_sh, y_text, jlb_size, jlb_size);
		jlbShovelBackClick.setBounds(x + x_back_sh - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
	}
	
	private void setComponentBoundFertilizer(int[] currentCamera, MouseEvent e) {
		if (jlbPoint.getX() > 0 || currentCamera[2] + currentCamera[0] > 0) {
			jlbPoint.setBounds(0, 0, 0, 0);
			currentCamera[1] = e.getX();
			currentCamera[2] = 0;
		} else if (jlbPoint.getX() < fertilizer_camera_max || currentCamera[2] + currentCamera[0] < fertilizer_camera_max) {
			if (fertilizer_camera_max > 0) {
				jlbPoint.setBounds(0, 0, 0, 0);
				currentCamera[1] = e.getX();
				currentCamera[2] = 0;
			} else {
				jlbPoint.setBounds(fertilizer_camera_max, 0, 0, 0);
				currentCamera[1] = e.getX();
				currentCamera[2] = fertilizer_camera_max;
			}
		} else {
			jlbPoint.setBounds(currentCamera[2] + currentCamera[0], 0, 0, 0);
		}

		int x = jlbPoint.getX();

		jlbCheapFertilizerImage.setBounds(x + x_cheap, y_image, jlb_size, jlb_size);
		jlbCheapFertilizerText.setBounds(x + x_cheap, y_text, jlb_size, jlb_size);
		jlbCheapFertilizerCost.setBounds(x + x_cheap, y_count, jlb_size, jlb_size);
		jlbCheapFertilizerClick.setBounds(x + x_cheap - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		
		jlbNormalFertilizerImage.setBounds(x + x_normal, y_image, jlb_size, jlb_size);
		jlbNormalFertilizerText.setBounds(x + x_normal, y_text, jlb_size, jlb_size);
		jlbNormalFertilizerCost.setBounds(x + x_normal, y_count, jlb_size, jlb_size);
		jlbNormalFertilizerClick.setBounds(x + x_normal - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);

		jlbAdvancedFertilizerImage.setBounds(x + x_advanced, y_image, jlb_size, jlb_size);
		jlbAdvancedFertilizerText.setBounds(x + x_advanced, y_text, jlb_size, jlb_size);
		jlbAdvancedFertilizerCost.setBounds(x + x_advanced, y_count, jlb_size, jlb_size);
		jlbAdvancedFertilizerClick.setBounds(x + x_advanced - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);

		jlbPremiumFertilizerImage.setBounds(x + x_premium, y_image, jlb_size, jlb_size);
		jlbPremiumFertilizerText.setBounds(x + x_premium, y_text, jlb_size, jlb_size);
		jlbPremiumFertilizerCost.setBounds(x + x_premium, y_count, jlb_size, jlb_size);
		jlbPremiumFertilizerClick.setBounds(x + x_premium - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);

		jlbFertilizerBack.setBounds(x + x_back_fe, y_image, jlb_size, jlb_size);
		jlbFertilizerBackText.setBounds(x + x_back_fe, y_text, jlb_size, jlb_size);
		jlbFertilizerBackClick.setBounds(x + x_back_fe - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
	}

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
			case 4:
				return "고구마";
			case 5:
				return "무";
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
			case 4:
				return "SweetPotatoSeed";
			case 5:
				return "DaikonSeed";
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
		case 4:
			return "SweetPotato";
		case 5:
			return "Daikon";
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
		        
		        if(fieldData[0] == 0) {
		        	plantCrop(index);
		        } else if (fieldData[1] == 4) {
		        	harvestCrop(index);
		        } else {
		        	waterCrop(index);
		        	fertilizerCrop(index);
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
		
		//currentCrop -3~-6 비료 주기, -2 물 주기, 0 - 노말, 1 - 감자, 2 - 당근, 3 - 비트, 4 - 고구마, 5 - 무
	    private void harvestCrop(int index) {
	    	int[] temp = farmData.getField(index);
	    	if(temp[0] == 1) {
	    		farmData.setCrop("Potato", 2 + temp[4], true);
	    	} else if (temp[0] == 4) {
	    		farmData.setCrop("SweetPotato", 2 + temp[4], true);
	    	} else {
	    		farmData.setCrop(getCropName(temp[0], false, false), 1 + temp[4], true);
	    	}
	    	farmData.setField(index);
	        jlbCropField.get(index).removeMouseListener(this);
	        refreshCount();
	        refreshField();
	    }
	
	    private void waterCrop(int index) {
	    	if (currentCrop == -2) {
	    		int[] temp = farmData.getField(index);
	    		temp[3] = 40;
	    		farmData.setField(index, temp);
	    		refreshField();
	    	}
	    }
	    
	    private void fertilizerCrop(int index) { // 3 싸구려, 4 일반, 5 고급, 6 프리미엄
	    	int[] temp = farmData.getField(index);
	    	if (currentCrop == -3) {
	    		if(temp[4] < 1 && farmData.getCoin() >= 5) {
	    			farmData.setCoin(5, false);
	    			temp[4] = 1;
		    		farmData.setField(index, temp);
		    		refreshCoin();
		    		refreshField();
	    		}
	    	} else if (currentCrop == -4) {
	    		if(temp[4] < 2 && farmData.getCoin() >= 10) {
	    			farmData.setCoin(10, false);
	    			temp[4] = 2;
		    		farmData.setField(index, temp);
		    		refreshCoin();
		    		refreshField();
	    		}
	    	} else if (currentCrop == -5) {
	    		if(temp[4] < 3 && farmData.getCoin() >= 50) {
	    			farmData.setCoin(50, false);
	    			temp[4] = 3;
		    		farmData.setField(index, temp);
		    		refreshCoin();
		    		refreshField();
	    		}
	    	} else if (currentCrop == -6) {
	    		if(temp[4] < 4 && farmData.getCoin() >= 200) {
	    			farmData.setCoin(200, false);
	    			temp[4] = 4;
		    		farmData.setField(index, temp);
		    		refreshCoin();
		    		refreshField();
	    		}
	    	}
	    }
	    
	    private void plantCrop(int index) {
	        if (currentCrop > 0 && farmData.getCrop(getCropName(currentCrop, true, false)) > 0) {
	            farmData.setCrop(getCropName(currentCrop, true, false), 1, false);
	            System.out.println("씨앗 소모");
	            farmData.setField(index, new int[]{currentCrop, 0, 0, 0, 0});
	            jlbCropField.get(index).removeMouseListener(this);
	            refreshCount();
	            refreshField();
	        }
	    }
	}
}