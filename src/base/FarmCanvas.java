package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class FarmCanvas extends JPanel implements Runnable, MouseListener {
	protected final String logo = "/resource/mainLobby/logo.png";
	
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
			paintMainLobby();
		}
		
		if(pa_inGame) {
			paintInGame();
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

	private void paintMainLobby() {
		if(pa_mainLobbyComponent) {
			paintMainLobby_Component();
			pa_mainLobbyComponent = false;
		}
		
		Image image;
		try {
			image = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(logo)));
		} catch (IOException e) {
			image = null;
			e.printStackTrace();
		}
		bufferGraphics.drawImage(image,
								 getWidth() / 2 - 500 * resolution / 80 / 2 - 1,
								 50 * resolution / 80,
								 500 * resolution / 80,
								 260 * resolution / 80,
								 null);
		
		//중앙점
		bufferGraphics.setColor(Color.black);
		bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
	}
	
	private void paintMainLobby_Component() {
		this.removeAll();
		this.setLayout(null);
		
		Point btnSize = new Point();
		btnSize.x = 200; btnSize.y = 50;
		
		JButton btnStart = new JButton("게임 시작");
		btnStart.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				pa_mainLobby = false;
				pa_inGame = true;
				pa_inGameComponent = true;
				mouseClickEffect = 0;
				mouseClick.x = btnStart.getBounds().x + e.getPoint().x;
				mouseClick.y = btnStart.getBounds().y + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
            	btnStart.setBackground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				btnStart.setBackground(Color.white);
			}
		});
//		btnStart.setFont(font);
		btnStart.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
						   getHeight() / 2 + btnSize.y,
						   btnSize.x * resolution / 80,
						   btnSize.y * resolution / 80);
		btnStart.setBorder(new RoundedBorder(5));
		btnStart.setBackground(Color.white);
		add(btnStart);
		
		JButton btnDesc = new JButton("농장 설명");
		btnDesc.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = btnDesc.getBounds().x + e.getPoint().x;
				mouseClick.y = btnDesc.getBounds().y + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				btnDesc.setBackground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				btnDesc.setBackground(Color.white);
			}
		});
//		btnDesc.setFont(font);
		btnDesc.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
						  getHeight() / 2 + btnSize.y + 75 * resolution / 80,
						  btnSize.x * resolution / 80,
						  btnSize.y * resolution / 80);
		btnDesc.setBorder(new RoundedBorder(5));
		btnDesc.setBackground(Color.white);
		add(btnDesc);
		
		JButton btnSett = new JButton("설정");
		btnSett.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = btnSett.getBounds().x + e.getPoint().x;
				mouseClick.y = btnSett.getBounds().y + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				btnSett.setBackground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				btnSett.setBackground(Color.white);
			}
		});
//		btnSett.setFont(font);
		btnSett.setBounds(getWidth() / 2 - btnSize.x * resolution / 80 / 2 - 1,
						  getHeight() / 2 + btnSize.y + 150 * resolution / 80,
						  btnSize.x * resolution / 80,
						  btnSize.y * resolution / 80);
		btnSett.setBorder(new RoundedBorder(5));
		btnSett.setBackground(Color.white);
		add(btnSett);
	}
	
	private void paintInGame() {
		if(pa_inGameComponent) {
			paintInGame_Component();
			pa_inGameComponent = false;
		}
		
		//중앙점
		bufferGraphics.setColor(Color.black);
		bufferGraphics.drawRect(getWidth() / 2, getHeight() / 2, 1, 1);
	}
	
	private void paintInGame_Component() {
		this.removeAll();
		this.setLayout(null);
		
		CircleButton btnHelp = new CircleButton("?");
		btnHelp.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				mouseClickEffect = 0;
				mouseClick.x = btnHelp.getBounds().x + e.getPoint().x;
				mouseClick.y = btnHelp.getBounds().y + e.getPoint().y;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				btnHelp.setBackground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				btnHelp.setBackground(Color.white);
			}
		});
//		btnStart.setFont(font);
		btnHelp.setBounds(getWidth() - (25 + 50) * resolution / 80 - 1,
						  10 * resolution / 80,
						  50 * resolution / 80,
						  50 * resolution / 80);
		btnHelp.setBackground(Color.white);
		add(btnHelp);
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
