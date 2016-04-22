
/**
 * 
 * @author  
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
	PigIO IO;
	PigGUI gui;
	PigStats pstats=PigStats.getPigStats();
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
	 * k
	 */
	public PigGUI() {
		//TODO Peng - load stats
		grid=100;
		//TODO start GUI
		//imagelabel= new JLabel ();
		gui = this;
		playerList = new ArrayList<Player>();
		getContentPane().setBackground( Color.black );
		mainn();
		
		
			
		
	}
	
	private void mainn(){
		//scroll for rule , stats, or userstats
				
		
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
					textArea=statText();
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
				
				}
			});
		
		
		
		Host = new JButton("Host");
		Host.setBounds(250, 400, 100, 30);
		Host.setToolTipText("Host Game");
		Host.setBackground(Color.WHITE);
		Host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					//System.exit(0);
				   IO = new PigServer(gui,pstats);
				   
				   //(PigServer) io).startGame()
			}
			});
		
		Join = new JButton("Join");
		Join.setBounds(400, 400, 100, 30);
		Join.setToolTipText("Join Game");
		Join.setBackground(Color.WHITE);
		Join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//IO=PigClient.getPigClient(gui, stats);	
				
				if(IO!=null){
					lobby();
				}
				lobby();
				join(pstats);
				//System.exit(0);
				
				
				}
			});
		
		
		
		panel.add(Host);
		panel.add(Join);
		panel.add(Stats);
		panel.add(Rules);
		//pack();
		//setVisible(true);
		//
	}
	
	private void lobby(){
		

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
			
			   IO = new PigServer(gui,pstats);
			   System.exit(0);
			   //(PigServer) io).startGame()k
				}
				});
				
		leave = new JButton("Leave");
		leave.setBounds(400, 400, 100, 30);
		leave.setToolTipText("Leave Game");
		leave.setBackground(Color.WHITE);
		leave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				
				leave(0);
				if(textArea!= null){
					remove(textArea);
					panel.revalidate();
					validate();
					repaint();
				}
			}
		});
						
		panel.add(nameText);
		panel.add(startButton);
		panel.add(leave);
		getContentPane().add(panel);
		panel.revalidate();
		validate();
		repaint();
				
	}
	/**
	 * @param playerIDs b
	 */
	public static String rules(){
		String rules = "Rules\nblah blah balkd alkjalkjdfjAAA"
				+ "aldkfjadlfjad"
				+ "\nladjfladfja"
				+ "\n"
				+ "aldfjkaldkfjal"
				+ "lakjdflajkf"
				+ "\n"
				+ "\n"
				+ ""
				+ "\n"
				+ "lajkdfalfjadfjal;dkfjalfja"
				+ "aldfjl;akj"
				+ "alfjkaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
				+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
				+ "AAAAAAAAalkajdfkladjfa alkfjakjfalk";
	
		return rules;
	}
	
	private JTextArea statText(){
		
		JTextArea statArea = new JTextArea("Stats");//this will call stateToString()
		statArea.setLayout(null);
		//textArea.setLocation(250, 125);
		statArea.setEditable(false);
		statArea.setLineWrap(true);
		statArea.setWrapStyleWord(true);
		statArea.setBounds(250, 125, 250, 250);
		statArea.setBorder(BorderFactory.createLineBorder(Color.red));
		return statArea;
	}
	private String statToString(){
		
		//ToDo 
		
		return null;
	}
	
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
	
	public void disconnect() {
		//TODO goes back to the main panel
	}
	
	public void setOrder(int [] playerIDs){
		//TODO remove namecards and re-add in this order
	}
	/**
	 * 
	 * @param oldValue
	 */
	public void rollOther (int playerID, int oldValue){
		//TODO add old value die to player, move blank die to the right
		//if oldValue == 0, it is the first roll and just add a blank die
		//Keep track of SUM (add int global to player class?) to determine winner at end.
	}
	/**
	 * 
	 * @param newValue
	 */
	public void roll(int newValue){
		//TODO add die face to yourself. ENABLE pass/roll buttons. DISABLE them when they are clicked.
		//keep track of each of these (ArrayList<Integer>)
	}
	/**
	 * 
	 * @param stats
	 */
	public void join(PigStats stats){
		//listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		playerList.add(new Player(stats));
		//panel2.add(nameButton);
		panel.add(playerList.get(playerList.size() - 1).button);//get last added
		getContentPane().add(panel);
		panel.revalidate();
		validate();
		repaint();
		
		
	}
	/**
	 * 
	 * @param playerID
	 */
	public void leave(int playerID){
		
		try{//try/catch is if leave is press after button is already been removed
			panel.remove(playerList.get(playerID).button);
			playerList.remove(playerID);
		}catch (IndexOutOfBoundsException e){
			
		}
		remove(textArea);	
		panel.revalidate();
		validate();
		repaint();
	}
	/**
	 * 
	 * @param playerID
	 */
	public void setTurn(int playerID){
		//TODO indicate whose turn it is
		//if playerID == -1, the game is ended. Take your roll
	}
	/**
	 * 
	 * @param lastValues
	 */
	public void reveal(int [] lastValues){
		//TODO reveal the hidden dice based on these values. Indexes = playerIDs.
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
	public class Player {
		PigStats statistics;
		JButton button;
		JTextArea statArea;
		int toggle1;
		public Player( PigStats stats) {
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
						scroll.setBounds(575, 25, 140, 100);
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
					
				   //(PigServer) io).startGame()
				}
				});
			grid=grid+50;
		}
		private  JTextArea statText(PigStats stats){
			//had to put username first . coudlnt figure it out. 
			String string=String.format(stats.getUserName()+"\nTotal Score :           %1$d\nAverage Score :     %1$d\n# Games Played : %1$d\n# Games Won :     %1$d\n# Ones Rolled :     %1$d", stats.getTotalScore(),stats.getAverageScore(),stats.getNumGamesPlayed(),stats.getNumGamesWon(),stats.getNumOnesRolled());
			JTextArea statArea = new JTextArea(string);//this will call stateToString()
			statArea.setLayout(null);
			statArea.setEditable(false);
			statArea.setLineWrap(true);
			statArea.setWrapStyleWord(true);
			//statArea.setBounds(250, 125, 250, 250);
			statArea.setBorder(BorderFactory.createLineBorder(Color.red));
			
			return statArea;
			
			
		}

	}
}