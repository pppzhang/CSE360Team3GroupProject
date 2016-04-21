
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
	JPanel panel;
	JPanel panel2;
	JButton namePlates;
	JButton startButton;
	JButton leave;
	PigIO IO;
	PigGUI gui;
	PigStats pstats= new PigStats("UserName");
	JTextField text1;
	JTextArea textArea;
	JScrollPane scrollPane;
	private int toggle1;
	/**
	 * k
	 */
	public PigGUI() {
		//TODO Peng - load stats
		//TODO start GUI
		//imagelabel= new JLabel ();
		gui = this;
		list = new ArrayList<JButton>();
		mainn();
		
		
			
		
	}

	private void mainn(){
		panel= new JPanel();
		
		getContentPane().add(panel);
		//getContentPane().add(scrollPane);
		panel.setLayout(null);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		// pears on the top bar
		setTitle("Pass the Pig");
		// Size of the window to be rendered
		setSize(750, 500);
		setLocation(200,200);
		// this centers the window
		setLocationRelativeTo(null);
		// ends the program when we close the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		textArea = new JTextArea(rules());
		
		textArea.setLayout(null);
		//textArea.setLocation(250, 125);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(250, 125, 250, 250);
		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		//textArea.insert(rules(),0);
		//scrollPane = new JScrollPane(textArea); 
		
		//PigClient.getPigClient(gui, stats)
		
		boolean stop = false;
		Rules = new JButton("Rules");
		Rules.setBounds(50, 150, 100, 30);
		Rules.setToolTipText("Rules of the Game");
		Rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//panel.add(new JScrollPane (textArea));
				if (toggle1 % 2 == 0){
					panel.add(textArea);
					
					panel.revalidate();
					validate();
					repaint();
					toggle1 ++;
				}else {
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
	
		
		
		
		Host = new JButton("Host");
		Host.setBounds(250, 400, 100, 30);
		Host.setToolTipText("Host Game");
		Host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					//System.exit(0);
				   IO = new PigServer(gui,pstats);
				   
				   //(PigServer) io).startGame()
			}
			});
		
		Join = new JButton("Join");
		Join.setBounds(500, 400, 100, 30);
		Join.setToolTipText("Join Game");
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
		Stats = new JButton("Stats");
		Stats.setBounds(50, 250, 100, 30);
		Stats.setToolTipText("See Stats of other players");
		Stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					System.exit(0);
				
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
				
				namePlates = new JButton("Name PLates");
				namePlates.setBounds(50,50, 100, 30);
				namePlates.setToolTipText("Rules of the Game");
				namePlates.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
							//System.exit(0);
							
						}
					});
				startButton = new JButton("Start Button");
				startButton.setBounds(250, 400, 100, 30);
				startButton.setToolTipText("Host Game");
				startButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
							
						   IO = new PigServer(gui,pstats);
						   System.exit(0);
						   //(PigServer) io).startGame()
					}
					});
				
				leave = new JButton("Leave");
				leave.setBounds(350, 400, 100, 30);
				leave.setToolTipText("Join Game");
				leave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
							//System.exit(0);
							leave(0);
						}
					});
							
				panel.add(namePlates);
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
		String rules = "Rules blah blah balkd alkjalkjdfjAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAalkajdfkladjfa alkfjakjfalk";
	
		return rules;
	}
	public void setOrder(int [] playerIDs){
		
	}
	/**
	 * 
	 * @param oldValue
	 */
	public void rollOther (int oldValue){
		
	}
	/**
	 * 
	 * @param newValue
	 */
	public void roll(int newValue){
		
	}
	/**
	 * 
	 * @param stats
	 */
	public void join(PigStats stats){
		//listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		
		JButton nameButton= new JButton(stats.username);
		nameButton.setBounds(350, 350, 100, 40);
		nameButton.setToolTipText("Get Player Stats");
		nameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//TODO disply stats
				
			   //(PigServer) io).startGame()
			}
			});
		
		list.add(nameButton);
		//panel2.add(nameButton);
		panel.add(nameButton);
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
			panel.remove(list.get(playerID));
			list.remove(playerID);
		}catch (IndexOutOfBoundsException e){
			
		}
			
		panel.revalidate();
		validate();
		repaint();
	}
	/**
	 * 
	 * @param playerID
	 */
	public void setTurn(int playerID){
		
	}
	/**
	 * 
	 * @param lastValues
	 */
	public void reveal(int [] lastValues){
		
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
}