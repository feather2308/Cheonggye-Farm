package base;

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
import java.awt.Font;
import java.awt.FontFormatException;

@SuppressWarnings("serial")
public class MyLauncher extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MyLauncher() {
		Font font = createFont();
		
		setResizable(false);
		setTitle("청계 농장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("로고 라벨");
		lblNewLabel.setFont(font);
		lblNewLabel.setBounds(12, 10, 52, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("해상도");
		lblNewLabel_1.setFont(font);
		lblNewLabel_1.setBounds(284, 196, 36, 15);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(font);
		comboBox.setBounds(332, 191, 90, 25);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"960x540", "1280x720", "1600x900", "1920x1080", "2560x1440"}));
		comboBox.setSelectedIndex(1);
		comboBox.setToolTipText("");
		contentPane.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("설명");
		btnNewButton_2.setFont(font);
		btnNewButton_2.setBounds(170, 226, 75,25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("시작");
		btnNewButton.setFont(font);
		btnNewButton.setBounds(260, 226, 75, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("종료");
		btnNewButton_1.setFont(font);
		btnNewButton_1.setBounds(347, 226, 75, 25);
		contentPane.add(btnNewButton_1);
	}
	
	private Font createFont() {
	    try {
	        Font customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resource/MAPLESTORY BOLD.TTF"));
	        customFont = customFont.deriveFont(12f);
	        return customFont;
	    } catch (IOException | FontFormatException e) {
	        System.err.println("폰트를 불러올 수 없습니다: " + e.getMessage());
	        return new Font("굴림", Font.PLAIN, 12);
	    }
	}
}
	
	/*private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object btnNewButton_2;
			if(e.getSource() == btnNewButton_2 ) {
				JOptionPane.showMessageDialog(
						.this,"TEST");
			}
		}
	};
}*/
