package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FarmCanvas extends JPanel implements Runnable, MouseListener {
	
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
	protected Image inGameImage_background;
	
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
	
	public FarmCanvas(int resolution) {
		this.resolution = resolution;
		addMouseListener(this);
	}

	public void start() {
		// TODO Auto-generated method stub
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(pa_inGame) {
			try {
				paintInGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
		btnSize.x = 280; btnSize.y = 85;
		
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
				mouseClick.x = btnStart.getBounds().x + e.getPoint().x;
				mouseClick.y = btnStart.getBounds().y + e.getPoint().y;
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
				mouseClick.x = btnDesc.getBounds().x + e.getPoint().x;
				mouseClick.y = btnDesc.getBounds().y + e.getPoint().y;
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
				mouseClick.x = btnSett.getBounds().x + e.getPoint().x;
				mouseClick.y = btnSett.getBounds().y + e.getPoint().y;
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
		if(pa_inGameComponent) {
			paintInGame_Component();
			pa_inGameComponent = false;
		}
		if(la_inGame) {
			inGameLoading();
			la_inGame = false;
		}
		bufferGraphics.drawImage(inGameImage_background, 0, 0, getWidth(), getHeight(), null);
		
		//중앙점
		bufferGraphics.setColor(Color.black);
		bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
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
		
		//지갑
		Image inGameImage_starcoin = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_starcoin)));
		Image inGameReImage_starcoin = inGameImage_starcoin.getScaledInstance(250 * resolution / 80, 115 * resolution / 80, Image.SCALE_SMOOTH);
		JLabel jlbStarCoin = new JLabel(new ImageIcon(inGameReImage_starcoin));
		jlbStarCoin.setBounds(getWidth() - 260 * resolution / 80 - 1,
							  0,
							  250 * resolution / 80,
							  115 * resolution / 80);
		add(jlbStarCoin);
		
		int labelImageSize = 200;
		int labelImageGap = 40;
		
		//삽
		BufferedImage inGameImage_shovel = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_shovel)));
		JLabel jlbShovel = new JLabel(new ImageIcon(inGameImage_shovel.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbShovel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbShovel.setIcon(new ImageIcon(inGameImage_shovel.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbShovelPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbShovelPress) {
			
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbShovel.getBounds().x + e.getPoint().x;
				mouseClick.y = jlbShovel.getBounds().y + e.getPoint().y;
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
		BufferedImage inGameImage_sprout = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_sprout)));
		JLabel jlbSprout = new JLabel(new ImageIcon(inGameImage_sprout.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbSprout.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbSprout.setIcon(new ImageIcon(inGameImage_sprout.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbSproutPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbSproutPress) {
			
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbSprout.getBounds().x + e.getPoint().x;
				mouseClick.y = jlbSprout.getBounds().y + e.getPoint().y;
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
		BufferedImage inGameImage_wateringcan = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_wateringcan)));
		JLabel jlbWateringCan = new JLabel(new ImageIcon(inGameImage_wateringcan.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbWateringCan.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				mouseClick.x = jlbWateringCan.getBounds().x + e.getPoint().x;
				mouseClick.y = jlbWateringCan.getBounds().y + e.getPoint().y;
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
		BufferedImage inGameImage_Fertilizer = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_fertilizer)));
		JLabel jlbFertilizer = new JLabel(new ImageIcon(inGameImage_Fertilizer.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbFertilizer.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_Fertilizer.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbFertilizerPress = true;
			}
			public void mouseReleased(MouseEvent e) {
				if(jlbFertilizerPress) {
			
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbFertilizer.getBounds().x + e.getPoint().x;
				mouseClick.y = jlbFertilizer.getBounds().y + e.getPoint().y;
				repaint();
			}
			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_Fertilizer.getSubimage(236, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}
			public void mouseExited(MouseEvent e) {
				//기본 이미지
				jlbFertilizer.setIcon(new ImageIcon(inGameImage_Fertilizer.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbFertilizerPress = false;
			}
		});
		jlbFertilizer.setBounds(getWidth() / 2 + (labelImageSize / 2 + labelImageGap * 1) * resolution / 80 - 1,
								 getHeight() - (labelImageGap + labelImageSize) * resolution / 80 - 1,
								 labelImageSize * resolution / 80,
								 labelImageSize * resolution / 80);
		add(jlbFertilizer);
		
		//사과
		BufferedImage inGameImage_apple = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(inGame_apple)));
		JLabel jlbApple = new JLabel(new ImageIcon(inGameImage_apple.getSubimage(0, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
		jlbApple.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//클릭 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(236 * 2, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
				jlbApplePress = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(jlbApplePress) {
			
				}
				mouseClickEffect = 0;
				mouseClick.x = jlbApple.getBounds().x + e.getPoint().x;
				mouseClick.y = jlbApple.getBounds().y + e.getPoint().y;
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//오버랩 이미지
				jlbApple.setIcon(new ImageIcon(inGameImage_apple.getSubimage(236, 0, 236, 236).getScaledInstance(labelImageSize * resolution / 80, labelImageSize * resolution / 80, Image.SCALE_SMOOTH)));
			}

			@Override
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
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseClickEffect = 0;
		mouseClick.x = e.getPoint().x;
		mouseClick.y = e.getPoint().y;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

