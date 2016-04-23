
/**
 * 
 * @author  Phill Reyes, Nathan Sears
 *
 */


import java.awt.event.*;
import java.awt.*;
import java.text.*;

import javax.imageio.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.net.URL;
public class PigGUI extends JFrame {
	
	//String[] stats;
	ArrayList<JButton> list;
	JButton Host;
	JButton Join;
	JButton Stats;
	JButton Rules;
	JLabel imagelabel;
	JLabel panel;
	ImageIcon dice;
	JPanel playerButtons;
	JButton namePlates;
	JButton startButton;
	JButton leave;
	JButton pass;
	JButton rollAgain;
	
	PigIO IO;
	PigGUI gui;
	PigStats pigStats;
	int myPlayerID;
	boolean playState;
	
	JLabel nameText;
	JTextArea textArea;
	JTextArea statArea;
	JScrollPane scroll;
	JLabel imageL;
	private int toggle1;
	ArrayList<Player>playerList;
	//// this to line up user buttons. used in play class
	public int grid;
	
	/**
	 * 
	 */
	public PigGUI() {
		playState = false;
		pigStats=PigStats.getPigStats();
		grid=100;
		//TODO start GUI
		//imagelabel= new JLabel ();
		gui = this;
		playerList = new ArrayList<Player>();
		getContentPane().setBackground( Color.black );
		mainn();
		
		
			
		
	}
	/**\
	 * this i the first jframe that will display rule button,statbutton,host and join buttons
	 */
	
	private void mainn(){
		//scroll for rule , stats, or userstats
		/*if(panel !=null){
				panel.removeAll();
				getContentPane().removeAll();			
		}*/
		setTitle("Pass the Pig");
		
		setSize(750, 500);
		setLocation(200,200);
		
		
		BufferedImage img = null;
	    try {
	        img = ImageIO.read(new File("img/dice.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	   Image imagepanel = img.getScaledInstance(800, 508, Image.SCALE_SMOOTH);
	   
	   ImageIcon imageIcon = new ImageIcon(imagepanel);
	   panel= new JLabel ();
	   panel.removeAll();
	   getContentPane().removeAll();
	   panel.setIcon(imageIcon);
	   getContentPane().add(panel);
	  // setLocationRelativeTo(null);
		
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
		
		boolean stop = false;
		Rules = new JButton("Rules");
		Rules.setBounds(50, 150, 100, 30);
		Rules.setToolTipText("Rules of the Game");
		Rules.setBackground(Color.WHITE);
		Rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//panel.add(new JScrollPane (textArea));
				
				if (toggle1 % 2 == 0){
					//ruleText();
					if(textArea!= null){
						panel.remove(scroll);
						panel.remove(textArea);
						panel.revalidate();
						validate();
						repaint();
					}
					
					textArea=ruleText();
					scroll=new JScrollPane(textArea);
					scroll.setBounds(250, 125, 250, 150);
					panel.add(scroll);
					panel.revalidate();
					validate();
					repaint();
					toggle1 ++;
				
				}else {
					
					panel.remove(scroll);
					panel.remove(textArea);
					panel.revalidate();
					validate();
					repaint();
					toggle1 ++;
				}
				
				//panel.add(scrollPane);
				//pack();
				
				
				}
			});
	
		Stats = new JButton("Stats");
		Stats.setBounds(50, 250, 100, 30);
		Stats.setToolTipText("See Stats of other players");
		Stats.setBackground(Color.WHITE);
		Stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (toggle1 % 2 == 0){
					//ruleText();
					if(textArea!= null){
						panel.remove(scroll);
						panel.remove(textArea);
						panel.revalidate();
						validate();
						repaint();
					}
					textArea=statText(statToString(pigStats));
					scroll=new JScrollPane(textArea);
					//scroll.setBounds(250, 125, 250, 150);// center
					scroll.setBounds(575, 25, 140, 110);//top right
					panel.add(scroll);
					panel.revalidate();
					validate();
					repaint();
					toggle1 ++;
				
				}else {
					
					panel.remove(scroll);
					panel.remove(textArea);
					panel.revalidate();
					validate();
					repaint();
					toggle1 ++;
				}
				
				}
			});
		
		
		
		Host = new JButton("Host");
		Host.setBounds(250, 400, 100, 30);
		Host.setToolTipText("Host Game");
		Host.setBackground(Color.WHITE);
		Host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				lobby(true);
				IO = new PigServer(gui, pigStats);
			}
			});
		
		Join = new JButton("Join");
		Join.setBounds(400, 400, 100, 30);
		Join.setToolTipText("Join Game");
		Join.setBackground(Color.WHITE);
		Join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				IO=PigClient.getPigClient(gui, pigStats);	
				
				if(IO!=null){
					lobby(false);
				}
			}
		});
		
		
		
		panel.add(Host);
		panel.add(Join);
		panel.add(Stats);
		panel.add(Rules);

		panel.revalidate();
		validate();
		repaint();
		//pack();
		//setVisible(true);
		//
	}
	
	private void lobby(boolean isHost){
		

		panel.removeAll();
		getContentPane().removeAll();		
		playerButtons = new JPanel(new GridLayout(0, 1));
		
		nameText = new JLabel("Players In Lobby: ");
		nameText.setHorizontalAlignment(JTextField.CENTER);
		nameText .setBounds(25,25, 150, 75);
		nameText.setForeground(Color.WHITE);

		nameText.setFont(new Font("SansSerif",Font.BOLD, 15));
				
		startButton = new JButton("Start");
		startButton.setBounds(250, 400, 100, 30);
		startButton.setToolTipText("Lets Begin Game");
		startButton.setBackground(Color.WHITE);
		startButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			   ((PigServer) IO).startGame();
				}
				});
				
		leave = new JButton("Leave");
		leave.setBounds(400, 400, 100, 30);
		leave.setToolTipText("Leave Game");
		leave.setBackground(Color.WHITE);
		leave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				if(textArea!= null){
					remove(textArea);
					
					panel.revalidate();
					validate();
					repaint();
				}
				IO.exit();
			}
		});
						
		panel.add(nameText);
		if (isHost)
			panel.add(startButton);
		panel.add(leave);
		getContentPane().add(panel);
		panel.revalidate();
		validate();
		repaint();
				
	}
	
	/**
	 * Returns strings of rules 
	 * @return string
	 */
	public static String rules(){
		String rules = "Rules\n"
				+ "Players take turns. "
				+ "On your turn, you roll the die. "
				+ "After each roll, you decide to either pass or roll again. "
				+ "If you roll a 1 you must pass the die and you score 0. "
				+ "If you choose to pass the die, you score the sum of your rolls and end your turn.";
	
		return rules;
	}
	/**
	 * This will return a JTextArea object with defined attributes 
	 * @param stats
	 * @return
	 */
	private JTextArea statText(String stats){
		
		JTextArea statArea = new JTextArea(stats);//this will call stateToString()
		statArea.setLayout(null);
		//textArea.setLocation(250, 125);
		statArea.setEditable(false);
		statArea.setLineWrap(true);
		statArea.setWrapStyleWord(true);
		statArea.setBounds(250, 125, 250, 250);
		statArea.setBorder(BorderFactory.createLineBorder(Color.red));
		return statArea;
	}
	/**
	 * Will create a formatted string of stats and then return that string. 
	 * @param stats
	 * @return
	 */
	private String statToString(PigStats stats){
			
		//had to put username first . coudlnt figure it out. 
		String string= "" + stats.getUserName()
				+ "\nTotal Score :          " + stats.getTotalScore() 
				+ "\nAverage Score :     " + ((double)((int)(stats.getAverageScore()*100)))/100.0
				+ "\n# Games Played : " + stats.getNumGamesPlayed() 
				+ "\n# Games Won :     " +  stats.getNumGamesWon()
				+ "\n# Ones Rolled :     " + stats.getNumOnesRolled();
		
		return string;
	}
	/**
	 * will create JtextArea object will return set text and aligned object 
	 * @return
	 */
	private JTextArea ruleText(){
		
		JTextArea	ruleArea = new JTextArea(rules());
		ruleArea.setLayout(null);
		//textArea.setLocation(250, 125);
		ruleArea.setEditable(false);
		ruleArea.setLineWrap(true);
		ruleArea.setWrapStyleWord(true);
		//ruleArea.setBounds(250, 125, 250, 250);
		ruleArea.setBorder(BorderFactory.createLineBorder(Color.red));
		//textArea.insert(rules(),0);d
				//scrollPane = new JScrollPane(textArea); 
				
				//PigClient.getPigClient(gui, stats)
		return ruleArea;
	
	}
	/**
	 * This will call mainn() to loop back to first screen
	 */
	public void disconnect() {
		panel.removeAll();
		getContentPane().removeAll();
		
		validate();
		repaint();
		IO = null;
		mainn();
	}
	
	public void setOrder(int [] playerIDs){
		playState = true;
		if (IO instanceof PigServer)
			panel.remove(startButton);
		pass = new JButton("Pass the Die");
		pass.setBounds(200, 400, 100, 30);
		pass.setToolTipText("Lets Begin Game");
		pass.setBackground(Color.WHITE);
		pass.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			rollAgain.setEnabled(false);
			pass.setEnabled(false);
			IO.rollAgain(false);
				}
				});
		rollAgain = new JButton("Roll the Die");
		rollAgain.setBounds(300, 400, 100, 30);
		rollAgain.setToolTipText("Lets Begin Game");
		rollAgain.setBackground(Color.WHITE);
		rollAgain.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			rollAgain.setEnabled(false);
			pass.setEnabled(false);
			IO.rollAgain(true);
				}
				});
		rollAgain.setEnabled(false);
		pass.setEnabled(false);
		
		panel.add(pass);
		panel.add(rollAgain);
		panel.revalidate();
		validate();
		repaint();
		//TODO remove namecards and re-add in this order
	}
	/**
	 * 
	 * @param oldValue
	 */
	public void rollOther (int playerID, int oldValue){
		playerList.get(playerID).score += oldValue;
		if (oldValue != 0) {
			playerList.get(playerID).myRolls.remove(playerList.get(playerID).myRolls.size() - 1);
			playerList.get(playerID).myRolls.add(oldValue);
		}
		playerList.get(playerID).myRolls.add(0);
		playerList.get(playerID).repaint();
	}
	/**
	 * 
	 * @param newValue
	 */
	public void roll(int newValue){
		if (newValue == 1) {
			playerList.get(myPlayerID).score = 0;
		} else {
			playerList.get(myPlayerID).score += newValue;
			rollAgain.setEnabled(true);
			pass.setEnabled(true);
		}
		playerList.get(myPlayerID).myRolls.add(newValue);
		playerList.get(myPlayerID).repaint();
		//TODO add die face to yourself. ENABLE pass/roll buttons. DISABLE them when they are clicked.
	}
	/**
	 * This will add a new player object to arraylist<player . then add last add
	 * player object to lobby 
	 * @param stats
	 */
	public void join(PigStats stats){
		if (stats == pigStats) myPlayerID = playerList.size();
		playerList.add(new Player(stats));
		panel.add(playerList.get(playerList.size() - 1).button);//get last added
		getContentPane().add(panel);
		panel.revalidate();
		validate();
		repaint();
		
		
	}
	/**
	 * If another player leaves 
	 * @param playerID
	 */
	public void leave(int playerID){
		if (playState) return;
		try{//try/catch is if leave is press after button is already been removed
			panel.remove(playerList.get(playerID).button);
			playerList.remove(playerID);
		}catch (IndexOutOfBoundsException e){
			
		}
		if (playerID < myPlayerID) myPlayerID--;
		
		panel.removeAll();
		getContentPane().removeAll();
		validate();
		repaint();
	}
	/**
	 * 
	 * @param playerID
	 */
	public void setTurn(int playerID){
		if (playerID == -1) {
			int maxIndex = 0;
			for (int i = 1; i < playerList.size(); i++) {
				if (playerList.get(i).score > playerList.get(maxIndex).score) maxIndex = i;
			}
			boolean winner = false;
			if (maxIndex == myPlayerID) winner = true;
			pigStats.save(playerList.get(myPlayerID).myRolls, winner);
			nameText.setText("" + playerList.get(maxIndex).statistics.getUserName() + " is the winner!");
		} else {
			nameText.setText("It is " + playerList.get(playerID).statistics.getUserName() + "'s turn.");
		}
	}
	/**
	 * 
	 * @param lastValues
	 */
	public void reveal(int [] lastValues){
		for (int i = 0; i < lastValues.length; i++) {
			if (i != myPlayerID) {
				if (lastValues[i] == 1) playerList.get(i).score = 0;
				else playerList.get(i).score += lastValues[i];
				playerList.get(i).myRolls.add(lastValues[i]);
				playerList.get(i).repaint();
			}
		}
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

		//PigGUI application = new PigGUI();
	
		public void run() {
			PigGUI ex = new PigGUI();
			ex.setVisible(true);
			}
		});
	}
	
	public void refresh() {
		panel.revalidate();
		validate();
		repaint();
	}
	/**
	 * 
	 * @author Phillip Reyes
	 * This class is used to have a list of buttons of player class.
	 *  will have stats and buttton with action listener
	 */
	private class Player {
		public int score;
		public ArrayList<Integer> myRolls;
		private JLabel rollsLab;
		PigStats statistics;
		JButton button;
		JTextArea statArea;
		int toggle1;
		public Player( PigStats stats) {
			myRolls = new ArrayList<Integer>();
			rollsLab = new JLabel();
			rollsLab.setBounds(140, grid, 300, 40);
			panel.add(rollsLab);
			this.statistics = stats;
			statArea=statText(stats);
			button= new JButton(stats.getUserName());
			button.setBounds(35, grid, 100, 40);
			button.setToolTipText("Get Player Stats");
			button.setBackground(Color.WHITE);
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event) {
					//TODO disply stats
					if (toggle1 % 2 == 0){
						//ruleText();
						if(textArea!= null){
							panel.remove(scroll);
							panel.remove(textArea);
							panel.revalidate();
							validate();
							repaint();
						}
						textArea=statText(statistics);
						scroll=new JScrollPane(textArea);
						scroll.setBounds(575, 25, 140, 110);
						panel.add(scroll);
						panel.revalidate();
						validate();
						repaint();
						toggle1 ++;
					
					}else {
						
						panel.remove(scroll);
						panel.remove(textArea);
						panel.revalidate();
						validate();
						repaint();
						toggle1 ++;
					}
				}
				});
			grid=grid+50;
		}
		/**
		 *  This will display the stats via aformmatted string
		 * @param stats
		 * @return
		 */
		private  JTextArea statText(PigStats stats){
			
			JTextArea statArea = new JTextArea(statToString(stats));//this will call stateToString() return formatted stats
			statArea.setLayout(null);
			statArea.setEditable(false);
			statArea.setLineWrap(true);
			statArea.setWrapStyleWord(true);
			//statArea.setBounds(250, 125, 250, 250);
			statArea.setBorder(BorderFactory.createLineBorder(Color.red));
			
			return statArea;
			
			
		}
		
		public void repaint() {
			String str = "";
			for (Integer i : myRolls) {
				if (i == 0) str = str + "? ";
				else str = str + i + " ";
			}
			rollsLab.setText(str);
			refresh();
		}

	}
}