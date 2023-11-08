package base;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFarm extends JFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFarm frame = new MyFarm(new int[] {100, 100, 80});
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFarm(int[] bound) {
		setTitle("청계 농장");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(bound[0], bound[1], 16*bound[2], 9*bound[2]);

		FarmCanvas farmCanvas = new FarmCanvas();
		add(farmCanvas);
		farmCanvas.start();
	}

}
