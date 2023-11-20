package base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
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
	
	protected Font font;
	
	//mainLobby
	//그림 관련 변수
	protected final String   //mainLobby_logo 	    = "/resource/mainLobby/logo.png",
						     mainLobby_background   = "/resource/mainLobby/background.png",
						     mainLobby_chicken 	    = "/resource/mainLobby/chicken.png",
						     mainLobby_singsing		= "/resource/mainLobby/singsing.png",
							 mainLobby_buttonstart = "/resource/mainLobby/buttonstart.png",
							 mainLobby_buttondesc = "/resource/mainLobby/buttondesc.png",
							 mainLobby_buttonsett = "/resource/mainLobby/buttonsett.png";
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
							mainLobbyImage_buttonsett;
	protected Image[] mainLobbyImage_logo,
					  mainLobbyImage_cloud1,
					  mainLobbyImage_cloud2;
	
	//그림 상태
	protected int mainLobby_logo_index = new Random().nextInt(0, 3),
				  mainLobby_cloud1_stat = 0,
				  mainLobby_cloud1_index = 0,
			      mainLobby_cloud2_stat = 0,
			      mainLobby_cloud2_index = 0;
	
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
						   crop_carrot = "/resource/inGame/crop/carrot.png";
	protected BufferedImage cropImage_potato,
							cropImage_carrot;
	
	protected Thread worker;
	protected Point mouseClick = new Point();
	
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
					if(mainLobby_cloud1_stat < 14) mainLobby_cloud1_stat++;
					else mainLobby_cloud1_stat = 0;
					if(mainLobby_cloud2_stat < 14) mainLobby_cloud2_stat++;
					else mainLobby_cloud2_stat = 0;
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
			mainLobbyLoading();
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
		
		//구름1
		switch(mainLobby_cloud1_stat) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				mainLobby_cloud1_index = 0;
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				mainLobby_cloud1_index = 1;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				mainLobby_cloud1_index = 2;
				break;
		}
		bufferGraphics.drawImage(mainLobbyImage_cloud1[mainLobby_cloud1_index],
								 50 * resolution / 80 - 1,
								 50 * resolution / 80,
								 240 * resolution / 80,
								 180 * resolution / 80,
								 null);
		
		//구름2
		switch(mainLobby_cloud2_stat) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				mainLobby_cloud2_index = 0;
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				mainLobby_cloud2_index = 1;;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				mainLobby_cloud2_index = 2;
				break;
		}
		bufferGraphics.drawImage(mainLobbyImage_cloud2[mainLobby_cloud2_index],
								 getWidth() - 300 * resolution / 80 - 1,
								 65 * resolution / 80,
								 250 * resolution / 80,
								 190 * resolution / 80,
								 null);
		
//		//중앙점
//		bufferGraphics.setColor(Color.black);
//		bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
	}
	
	private void mainLobbyLoading() throws IOException {
		mainLobbyImage_background = 		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_background)));
//		mainLobbyImage_logo = 				 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo)));
		mainLobbyImage_chicken = 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_chicken)));
		mainLobbyImage_singsing = 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_singsing)));
		mainLobbyImage_buttonstart =		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttonstart)));
		mainLobbyImage_buttondesc =			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttondesc)));
		mainLobbyImage_buttonsett =			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_buttonsett)));
		mainLobbyImage_logo = new Image[]   {ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[0]))),
											 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[1]))),
											 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_logo[2])))};
		mainLobbyImage_cloud1 = new Image[] {ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[0]))),
								 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[1]))),
								 			 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud1[2])))};
		mainLobbyImage_cloud2 = new Image[] {ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[0]))),
				            	 	 		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[1]))),
				            	 	 		 ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(mainLobby_cloud2[2])))};
	}
	
	//버튼 프레스 버그 관련 변수. -> 버튼 범위에서 나가면 클릭 안됨.
	boolean btnStartPress = false,
			btnDescPress = false,
			btnSettPress = false;
	
	private void paintMainLobby_Component() throws IOException {
		this.removeAll();
		this.setLayout(null);
		
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
			inGameLoading();
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
	
	private void inGameLoading() throws IOException {
		inGameImage_background = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_background)));
		inGameImage_starcoin = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_starcoin)));
		inGameImage_shovel = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_shovel)));
		inGameImage_sprout = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_sprout)));
		inGameImage_wateringcan = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_wateringcan)));
		inGameImage_fertilizer = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_fertilizer)));
		inGameImage_apple = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_apple)));
		
		cropImage_potato = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(crop_potato)));
	}
	
	private void addStarCoin() {
		//지갑
		JLabel jlbStarCoin = new JLabel(new ImageIcon(inGameImage_starcoin.getScaledInstance(250 * resolution / 80, 115 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbStarCoin.setBounds(getWidth() - 260 * resolution / 80 - 1,
							  0,
							  250 * resolution / 80,
							  115 * resolution / 80);
		add(jlbStarCoin);
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
	boolean jlbBackClickPress = false;
	//작물 위치
	int x_potato,	x_carrot,	x_beetroot;
	int count, sprout_camera_max;
	
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
	
	private void paintShovel() {
		this.removeAll();
		addStarCoin();
		
		count = 0;
		
		jlbShovel = new JLabel("");
		jlbShovel.setBounds(x_shovel, y_image, jlb_size, jlb_size);
		add(jlbShovel);
		
		jlbShovelText = new JLabel("땅 파기");
		jlbShovelText.setFont(font);
		jlbShovelText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbShovelText.setBounds(x_shovel, y_text, jlb_size, jlb_size);
		add(jlbShovelText);
		
		jlbShovelClick = new JLabel();
		jlbShovelClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = jlbShovelClick.getX() + e.getPoint().x;
				mouseClick.y = jlbShovelClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		jlbShovelClick.setBounds(x_shovel - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbShovelClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbShovelClick);
		
		jlbHoe = new JLabel("");
		jlbHoe.setBounds(x_hoe, y_image, jlb_size, jlb_size);
		add(jlbHoe);
		
		jlbHoeText = new JLabel("밭 갈기");
		jlbHoeText.setFont(font);
		jlbHoeText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbHoeText.setBounds(x_hoe, y_text, jlb_size, jlb_size);
		add(jlbHoeText);
		
		jlbHoeClick = new JLabel();
		jlbHoeClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = jlbHoeClick.getX() + e.getPoint().x;
				mouseClick.y = jlbHoeClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		jlbHoeClick.setBounds(x_hoe - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbHoeClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbHoeClick);
		
		//뒤로가기
//		jlbBack = new JLabel(new ImageIcon(cropImage_back.getSubimage(0, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbBack = new JLabel();
		jlbBack.setBounds(x_back_sh, y_image, jlb_size, jlb_size);
		add(jlbBack);
		
		jlbBackText = new JLabel("뒤로가기");
		jlbBackText.setFont(font);
		jlbBackText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBackText.setBounds(x_back_sh, y_text, jlb_size, jlb_size);
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
					try {
						paintInGame_Component();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
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
		jlbBackClick.setBounds(x_back_sh - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbBackClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbBackClick);
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
		
		count = 0;
		
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
		jlbPotatoCount.setFont(font);
		jlbPotatoCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbPotatoCount.setBounds(x_potato, y_count, jlb_size, jlb_size);
		add(jlbPotatoCount);
		
		jlbPotatoClick = new JLabel();
		jlbPotatoClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = jlbPotatoClick.getX() + e.getPoint().x;
				mouseClick.y = jlbPotatoClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		jlbPotatoClick.setBounds(x_potato - 10 * resolution / 80, y_click, jlb_click_x_size, jlb_click_y_size);
		jlbPotatoClick.setBorder(new LineBorder(Color.black, 1));
		add(jlbPotatoClick);
		
		//당근
//		당근 이거 이미지 가져오기
//		jlbCarrot = new JLabel(new ImageIcon(cropImage_carrot.getSubimage(500, 0, 100, 100).getScaledInstance(100 * resolution / 80, 100 * resolution / 80, Image.SCALE_SMOOTH)));
		jlbCarrot = new JLabel();
		jlbCarrot.setBounds(x_carrot, y_image, jlb_size, jlb_size);
		add(jlbCarrot);
		
		jlbCarrotText = new JLabel("당근");
		jlbCarrotText.setFont(font);
		jlbCarrotText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCarrotText.setBounds(x_carrot, y_text, jlb_size, jlb_size);
		add(jlbCarrotText);
		
		jlbCarrotCount = new JLabel("보유개수: " + farmData.getCrop("Carrot") + "개");
		jlbCarrotCount.setFont(font);
		jlbCarrotCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbCarrotCount.setBounds(x_carrot, y_count, jlb_size, jlb_size);
		add(jlbCarrotCount);
		
		jlbCarrotClick = new JLabel();
		jlbCarrotClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = jlbCarrotClick.getX() + e.getPoint().x;
				mouseClick.y = jlbCarrotClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
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
		jlbBeetrootCount.setFont(font);
		jlbBeetrootCount.setHorizontalAlignment(SwingConstants.LEFT);
		jlbBeetrootCount.setBounds(x_beetroot, y_count, jlb_size, jlb_size);
		add(jlbBeetrootCount);
		
		jlbBeetrootClick = new JLabel();
		jlbBeetrootClick.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = jlbBeetrootClick.getX() + e.getPoint().x;
				mouseClick.y = jlbBeetrootClick.getY() + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
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
					try {
						paintInGame_Component();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
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