
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
import java.util.Scanner;
import java.io.*;
import java.net.URL;
public class PigGUI extends JFrame {
	
	//String[] stats;
	/**
	 * k
	 */
	public PigGUI() {
		//TODO Peng - load stats
		//TODO start GUI
		// Appears on the top bar
		setTitle("Simple example");
		// Size of the window to be rendered
		setSize(300, 200);
		// this centers the window
		setLocationRelativeTo(null);
		// ends the program when we close the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//PigClient.getPigClient(gui, stats)
	}
	
	/**
	 * @param playerIDs b
	 */
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
/*	public void join(PigStats stats){
		
	}*/
	/**
	 * 
	 * @param playerID
	 */
	public void  leave(int playerID){
		
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