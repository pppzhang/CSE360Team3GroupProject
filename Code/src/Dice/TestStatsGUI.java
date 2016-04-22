import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;


public class TestStatsGUI {

	private JFrame frame;
	private JLabel dieLabel;
	private PigStats[] player = new PigStats[5];
	private JButton[] btnPlayer = new JButton[5];
	private JLabel[] statsLabel = new JLabel[5];
	private int playerCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestStatsGUI window = new TestStatsGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestStatsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Test PigStats");
		frame.setBounds(100, 100, 860, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		dieLabel = new JLabel("");
		dieLabel.setIcon(new ImageIcon("img/1.png"));
		dieLabel.setBounds(560, 60, 220, 220);
		frame.getContentPane().add(dieLabel);
	
		
		JButton btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rollNumber = rollDie();
				player[playerCount-1].addRollNumber(rollNumber);
				dieLabel.setIcon(new ImageIcon("img/"+rollNumber+".png"));;
				
				int score = player[playerCount-1].getScore();
				statsLabel[playerCount-1].setText("Score: "+score);
				frame.repaint();
			}
		});
		btnRoll.setBounds(610, 320, 117, 25);
		frame.getContentPane().add(btnRoll);
	
		
		JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playerCount++;
				if (playerCount < 6) {
					String username = (String)JOptionPane.showInputDialog(frame,"Username:\n");
					if (username != null && username.length()>0) {
						btnPlayer[playerCount-1] = new JButton(username);
						btnPlayer[playerCount-1].setBounds(56, 100+50*playerCount, 117, 25);
						frame.getContentPane().add(btnPlayer[playerCount-1]);
						
						statsLabel[playerCount-1] = new JLabel("Score:");
						statsLabel[playerCount-1].setBounds(200, 100+50*playerCount, 117, 25);
						frame.getContentPane().add(statsLabel[playerCount-1]);
						frame.repaint();
						
						player[playerCount-1] = new PigStats(username);						
						
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Sorry, seat is not available.");

			}
		});
		btnJoin.setBounds(56, 60, 117, 25);
		frame.getContentPane().add(btnJoin);
		

		

	}
	
	private int rollDie() {
		return (int) (Math.random()*6+1);
	}
}
