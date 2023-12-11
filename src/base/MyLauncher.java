package base;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MyLauncher extends JFrame {
	protected final String mapleFont = "/resource/MAPLESTORY BOLD.TTF",
						   icon 	 = "/resource/icon32x32.png";
	protected final String image_path = "/resource/main.png";
	protected BufferedImage image;

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	public Font font = createFont();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyLauncher frame = new MyLauncher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MyLauncher() {
		setBounds(100, 100, 450, 300);
		try {
			addLauncher();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Font createFont() {
	    try {
	        Font customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(mapleFont));
	        customFont = customFont.deriveFont(12f);
	        return customFont;
	    } catch (IOException | FontFormatException e) {
	        System.err.println("폰트를 불러올 수 없습니다: " + e.getMessage());
	        return new Font("굴림", Font.PLAIN, 12);
	    }
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addLauncher() throws IOException {
		this.getContentPane().removeAll();
		
		setResizable(false);
		setTitle("청계 농장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(this.getBounds().x, this.getBounds().y, 480, 300);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(icon).getPath());
		setIconImage(imageIcon.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(233, 233, 233));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		image = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(image_path)));
		
		JLabel lblLogo = new JLabel(new ImageIcon(image.getScaledInstance(480, 270, Image.SCALE_SMOOTH)));
		lblLogo.setFont(font);
		lblLogo.setBounds(0, 0, 480, 270);
		
		JLabel lblResolution = new JLabel("해상도");
		lblResolution.setFont(font);
		lblResolution.setBounds(325, 205, 36, 15);
		lblResolution.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblResolution);
		
		comboBox = new JComboBox();
		comboBox.setFont(font);
		comboBox.setBounds(370, 200, 90, 25);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"960x540", "1280x720", "1600x900", "1920x1080", "2560x1440"}));
		comboBox.setSelectedIndex(1);
		comboBox.setBackground(Color.white);
		contentPane.add(comboBox);
		
		JButton btnStart = new JButton("시작");
		btnStart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	addMyFarm();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	btnStart.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnStart.setBackground(Color.white);
            }
        });
		btnStart.setFont(font);
		btnStart.setBounds(325, 230, 65, 25);
		btnStart.setBackground(Color.white);
		contentPane.add(btnStart);
		
		JButton btnExit = new JButton("종료");
		btnExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	btnExit.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnExit.setBackground(Color.white);
            }
        });
		btnExit.setFont(font);
		btnExit.setBounds(395, 230, 65, 25);
		btnExit.setBackground(Color.white);
		contentPane.add(btnExit);
		
		contentPane.add(lblLogo);
	}
	
	private void addMyFarm() {
		this.getContentPane().removeAll();
		
		int resolution;
		switch(comboBox.getSelectedIndex()) {
			case 0:
				resolution = 960/16;
				break;
			case 1:
				resolution = 1280/16;
				break;
			case 2:
				resolution = 1600/16;
				break;
			case 3:
				resolution = 1920/16;
				break;
			case 4:
				resolution = 2560/16;
				break;
			default:
				resolution = 1280/16;
				break;
		}
		setBounds(this.getBounds().x, this.getBounds().y, 16*resolution, 9*resolution);

		FarmCanvas farmCanvas = new FarmCanvas(this, resolution);
		farmCanvas.setSize(this.getWidth(), this.getHeight());
		add(farmCanvas);
		farmCanvas.start();
    	contentPane.add(farmCanvas);
	}
}