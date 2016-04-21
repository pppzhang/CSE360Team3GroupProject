
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
		
	}
	
	
	static public PigStats getPigStats() {
		//load from file (using FileInputStream/ObjectInputStream and explicit casting readObject to PigStats)
		//if error loading file:
			return (new PigStats());
	}
	
	public String getUserName(){
		
		return username;
		
	}

	
	/**
	 * 
	 * @param rolls The die values of the player's rolls.
	 * @param winner Did the player win?
	 */
	public save(int rolls[], boolean winner) {
		//update instance variables with new data and save to file (create file if cannot find find)
	}
	
	//PENG: we need one of these for all the existing instance variables.
	public int getScore() {
		return score;
	}
	
}
