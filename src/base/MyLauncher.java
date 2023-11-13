package base;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class MyLauncher extends JFrame {
	protected final String mapleFont = "/resource/MAPLESTORY BOLD.TTF",
						   icon 	 = "/resource/icon32x32.png";

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	private Font font = createFont();

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
		addLauncher();
	}
	
	private Font createFont() {
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
	private void addLauncher() {
		this.getContentPane().removeAll();
		
		setResizable(false);
		setTitle("청계 농장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(this.getBounds().x, this.getBounds().y, 450, 300);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(icon).getPath());
		setIconImage(imageIcon.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(233, 233, 233));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("로고 라벨");
		lblLogo.setFont(font);
		lblLogo.setBounds(12, 10, 52, 15);
		contentPane.add(lblLogo);
		
		JLabel lblResolution = new JLabel("해상도");
		lblResolution.setFont(font);
		lblResolution.setBounds(284, 196, 36, 15);
		lblResolution.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblResolution);
		
		comboBox = new JComboBox();
		comboBox.setFont(font);
		comboBox.setBounds(332, 191, 90, 25);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"960x540", "1280x720", "1600x900", "1920x1080", "2560x1440"}));
		comboBox.setSelectedIndex(1);
		comboBox.setBackground(Color.white);
		contentPane.add(comboBox);
		
		JButton btnDescription = new JButton("설명");
		btnDescription.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	addHelp();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	btnDescription.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnDescription.setBackground(Color.white);
            }
        });
		btnDescription.setFont(font);
		btnDescription.setBounds(220, 226, 65, 25);
		btnDescription.setBackground(Color.white);
		contentPane.add(btnDescription);
		
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
		btnStart.setBounds(290, 226, 65, 25);
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
		btnExit.setBounds(360, 226, 65, 25);
		btnExit.setBackground(Color.white);
		contentPane.add(btnExit);
	}
	
	private void addHelp() {
		this.getContentPane().removeAll();
		
		setTitle("청계 농장 - 도움말");
		setBounds(this.getBounds().x, this.getBounds().y, 300, 500);
		JButton btnBack = new JButton("뒤로가기");
		btnBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	addLauncher();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	btnBack.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnBack.setBackground(Color.white);
            }
        });
		btnBack.setFont(font);
		btnBack.setBounds(this.getWidth()-110, this.getHeight()-80, 80, 25);
		btnBack.setBackground(Color.white);
		contentPane.add(btnBack);
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

		FarmCanvas farmCanvas = new FarmCanvas(resolution);
		farmCanvas.setSize(this.getWidth(), this.getHeight());
		add(farmCanvas);
		farmCanvas.start();
    	contentPane.add(farmCanvas);
	}
}
