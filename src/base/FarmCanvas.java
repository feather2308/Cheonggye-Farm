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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FarmCanvas extends JPanel implements Runnable, MouseListener {
	protected MyLauncher myLauncher;
	protected FarmData farmData = new FarmData();
	
	protected Font font, font_count;
	
	//cursor
	Map<String, Cursor> cursor = new HashMap<>();
	protected final String cursor_chicken = "/resource/cursor/cursor_chicken.png",
						   cursor_potato =	"/resource/cursor/cursor_potato.png",
						   cursor_carrot =	"/resource/cursor/cursor_carrot.png",
						   cursor_beetroot = "/resource/cursor/cursor_beetroot.png";
	protected BufferedImage cursorImage_chicken,
							cursorImage_potato,
							cursorImage_carrot,
							cursorImage_beetroot;
	protected Cursor customCursor_chicken,
					 customCursor_potato,
					 customCursor_carrot,
					 customCursor_beetroot;
	
	//mainLobby
	//그림 관련 변수
	protected final String   //mainLobby_logo 	    = "/resource/mainLobby/logo.png",
						     mainLobby_background   = "/resource/mainLobby/background.png",
						     mainLobby_chicken 	    = "/resource/mainLobby/chicken.png",
						     mainLobby_singsing		= "/resource/mainLobby/singsing.png",
							 mainLobby_buttonstart = "/resource/mainLobby/buttonstart.png",
							 mainLobby_buttondesc = "/resource/mainLobby/buttondesc.png",
							 mainLobby_buttonsett = "/resource/mainLobby/buttonsett.png",
							 mainLobby_cloud = "/resource/mainLobby/cloud.png";
	protected final String[] mainLobby_logo			= {"/resource/mainLobby/logo1.png",
													   "/resource/mainLobby/logo2.png",
													   "/resource/mainLobby/logo3.png"},
							 mainLobby_cloud1 		= {"/resource/mainLobby/cloud1_0.png",
													   "/resource/mainLobby/cloud1_1.png",
													   "/resource/mainLobby/cloud1_2.png"},
							 mainLobby_cloud2		= {"/resource/mainLobby/cloud2_0.png",
									 				   "/resource/mainLobby/cloud2_1.png",
									 				   "/resource/mainLobby/cloud2_2.png"};
	protected Image //mainLobbyImage_logo,
					mainLobbyImage_background,
					mainLobbyImage_chicken,
					mainLobbyImage_singsing;
	protected BufferedImage mainLobbyImage_buttonstart,
							mainLobbyImage_buttondesc,
							mainLobbyImage_buttonsett,
							mainLobbyImage_cloud;
	protected Image[] mainLobbyImage_logo,
					  mainLobbyImage_cloud1,
					  mainLobbyImage_cloud2;
	
	//그림 상태
	protected int mainLobby_logo_index = new Random().nextInt(0, 3),
				  mainLobby_cloud_stat = 0, mainLobby_cloud_index = 0;
	
	//inGame
	protected final String inGame_starcoin = 	"/resource/inGame/starcoin.png",
						   inGame_sprout = 		"/resource/inGame/sprout.png",
						   inGame_wateringcan = "/resource/inGame/wateringcan.png",
						   inGame_apple =		"/resource/inGame/apple.png",
						   inGame_fertilizer =	"/resource/inGame/fertilizer.png",
						   inGame_shovel =		"/resource/inGame/shovel.png",
						   inGame_background =	"/resource/inGame/background.png";
	protected Image inGameImage_background,
					inGameImage_starcoin;
	protected BufferedImage inGameImage_sprout,
						    inGameImage_wateringcan,
						    inGameImage_apple,
						    inGameImage_fertilizer,
						    inGameImage_shovel;

	//crop
	protected final String crop_potato = "/resource/inGame/crop/potato.png",
						   crop_carrot = "/resource/inGame/crop/carrot.png",
						   crop_beetroot = "/resource/inGame/crop/beetroot.png";
	protected BufferedImage cropImage_potato,
							cropImage_carrot,
							cropImage_beetroot;
	
	protected Thread worker;
	protected Point mouseClick = new Point();
	protected Point mouseCursor = new Point();
	
	//더블버퍼링용 변수
	protected Graphics bufferGraphics = null;
	protected Image offscreen;
	
	protected final int[] mouseClickEffectSize = {2, 4, 6, 8};
	
	protected int mouseClickEffect = -1;
	protected int resolution;
	
	//paint able
	protected boolean pa_mainLobby = true;
	protected boolean pa_inGame = false;
	protected boolean pa_bottomInfo = false;
	protected boolean pa_inShop = false;
	
	//paint component
	protected boolean pa_mainLobbyComponent = true;
	protected boolean pa_inGameComponent = false;
	
	//loading image
	protected boolean la_mainLobby = true;
	protected boolean la_inGame = false;
	
	public FarmCanvas(MyLauncher myLauncher, int resolution) {
		this.myLauncher = myLauncher;
		this.resolution = resolution;		
		addMouseListener(this);
		
		//폰트
		font = myLauncher.font.deriveFont(18f * resolution / 80);
		font_count = myLauncher.font.deriveFont(15f * resolution / 80);
	}

	public void start() {
		worker = new Thread(this);
		worker.start();
		
		requestFocus();
		repaint();
	}
	
	public void run() {
		while(true) {
			try {
				if(mouseClickEffect < 4 && mouseClickEffect != -1) {
					mouseClickEffect++;
				}
				if(pa_mainLobby) {
					if(mainLobby_cloud_stat < 30) mainLobby_cloud_stat++;
					else mainLobby_cloud_stat = 0;
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	public void paint(Graphics g) {
		offscreen = createImage(getSize().width,getSize().height);
		bufferGraphics = offscreen.getGraphics();
		
		bufferGraphics.setColor(Color.white);
		bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

		
		if(pa_mainLobby) {
			try {
				paintMainLobby();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(pa_inGame) {
			try {
				paintInGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		paintComponents(bufferGraphics);
		
		//마우스 클릭 이펙트
		bufferGraphics.setColor(Color.BLACK);
		if(mouseClickEffect < mouseClickEffectSize.length && mouseClickEffect >= 0)
			bufferGraphics.drawOval(mouseClick.x - mouseClickEffectSize[mouseClickEffect]/2,
   									mouseClick.y - mouseClickEffectSize[mouseClickEffect]/2,
   									mouseClickEffectSize[mouseClickEffect],
   									mouseClickEffectSize[mouseClickEffect]);
		
	    g.drawImage(offscreen, 0, 0, null);
	}

	private void paintMainLobby() throws IOException {
		if(la_mainLobby) {
			loading_MainLobby();
			la_mainLobby = false;
		}
		
		if(pa_mainLobbyComponent) {
			paintMainLobby_Component();
			pa_mainLobbyComponent = false;
		}
		
		//배경
		bufferGraphics.drawImage(mainLobbyImage_background, 0, 0, getWidth(), getHeight(), null);
		
		//로고
		bufferGraphics.drawImage(mainLobbyImage_logo[mainLobby_logo_index],
								 getWidth() / 2 - 600 * resolution / 80 / 2 - 1,
								 0,
								 600 * resolution / 80,
								 300 * resolution / 80,
								 null);
		
		//치킨
		bufferGraphics.drawImage(mainLobbyImage_chicken,
								 20 * resolution / 80 - 1,
								 getHeight() - 275 * resolution / 80,
								 270 * resolution / 80,
								 240 * resolution / 80,
								 null);
		
		//싱싱채소
		bufferGraphics.drawImage(mainLobbyImage_singsing,
								 getWidth() - 280 * resolution / 80 - 1,
								 getHeight() - 250 * resolution / 80,
								 250 * resolution / 80,
								 200 * resolution / 80,
								 null);

		switch(mainLobby_cloud_stat) {
			case 0:			case 1:			case 2:			case 3:			case 4:
				mainLobby_cloud_index = 0;
				break;
			case 5:			case 6:			case 7:			case 8:			case 9:
				mainLobby_cloud_index = 1;
				break;
			case 10:		case 11:		case 12:		case 13:		case 14:
				mainLobby_cloud_index = 2;
				break;
			case 15:		case 16:		case 17:		case 18:		case 19:
				mainLobby_cloud_index = 3;
				break;
			case 20:		case 21:		case 22:		case 23:		case 24:
				mainLobby_cloud_index = 4;
				break;
			case 25:		case 26:		case 27:		case 28:		case 29:
				mainLobby_cloud_index = 5;
				break;
		}
		//구름1
		bufferGraphics.drawImage(mainLobbyImage_cloud.getSubimage(220 * mainLobby_cloud_index, 0, 220, 130).getScaledInstance(220, 130, Image.SCALE_SMOOTH),
				 50 * resolution / 80 - 1,
				 50 * resolution / 80,
				 220 * resolution / 80,
				 130 * resolution / 80,
				 null);
		
		//구름2
		bufferGraphics.drawImage(mainLobbyImage_cloud.getSubimage(230 * mainLobby_cloud_index, 130, 230, 140).getScaledInstance(230, 140, Image.SCALE_SMOOTH),
				getWidth() - 300 * resolution / 80 - 1,
				 65 * resolution / 80,
				 230 * resolution / 80,
				 140 * resolution / 80,
				 null);

//		//중앙점
//		bufferGraphics.setColor(Color.black);
//		bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
	}
	
	private void loading_MainLobby() throws IOException {
		mainLobbyImage_background = 		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_background)));
//		mainLobbyImage_logo = 				 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo)));
		mainLobbyImage_chicken = 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_chicken)));
		mainLobbyImage_singsing = 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_singsing)));
		mainLobbyImage_buttonstart =		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttonstart)));
		mainLobbyImage_buttondesc =			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttondesc)));
		mainLobbyImage_buttonsett =			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttonsett)));
		mainLobbyImage_cloud =				 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud)));
		mainLobbyImage_logo = new Image[]   {ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[0]))),
											 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[1]))),
											 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[2])))};
		mainLobbyImage_cloud1 = new Image[] {ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[0]))),
								 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[1]))),
								 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[2])))};
		mainLobbyImage_cloud2 = new Image[] {ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[0]))),
				            	 	 		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[1]))),
				            	 	 		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[2])))};
		cursorImage_chicken = ImageIO.read(getClass().getResourceAsStream(cursor_chicken));
		cursorImage_potato = ImageIO.read(getClass().getResourceAsStream(cursor_potato));
		cursorImage_carrot = ImageIO.read(getClass().getResourceAsStream(cursor_carrot));
		customCursor_chicken = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_chicken, new Point(0, 0), "Custom Cursor");
		customCursor_potato = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_potato, new Point(0, 0), "Custom Cursor");
		customCursor_carrot = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage_carrot, new Point(0, 0), "Custom Cursor");
		
		cursor.put("chicken", customCursor_chicken);
		cursor.put("potato", customCursor_potato);
		cursor.put("carrot", customCursor_carrot);
		cursor.put("beetroot", customCursor_beetroot);
	}
	
	private void loading_InGame() throws IOException {
		inGameImage_background = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background)));
		inGameImage_starcoin = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_starcoin)));
		inGameImage_shovel = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_shovel)));
		inGameImage_sprout = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_sprout)));
		inGameImage_wateringcan = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_wateringcan)));
		inGameImage_fertilizer = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_fertilizer)));
		inGameImage_apple = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_apple)));
		
		cropImage_potato = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_potato)));
		cropImage_carrot = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_carrot)));
		cropImage_beetroot = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_beetroot)));
	}
	
	private void loading_Field() {
		//필드맵도 카메라 넣고싶음 언젠가 하겠지 백그라운드랑 그런쪽 손보셈 ㅇㅇ.
		cropImage = new HashMap<>();
		cropImage.put(1, cropImage_potato);
		cropImage.put(2, cropImage_carrot);
		cropImage.put(3, cropImage_beetroot);
		
		jlbCropField = new ArrayList<>();
		
		for(int i = 0; i < farmData.field.size(); i++) {
			ImageIcon jlbImage = null;
			if(farmData.getField(i)[0] != 0)
				jlbImage = new ImageIcon(cropImage.get(farmData.getField(i)[0]).getSubimage(100 * farmData.getField(i)[1], 0, 100, 100));
			JLabel jlbTemp = new JLabel(jlbImage);
			jlbTemp.setBounds((280 + 110 * i) * resolution / 80, getHeight() / 2 - 70 * resolution / 80, 100, 100);
			add(jlbTemp);
			jlbCropField.add(jlbTemp);
		}
	}

	//버튼 프레스 버그 관련 변수. -> 버튼 범위에서 나가면 클릭 안됨.
	boolean btnStartPress = false,
			btnDescPress = false,
			btnSettPress = false;
	
	private void paintMainLobby_Component() throws IOException {
		this.removeAll();
		this.setLayout(null);
		setCursor("chicken");
		
		Point btnSize = new Point();
		btnSize.x = 250; btnSize.y = 80;
		
		JButton btnStart = new JButton(new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 0, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
		btnStart.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				btnStart.setIcon(new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 125*2, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				btnStartPress = true;
			}

			public void mouseReleased(MouseEvent e) {
				if(btnStartPress) {
					pa_mainLobby = false;
					pa_inGame = true;
					pa_inGameComponent = true;
					la_inGame = true;
				}
				mouseClickEffect = 0;
				mouseClick.x = btnStart.getX() + e.getPoint().x;
				mouseClick.y = btnStart.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				btnStart.setIcon(new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 125*1, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				//기본 이미지
				btnStart.setIcon(new ImageIcon(mainLobbyImage_buttonstart.getSubimage(0, 0, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				btnStartPress = false;
			}
		});
//		btnStart.setFont(font);
		btnStart.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
						   getHeight() / 2 + (btnSize.y - 65) * resolution / 80,
						   btnSize.x * resolution / 80,
						   btnSize.y * resolution / 80);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		add(btnStart);
		
		JButton btnDesc = new JButton(new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 0, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
		btnDesc.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				btnDesc.setIcon(new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 125*2, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				btnDescPress = true;	
			}

			public void mouseReleased(MouseEvent e) {
				if(btnDescPress) {

				}
				mouseClickEffect = 0;
				mouseClick.x = btnDesc.getX() + e.getPoint().x;
				mouseClick.y = btnDesc.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				btnDesc.setIcon(new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 125*1, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				//기본 이미지
				btnDesc.setIcon(new ImageIcon(mainLobbyImage_buttondesc.getSubimage(0, 0, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				btnDescPress = false;
			}
		});
//		btnDesc.setFont(font);
		btnDesc.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
						  getHeight() / 2 + (btnSize.y + 35) * resolution / 80,
						  btnSize.x * resolution / 80,
						  btnSize.y * resolution / 80);
		btnDesc.setBorderPainted(false);
		btnDesc.setContentAreaFilled(false);
		add(btnDesc);
		
		JButton btnSett = new JButton(new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 0, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
		btnSett.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				btnSett.setIcon(new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 125*2, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				btnSettPress = true;
			}

			public void mouseReleased(MouseEvent e) {
				if(btnSettPress) {
					
				}
				mouseClickEffect = 0;
				mouseClick.x = btnSett.getX() + e.getPoint().x;
				mouseClick.y = btnSett.getY() + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				btnSett.setIcon(new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 125*1, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
			}

			public void mouseExited(MouseEvent e) {
				//기본 이미지
				btnSett.setIcon(new ImageIcon(mainLobbyImage_buttonsett.getSubimage(0, 0, 352, 112).getScaledInstance(btnSize.x * resolution / 80, btnSize.y * resolution / 80, Image.SCALE_SMOOTH)));
				btnSettPress = false;
			}
		});
//		btnSett.setFont(font);
		btnSett.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
						  getHeight() / 2 + (btnSize.y + 135) * resolution / 80,
						  btnSize.x * resolution / 80,
						  btnSize.y * resolution / 80);
		btnSett.setBorderPainted(false);
		btnSett.setContentAreaFilled(false);
		add(btnSett);
	}
	
	private void paintInGame() throws IOException {
		setValue();
		
		if(la_inGame) {
			loading_InGame();
			loading_Field();
			refreshField();
			la_inGame = false;
		}
		if(pa_inGameComponent) {
			paintInGame_Component();
			pa_inGameComponent = false;
		}
		bufferGraphics.drawImage(inGameImage_background, 0, 0, getWidth(), getHeight(), null);
		
//		//중앙점
//		bufferGraphics.setColor(Color.black);
//		bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
	}
	
	//버튼 프레스 버그 관련 변수. -> 버튼 범위에서 나가면 클릭 안됨.
	boolean jlbShovelPress = false,
			jlbSproutPress = false,
			jlbWateringCanPress = false,
			jlbFertilizerPress = false,
			jlbApplePress = false;
	
	private void paintInGame_Component() throws IOException {
		this.removeAll();
		this.setLayout(null);
		
//		CircleButton btnHelp = new CircleButton("?");
//		btnHelp.addMouseListener(new MouseListener() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//			}
//
//			public void mousePressed(MouseEvent e) {
//				
//			}
//
//			public void mouseReleased(MouseEvent e) {
//				mouseClickEffect = 0;
//				mouseClick.x = btnHelp.getBounds().x + e.getPoint().x;
//				mouseClick.y = btnHelp.getBounds().y + e.getPoint().y;
//				repaint();
//			}
//
//			public void mouseEntered(MouseEvent e) {
//				btnHelp.setBackground(Color.LIGHT_GRAY);
//			}
//
//			public void mouseExited(MouseEvent e) {
//				btnHelp.setBackground(Color.white);
//			}
//		});
////		btnStart.setFont(font);
//		btnHelp.setBounds(getWidth() - (25 + 50) * resolution / 80 - 1,
//						  10 * resolution / 80,
//						  50 * resolution / 80,
//						  50 * resolution / 80);
//		btnHelp.setBackground(Color.white);
//		add(btnHelp);
		
		addStarCoin();
		addField();
		
		int labelImageSize = 200;
		int labelImageGap = 40;
		
		//삽
		JLabel jlbShovel = new JLabel(new ImageIcon(inGameImage_shovel.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbShovel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbShovel.setIcon(new ImageIcon(inGameImage_shovel.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbShovelPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbShovelPress) {
					paintShovel();
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbShovel.getX() + e.getPoint().x;
				mouseClick.y = jlbShovel.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				jlbShovel.setIcon(new ImageIcon(inGameImage_shovel.getSubimage(236, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				//기본 이미지
				jlbShovel.setIcon(new ImageIcon(inGameImage_shovel.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbShovelPress = false;
			}
		});
		jlbShovel.setBounds(getWidth() / 2 - (labelImageSize / 2 + labelImageSize * 2 + labelImageGap * 2) * resolution / 80 - 1,
							getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1,
							labelImageSize * resolution / 80,
							labelImageSize * resolution / 80);
		add(jlbShovel);
		
		//새싹
		JLabel jlbSprout = new JLabel(new ImageIcon(inGameImage_sprout.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbSprout.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbSprout.setIcon(new ImageIcon(inGameImage_sprout.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbSproutPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbSproutPress) {
					paintSprout();
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSprout.getX() + e.getPoint().x;
				mouseClick.y = jlbSprout.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				jlbSprout.setIcon(new ImageIcon(inGameImage_sprout.getSubimage(236, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				//기본 이미지
				jlbSprout.setIcon(new ImageIcon(inGameImage_sprout.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbSproutPress = false;
			}
		});
		jlbSprout.setBounds(getWidth() / 2 - (labelImageSize / 2 + labelImageSize * 1 + labelImageGap * 1) * resolution / 80 - 1,
							 getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1,
							 labelImageSize * resolution / 80,
							 labelImageSize * resolution / 80);
		add(jlbSprout);
		
		//물뿌리개
		JLabel jlbWateringCan = new JLabel(new ImageIcon(inGameImage_wateringcan.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbWateringCan.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbWateringCan.setIcon(new ImageIcon(inGameImage_wateringcan.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbWateringCanPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbWateringCanPress) {
					paintWateringCan();
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbWateringCan.getX() + e.getPoint().x;
				mouseClick.y = jlbWateringCan.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				jlbWateringCan.setIcon(new ImageIcon(inGameImage_wateringcan.getSubimage(236, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				//기본 이미지
				jlbWateringCan.setIcon(new ImageIcon(inGameImage_wateringcan.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbWateringCanPress = false;
			}
		});
		jlbWateringCan.setBounds(getWidth() / 2 - (labelImageSize / 2) * resolution / 80 - 1,
								  getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1,
								  labelImageSize * resolution / 80,
								  labelImageSize * resolution / 80);
		add(jlbWateringCan);
		
		//비료
		JLabel jlbFertilizer = new JLabel(new ImageIcon(inGameImage_fertilizer.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbFertilizer.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_fertilizer.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbFertilizerPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbFertilizerPress) {
			
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbFertilizer.getX() + e.getPoint().x;
				mouseClick.y = jlbFertilizer.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_fertilizer.getSubimage(236, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				//기본 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_fertilizer.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbFertilizerPress = false;
			}
		});
		jlbFertilizer.setBounds(getWidth() / 2 + (labelImageSize / 2 + labelImageGap * 1) * resolution / 80 - 1,
								 getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1,
								 labelImageSize * resolution / 80,
								 labelImageSize * resolution / 80);
		add(jlbFertilizer);
		
		//사과
		JLabel jlbApple = new JLabel(new ImageIcon(inGameImage_apple.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbApple.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbApplePress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbApplePress) {
			
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbApple.getX() + e.getPoint().x;
				mouseClick.y = jlbApple.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(236, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				//기본 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbApplePress = false;
			}
			
		});
		jlbApple.setBounds(getWidth() / 2 + (labelImageSize / 2 + labelImageSize + labelImageGap * 2) * resolution / 80 - 1,
						   getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1,
						   labelImageSize * resolution / 80,
						   labelImageSize * resolution / 80);
		add(jlbApple);
	}
	
	private void addStarCoin() {
		//지갑 숫자
		JLabel jlbStarCoinValue = new JLabel(farmData.getCoin());
		jlbStarCoinValue.setFont(font);
		jlbStarCoinValue.setHorizontalAlignment(SwingConstants.LEFT);
		jlbStarCoinValue.setBounds(getWidth() - 235 * resolution / 80 - 1,
								   20 * resolution / 80,
								   160 * resolution / 80,
								   50 * resolution / 80);
		add(jlbStarCoinValue);
		
		//지갑
		JLabel jlbStarCoin = new JLabel(new ImageIcon(inGameImage_starcoin.getScaledInstance(250 * resolution / 80, 115 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbStarCoin.setBounds(getWidth() - 260 * resolution / 80 - 1,
							  0,
							  250 * resolution / 80,
							  115 * resolution / 80);
		add(jlbStarCoin);
		
		
	}
	
	ArrayList<JLabel> jlbCropField;
	Map<Integer, BufferedImage> cropImage;
	
	private void addField() {
		for(int i = 0; i < jlbCropField.size(); i++) {
			add(jlbCropField.get(i));
		}
	}
	
	private void refreshField() {
		for(int i = 0; i < jlbCropField.size(); i++) {
			ImageIcon jlbImage = null;
			if(farmData.getField(i)[0] != 0)
				jlbImage = new ImageIcon(cropImage.get(farmData.getField(i)[0]).getSubimage(100 * farmData.getField(i)[1], 0, 100, 100));
			
			JLabel jlbTemp = jlbCropField.get(i);
			jlbTemp.setIcon(jlbImage);
			int k = i;
			if(jlbTemp.getMouseListeners().length == 0) {
				if(farmData.getField(i)[1] == 4 && farmData.getField(i)[0] != 0) {
					jlbTemp.addMouseListener(new MouseListener() {
						public void mouseClicked(MouseEvent e) {
						}
						public void mousePressed(MouseEvent e) {
						}
						public void mouseReleased(MouseEvent e) {
							farmData.setCrop(getCropName(farmData.getField(k)[0]), 2, true);
							farmData.setField(k, new int[] {0, 0});
							refreshCount();
							refreshField();
							jlbTemp.removeMouseListener(this);
							
							mouseClickEffect = 0;
							mouseClick.x = jlbTemp.getX() + e.getPoint().x;
							mouseClick.y = jlbTemp.getY() + e.getPoint().y;
							repaint();
						}
						public void mouseEntered(MouseEvent e) {
						}
						public void mouseExited(MouseEvent e) {
						}
					});
				} else if(farmData.getField(i)[0] == 0) {
					jlbTemp.addMouseListener(new MouseListener() {
						public void mouseClicked(MouseEvent e) {
						}
						public void mousePressed(MouseEvent e) {
						}
						public void mouseReleased(MouseEvent e) {
							if(currentCrop != 0 && farmData.getCrop(getCropName(currentCrop)) > 0) {
								farmData.setCrop(getCropName(currentCrop), 1, false);
								System.out.println("씨앗 소모");
								farmData.setField(k, new int[] {currentCrop, 0});
								refreshCount();
								refreshField();
								jlbTemp.removeMouseListener(this);
							}
							
							mouseClickEffect = 0;
							mouseClick.x = jlbTemp.getX() + e.getPoint().x;
							mouseClick.y = jlbTemp.getY() + e.getPoint().y;
							repaint();
						}
						public void mouseEntered(MouseEvent e) {
						}
						public void mouseExited(MouseEvent e) {
						}
					});
				}
			}
		}
	}
	
	//기준점
	JLabel jlbPoint;
	//작물 라벨 - 감자, 당근, 비트
	JLabel jlbPotato,		jlbPotatoText,		jlbPotatoCount,		jlbPotatoClick,
		   jlbCarrot,		jlbCarrotText,		jlbCarrotCount,		jlbCarrotClick,
		   jlbBeetroot,		jlbBeetrootText,	jlbBeetrootCount,	jlbBeetrootClick;
	//뒤로가기
	JLabel jlbBack, 		jlbBackText,							jlbBackClick;
	
	//위치
	int x_back_sh,	x_back_sp,
		y_image,	y_text,		y_count,	y_click;
	//크기 변수
	int jlb_size, jlb_click_x_size, jlb_click_y_size;
	//버튼 변수
	boolean jlbPotatoClickPress = false,
			jlbCarrotClickPress = false,
			jlbBeetrootClickPress = false;
	boolean jlbBackClickPress = false;
	//작물 위치
	int x_potato,	x_carrot,	x_beetroot;
	int count, sprout_camera_max;
	
	//현재마우스 변수
	int currentCrop = 0;
	
	private void setValue() {
		//x
		//삽
		count = 0;
		x_shovel = (50 + 125 * count) * resolution / 80; count++;
		x_hoe = (50 + 125 * count) * resolution / 80; count++;
		x_back_sh = (50 + 125 * count) * resolution / 80;
		//새싹
		count = 0;
		x_potato = (50 + 125 * count) * resolution / 80; count++;
		x_carrot = (50 + 125 * count) * resolution / 80; count++;
		x_beetroot = (50 + 125 * count) * resolution / 80; count++;
		x_back_sp = (50 + 125 * count) * resolution / 80;
		//걍 변수
		sprout_camera_max = (1280 - (50 * 2 + 125 * count + 100)) * resolution / 80;
		
		//y
		y_image = getHeight() / 2 + 130 * resolution / 80;
		y_text  = getHeight() / 2 + 180 * resolution / 80;
		y_count = getHeight() / 2 + 210 * resolution / 80;
		y_click = getHeight() / 2 + 130 * resolution / 80;
		
		//사이즈
		jlb_size = 100 * resolution / 80;
		jlb_click_x_size = 120 * resolution / 80;
		jlb_click_y_size = 180 * resolution / 80;
	}
	
	/*
	 삽 시작
	 */
	
	JLabel jlbShovel,	jlbShovelText,	jlbShovelClick,
		   jlbHoe,		jlbHoeText,		jlbHoeClick;
	
	int x_shovel,	x_hoe;
	
	boolean jlbShovelBackPress = false;
	
	private void paintShovel() {
		this.removeAll();
		addStarCoin();
		addField();
		
		JLabel jlbShovelBack = new JLabel("뒤로가기");
		jlbShovelBack.setFont(font);
		jlbShovelBack.setHorizontalAlignment(SwingConstants.CENTER);
		jlbShovelBack.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbShovelBackPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbShovelBackPress) {
					try {
						paintInGame_Component();
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
				jlbShovelBackPress = false;
			}
		});
		jlbShovelBack.setBounds(getWidth() / 2 - 50 * resolution / 80 - 1, getHeight() - 160 * resolution / 80, 100, 30);
		jlbShovelBack.setBorder(new LineBorder(Color.black, 1));
		add(jlbShovelBack);
		
//		jlbShovel = new JLabel("");
//		jlbShovel.setBounds(x_shovel, y_image, jlb_size, jlb_size);
//		add(jlbShovel);
//		
//		jlbShovelText = new JLabel("땅 파기");
//		jlbShovelText.setFont(font);
//		jlbShovelText.setHorizontalAlignment(SwingConstants.CENTER);
//		jlbShovelText.setBounds(x_shovel, y_text, jlb_size, jlb_size);
//		add(jlbShovelText);
//		
//		jlbShovelClick = new JLabel();
//		jlbShovelClick.addMouseListener(new MouseListener() {
//			public void mouseClicked(MouseEvent e) {
//			}
//			public void mousePressed(MouseEvent e) {
//			}
//			public void mouseReleased(MouseEvent e) {
//				mouseClickEffect = 0;
//				mouseClick.x = jlbShovelClick.getX() + e.getPoint().x;
//				mouseClick.y = jlbShovelClick.getY() + e.getPoint().y;
//				repaint();
//			}
//			public void mouseEntered(MouseEvent e) {
//			}
//			public void mouseExited(MouseEvent e) {
//			}
//		});
//		jlbShovelClick.setBounds(x_shovel - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
//		jlbShovelClick.setBorder(new LineBorder(Color.black, 1));
//		add(jlbShovelClick);
//		
//		jlbHoe = new JLabel("");
//		jlbHoe.setBounds(x_hoe, y_image, jlb_size, jlb_size);
//		add(jlbHoe);
//		
//		jlbHoeText = new JLabel("밭 갈기");
//		jlbHoeText.setFont(font);
//		jlbHoeText.setHorizontalAlignment(SwingConstants.CENTER);
//		jlbHoeText.setBounds(x_hoe, y_text, jlb_size, jlb_size);
//		add(jlbHoeText);
//		
//		jlbHoeClick = new JLabel();
//		jlbHoeClick.addMouseListener(new MouseListener() {
//			public void mouseClicked(MouseEvent e) {
//			}
//			public void mousePressed(MouseEvent e) {
//			}
//			public void mouseReleased(MouseEvent e) {
//				mouseClickEffect = 0;
//				mouseClick.x = jlbHoeClick.getX() + e.getPoint().x;
//				mouseClick.y = jlbHoeClick.getY() + e.getPoint().y;
//				repaint();
//			}
//			public void mouseEntered(MouseEvent e) {
//			}
//			public void mouseExited(MouseEvent e) {
//			}
//		});
//		jlbHoeClick.setBounds(x_hoe - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
//		jlbHoeClick.setBorder(new LineBorder(Color.black, 1));
//		add(jlbHoeClick);
//		
//		//뒤로가기
////		jlbBack = new JLabel(new ImageIcon(cropImage_back.getSubimage(0, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
//		jlbBack = new JLabel();
//		jlbBack.setBounds(x_back_sh, y_image, jlb_size, jlb_size);
//		add(jlbBack);
//		
//		jlbBackText = new JLabel("뒤로가기");
//		jlbBackText.setFont(font);
//		jlbBackText.setHorizontalAlignment(SwingConstants.CENTER);
//		jlbBackText.setBounds(x_back_sh, y_text, jlb_size, jlb_size);
//		add(jlbBackText);
//		
//		jlbBackClick = new JLabel();
//		jlbBackClick.addMouseListener(new MouseListener() {
//			public void mouseClicked(MouseEvent e) {
//			}
//			public void mousePressed(MouseEvent e) {
//				jlbBackClickPress = true;
//			}
//			public void mouseReleased(MouseEvent e) {
//				if(jlbBackClickPress) {
//					try {
//						paintInGame_Component();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//				mouseClickEffect = 0;
//				mouseClick.x = jlbBackClick.getX() + e.getPoint().x;
//				mouseClick.y = jlbBackClick.getY() + e.getPoint().y;
//				repaint();
//			}
//			public void mouseEntered(MouseEvent e) {
//			}
//			public void mouseExited(MouseEvent e) {
//				jlbBackClickPress = false;
//			}
//		});
//		jlbBackClick.setBounds(x_back_sh - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
//		jlbBackClick.setBorder(new LineBorder(Color.black, 1));
//		add(jlbBackClick);
	}
	
	/*
	 삽 종료
	 */

	/*
	 새싹 시작
	*/
	
	private void paintSprout() {
		this.removeAll();
		addStarCoin();
		addField();
		
		//이동 제어용 라벨
		jlbPoint = new JLabel();
		jlbPoint.setBounds(0, 0, 0, 0);
		
		//감자
		jlbPotato = new JLabel(new ImageIcon(cropImage_potato.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbPotato.setBounds(x_potato, y_image, jlb_size, jlb_size);
		add(jlbPotato);
		
		jlbPotatoText = new JLabel("감자");
		jlbPotatoText.setFont(font);
		jlbPotatoText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPotatoText.setBounds(x_potato, y_text, jlb_size, jlb_size);
		add(jlbPotatoText);
		
		jlbPotatoCount = new JLabel("보유개수: " + farmData.getCrop("Potato") + "개");
		jlbPotatoCount.setFont(font_count);
		jlbPotatoCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbPotatoCount.setBounds(x_potato, y_count, jlb_size, jlb_size);
		add(jlbPotatoCount);
		
		jlbPotatoClick = new JLabel();
		jlbPotatoClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbPotatoClickPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbPotatoClickPress) {
					setCursor("potato");
					currentCrop = 1;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbPotatoClick.getX() + e.getPoint().x;
				mouseClick.y = jlbPotatoClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				jlbPotatoClickPress = false;
			}
		});
		jlbPotatoClick.setBounds(x_potato - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbPotatoClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbPotatoClick);
		
		//당근
		jlbCarrot = new JLabel(new ImageIcon(cropImage_carrot.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCarrot.setBounds(x_carrot, y_image, jlb_size, jlb_size);
		add(jlbCarrot);
		
		jlbCarrotText = new JLabel("당근");
		jlbCarrotText.setFont(font);
		jlbCarrotText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCarrotText.setBounds(x_carrot, y_text, jlb_size, jlb_size);
		add(jlbCarrotText);
		
		jlbCarrotCount = new JLabel("보유개수: " + farmData.getCrop("Carrot") + "개");
		jlbCarrotCount.setFont(font_count);
		jlbCarrotCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbCarrotCount.setBounds(x_carrot, y_count, jlb_size, jlb_size);
		add(jlbCarrotCount);
		
		jlbCarrotClick = new JLabel();
		jlbCarrotClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbCarrotClickPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbCarrotClickPress) {
					setCursor("carrot");
					currentCrop = 2;
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbCarrotClick.getX() + e.getPoint().x;
				mouseClick.y = jlbCarrotClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				jlbCarrotClickPress = false;
			}
		});
		jlbCarrotClick.setBounds(x_carrot - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbCarrotClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbCarrotClick);
		
		//비트
//		비트 이거 이미지 가져오기
//		jlbBeetroot = new JLabel(new ImageIcon(cropImage_beetroot.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBeetroot = new JLabel();
		jlbBeetroot.setBounds(x_beetroot, y_image, jlb_size, jlb_size);
		add(jlbBeetroot);
		
		jlbBeetrootText = new JLabel("비트");
		jlbBeetrootText.setFont(font);
		jlbBeetrootText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBeetrootText.setBounds(x_beetroot, y_text, jlb_size, jlb_size);
		add(jlbBeetrootText);
		
		jlbBeetrootCount = new JLabel("보유개수: " + farmData.getCrop("Beetroot") + "개");
		jlbBeetrootCount.setFont(font_count);
		jlbBeetrootCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbBeetrootCount.setBounds(x_beetroot, y_count, jlb_size, jlb_size);
		add(jlbBeetrootCount);
		
		jlbBeetrootClick = new JLabel();
		jlbBeetrootClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbBeetrootClickPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbBeetrootClickPress) {
					
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbBeetrootClick.getX() + e.getPoint().x;
				mouseClick.y = jlbBeetrootClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				jlbBeetrootClickPress = false;
			}
		});
		jlbBeetrootClick.setBounds(x_beetroot - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbBeetrootClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbBeetrootClick);
		
		//뒤로가기
//		jlbBack = new JLabel(new ImageIcon(cropImage_back.getSubimage(0, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBack = new JLabel();
		jlbBack.setBounds(x_back_sp, y_image, jlb_size, jlb_size);
		add(jlbBack);
		
		jlbBackText = new JLabel("뒤로가기");
		jlbBackText.setFont(font);
		jlbBackText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBackText.setBounds(x_back_sp, y_text, jlb_size, jlb_size);
		add(jlbBackText);
		
		jlbBackClick = new JLabel();
		jlbBackClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbBackClickPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbBackClickPress) {
					setCursor("chicken");
					try {
						paintInGame_Component();
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
			}
			public void mouseExited(MouseEvent e) {
				jlbBackClickPress = false;
			}
		});
		jlbBackClick.setBounds(x_back_sp - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbBackClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbBackClick);
		
		// [0 - 마우스 x축 움직인 정도, 1 - 마우스 초기 x좌표, 2 - 컴포넌트 초기 x좌표]
		int[] currentCamera = new int[] {0, 0, 0};
		
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
		mouseControl.setBounds(0,
							   getHeight() / 2 + 113 * resolution / 80,
							   getWidth(),
							   220 * resolution / 80);
		add(mouseControl);
	}

	//카메라 무빙~
	private void setComponentBoundSprout(int[] currentCamera, MouseEvent e) {
		if(jlbPoint.getX() > 0 || currentCamera[2] + currentCamera[0] > 0) {
			jlbPoint.setBounds(0, 0, 0, 0);
			currentCamera[1] = e.getX();
			currentCamera[2] = 0;
		} else if(jlbPoint.getX() < sprout_camera_max || currentCamera[2] + currentCamera[0] < sprout_camera_max) {
			if(sprout_camera_max > 0) {
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
	 새싹 종료
	 */
	
	/*
	 물 뿌리개 시작
	 */
	private void paintWateringCan() {
		this.removeAll();
		addStarCoin();
		addField();
		
		JLabel jlbWateringCan = new JLabel("뒤로가기");
		jlbWateringCan.setFont(font);
		jlbWateringCan.setHorizontalAlignment(SwingConstants.CENTER);
		jlbWateringCan.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				jlbWateringCanPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbWateringCanPress) {
					try {
						paintInGame_Component();
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
				jlbWateringCanPress = false;
			}
		});
		jlbWateringCan.setBounds(getWidth() / 2 - 50 * resolution / 80 - 1, getHeight() - 160 * resolution / 80, 100, 30);
		jlbWateringCan.setBorder(new LineBorder(Color.black, 1));
		add(jlbWateringCan);
	}
	
	/*
	 물 뿌리개 종료
	 */
	
	protected void setCursor(String key) {
		this.setCursor(cursor.get(key));
	}
	
	protected String getCropName(int id) {
		switch(id) {
			case 1:
				return "Potato";
			case 2:
				return "Carrot";
			case 3:
				return "Beetroot";
		}
		return "";
	}
	
	protected void refreshCount() {
		if(jlbPotatoCount != null) {
			jlbPotatoCount.setText("보유개수: " + farmData.getCrop("Potato") + "개");
			jlbCarrotCount.setText("보유개수: " + farmData.getCrop("Carrot") + "개");
			jlbBeetrootCount.setText("보유개수: " + farmData.getCrop("Beetroot") + "개");
		}
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
}