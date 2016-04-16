
import java.util.ArrayList;

/**
 * PigStats store player statistics and send player statistics to other classes upon request. 
 * @author Peng Zhang
 *
 */
public class PigStats {
	private String username;
	private int score;
	private ArrayList<Integer> rollNumber;
	
	/**
	 * Constructor needs a player's username. Initialize score and roll results.
	 * @param username
	 */
	public PigStats (String username) {
		this.username = username;
		this.score = 0;
		this.rollNumber = new ArrayList<>();
	}
	
	/**
	 * addRollNumber receives dice roll result and add it to score, reset score to 0 if roll number is 1.
	 * @param roll
	 */
	public void addRollNumber (int roll) {
		rollNumber.add(roll);
		if (roll == 1)
			this.score = 0;
		else
			this.score = this.score + roll;
	}
	
	/**
	 * getScore returns a player's score to caller
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * getRollNumber returns a player's every roll results to caller
	 * @return
	 */
	public ArrayList<Integer> getRollNumber() {
		return rollNumber;
	}
}
