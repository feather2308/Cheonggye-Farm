package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class FarmCanvas extends JPanel implements Runnable, MouseListener {
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
		switch(mouseClickEffect) {
		case 0:
			bufferGraphics.drawOval(mouseClick.x - mouseClickEffectSize[0]/2,
					   				mouseClick.y - mouseClickEffectSize[0]/2,
					   				mouseClickEffectSize[0],
					   				mouseClickEffectSize[0]);
			break;
		case 1:
			bufferGraphics.drawOval(mouseClick.x - mouseClickEffectSize[1]/2,
					   				mouseClick.y - mouseClickEffectSize[1]/2,
					   				mouseClickEffectSize[1],
					   				mouseClickEffectSize[1]);
			break;
		case 2:
			bufferGraphics.drawOval(mouseClick.x - mouseClickEffectSize[2]/2,
					   				mouseClick.y - mouseClickEffectSize[2]/2,
					   				mouseClickEffectSize[2],
					   				mouseClickEffectSize[2]);
			break;
		case 3:
			bufferGraphics.drawOval(mouseClick.x - mouseClickEffectSize[3]/2,
					   				mouseClick.y - mouseClickEffectSize[3]/2,
					   				mouseClickEffectSize[3],
					   				mouseClickEffectSize[3]);
			break;
		}
		
	    g.drawImage(offscreen, 0, 0, null);
	}

	private void paintMainLobby() {
		if(pa_mainLobbyComponent) {
			paintMainLobby_Component();
			pa_mainLobbyComponent = false;
		}
	}
	
	private void paintMainLobby_Component() {
		JButton btnStart = new JButton("시작");
		btnStart.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				pa_mainLobby = false;
				pa_inGame = true;
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
		btnStart.setBounds(290, 226, 65, 25);
		btnStart.setBackground(Color.white);
		add(btnStart);
	}
	
	private void paintInGame() {
		
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
