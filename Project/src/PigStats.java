
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * PigStats store player statistics and send player statistics to other classes upon request. 
 * @author Peng Zhang
 *
 */
public class PigStats {
	private String username;
	private int totalScore;
	private double averageScore;
	private int numGamesPlayed;
	private int numGamesWon;
	private int numOnesRolled;

	private PigStats() {
		username = JOptionPane.showInputDialog("Enter your username: ");
		//instantiate other instance variables
		totalScore = 0;
		averageScore = 0;
		numGamesPlayed = 0;
		numGamesWon = 0;
		numOnesRolled = 0;		
	}
	
	private PigStats(String username, int totalScore, double averageScore, int numGamesPlayed, int numGamesWon, int numOnesRolled) {
		this.username = username;
		this.totalScore = totalScore;
		this.averageScore = averageScore;
		this.numGamesPlayed = numGamesPlayed;
		this.numGamesWon = numGamesWon;
		this.numOnesRolled = numOnesRolled;
		
	}
	
	
	static public PigStats getPigStats() {
		PigStats player;
		
		//load from file (using FileInputStream/ObjectInputStream and explicit casting readObject to PigStats)
		File f = new File("*.sav");
		if(f.exists()) {
			try {
				FileInputStream readFile = new FileInputStream("*.sav");
				ObjectInputStream read = new ObjectInputStream (readFile);
				
				String pusername = (String) f.getName();
				int ptotalScore = (int)read.readObject();
				double paverageScore = (int)read.readObject();
				int pnumGamesPlayed = (int)read.readObject();
				int pnumGamesWon = (int)read.readObject();
				int pnumOnesRolled = (int)read.readObject();	
				
				player = new PigStats(pusername,ptotalScore,paverageScore,pnumGamesPlayed,pnumGamesWon, pnumOnesRolled);
				
				read.close();
				
			} catch (Exception e){
				e.printStackTrace();
			}
			
			//if error loading file:
			player= new PigStats();
		}
		//if no .sav file exists:
		else {
			player = new PigStats();
		}
		
		return player;
	}
	
	/**
	 * 
	 * @param rolls The die values of the player's rolls.
	 * @param winner Did the player win?
	 */
	public void save(int rolls[], boolean winner) {
		//update instance variables with new data and save to file (create file if cannot find find)
		int rollSize = rolls.length;
		
		if (rolls[rollSize-1] == 1) {
			numOnesRolled++;			
		}
		else {
			for (int i=0; i<rollSize; i++) {
				totalScore = totalScore + rolls[i];
			}
		}

		numGamesPlayed++;
		averageScore = (double) totalScore / numGamesPlayed;
		
		
		if (winner)
			numGamesWon ++;
		
		try {
			FileOutputStream saveFile = new FileOutputStream(username+".sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(totalScore);
			save.writeObject(averageScore);
			save.writeObject(numGamesPlayed);
			save.writeObject(numGamesWon);
			save.writeObject(numOnesRolled);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//PENG: we need one of these for all the existing instance variables.
	public String getUserName(){		
		return username;		
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public double getAverageScore() {
		return averageScore;
	}
	
	public int getNumGamesPlayed() {
		return numGamesPlayed;
	}
	
	public int getNumGamesWon() {
		return numGamesWon;
	}
	
	public int getNumOnesRolled() {
		return numOnesRolled;
	}
}
