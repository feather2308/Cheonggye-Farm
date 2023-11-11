package base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class FarmCanvas extends Canvas implements Runnable, MouseListener {
	protected Thread worker;
	protected Point mouseClick = new Point();
	
	protected final int[] mouseClickEffectSize = {2, 4, 6, 8};
	
	protected int mouseClickEffect = -1;
	protected int resolution;
	
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
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		

		g.setColor(Color.BLACK);
		switch(mouseClickEffect) {
		case 0:
			g.drawOval(mouseClick.x - mouseClickEffectSize[0]/2,
					   mouseClick.y - mouseClickEffectSize[0]/2,
					   mouseClickEffectSize[0],
					   mouseClickEffectSize[0]);
			break;
		case 1:
			g.drawOval(mouseClick.x - mouseClickEffectSize[1]/2,
					   mouseClick.y - mouseClickEffectSize[1]/2,
					   mouseClickEffectSize[1],
					   mouseClickEffectSize[1]);
			break;
		case 2:
			g.drawOval(mouseClick.x - mouseClickEffectSize[2]/2,
					   mouseClick.y - mouseClickEffectSize[2]/2,
					   mouseClickEffectSize[2],
					   mouseClickEffectSize[2]);
			break;
		case 3:
			g.drawOval(mouseClick.x - mouseClickEffectSize[3]/2,
					   mouseClick.y - mouseClickEffectSize[3]/2,
					   mouseClickEffectSize[3],
					   mouseClickEffectSize[3]);
			break;
		}
		
//		g.
//		//네모 박스 스폰지밥
//		g.setColor(Color.cyan);
//		g.fillRect(0, 0, 100, 100);
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
