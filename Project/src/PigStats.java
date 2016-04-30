import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * PigStats stores player statistics and sends player statistics to other classes upon request. 
 * @author Peng Zhang
 */

public class PigStats {
	private String username;
	private int totalScore;
	private double averageScore;
	private int numGamesPlayed;
	private int numGamesWon;
	private int numOnesRolled;
	
	/**
	 * Constructor for new player, get user name input and initialize variables
	 */
	private PigStats() {
		username = JOptionPane.showInputDialog("Enter your username: ");
		//instantiate other instance variables
		totalScore = 0;
		averageScore = 0;
		numGamesPlayed = 0;
		numGamesWon = 0;
		numOnesRolled = 0;		
	}
	
	/**
	 * Constructor for existing player whose stats were stored in a .passthepig.sav file.
	 * @param username
	 * @param totalScore
	 * @param averageScore
	 * @param numGamesPlayed
	 * @param numGamesWon
	 * @param numOnesRolled
	 */
	private PigStats(String username, int totalScore, double averageScore, int numGamesPlayed, int numGamesWon, int numOnesRolled) {
		this.username = username;
		this.totalScore = totalScore;
		this.averageScore = averageScore;
		this.numGamesPlayed = numGamesPlayed;
		this.numGamesWon = numGamesWon;
		this.numOnesRolled = numOnesRolled;
		
	}
	
	/**
	 * method to get PigStats, search .sav file first, if not found get user name and initialize a new one
	 * @return PigStats
	 */
	public static PigStats getPigStats() {
		PigStats player;
		
		//load from file (using FileInputStream/ObjectInputStream and explicit casting readObject to PigStats)
		
		File dir = new File(".");	//set current directory for search
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		
				
		if(savFile.length > 0) {
			String filename = savFile[0].getName();		// the first .passthepig.sav file is assumed to be one that storing game stats
			String pusername = filename.substring(0,filename.length()-15);
			try {
				FileInputStream readFile = new FileInputStream(filename);
				ObjectInputStream read = new ObjectInputStream (readFile);				

				int ptotalScore = (int)read.readObject();
				double paverageScore = (double)read.readObject();
				int pnumGamesPlayed = (int)read.readObject();
				int pnumGamesWon = (int)read.readObject();
				int pnumOnesRolled = (int)read.readObject();	
				
				player = new PigStats(pusername,ptotalScore,paverageScore,pnumGamesPlayed,pnumGamesWon, pnumOnesRolled);
				
				read.close();
				
			} catch (Exception e){
				e.printStackTrace();
				//if error loading file:
				player= new PigStats();
			}			

		}
		//if no .sav file exists:
		else {
			player = new PigStats();
		}
		
		return player;
	}
	
	/**
	 * save roll results
	 * @param rolls The die values of the player's rolls.
	 * @param winner Did the player win?
	 */
	public void save(ArrayList<Integer> rolls, boolean winner) {
		//update instance variables with new data and save to file (create file if cannot find find)
		int rollSize = rolls.size();
		
		if (rolls.get(rollSize - 1) == 1) {
			numOnesRolled++;			
		}
		else {
			for (int i=0; i<rollSize; i++) {
				totalScore = totalScore + rolls.get(i);
			}
		}

		numGamesPlayed++;
		averageScore = (double) totalScore / numGamesPlayed;
		
		
		if (winner)
			numGamesWon ++;
		
		try {
			FileOutputStream saveFile = new FileOutputStream(username+".passthepig.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(totalScore);
			save.writeObject(averageScore);
			save.writeObject(numGamesPlayed);
			save.writeObject(numGamesWon);
			save.writeObject(numOnesRolled);
			
			save.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Get user name
	 * @return
	 */
	public String getUserName(){		
		return username;		
	}
	
	/**
	 * Get totalScore
	 * @return
	 */
	public int getTotalScore() {
		return totalScore;
	}
	
	/**
	 * Get averageScore 
	 * @return
	 */
	public double getAverageScore() {
		return averageScore;
	}
	
	/**
	 * Get number of games played
	 * @return
	 */
	public int getNumGamesPlayed() {
		return numGamesPlayed;
	}
	
	/**
	 * Get number of games won
	 * @return
	 */
	public int getNumGamesWon() {
		return numGamesWon;
	}
	
	/**
	 * Get number of ones rolled
	 * @return
	 */
	public int getNumOnesRolled() {
		return numOnesRolled;
	}
}
