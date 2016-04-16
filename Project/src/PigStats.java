
import java.util.ArrayList;

public class PigStats {
	public String username;
	private int score;
	private ArrayList<Integer> rollNumber;
	
	public PigStats (String username) {
		this.username = username;
		this.score = 0;
		this.rollNumber = new ArrayList<>();
	}
	
	public void addRollNumber (int roll) {
		rollNumber.add(roll);
		if (roll == 1)
			this.score = 0;
		else
			this.score = this.score + roll;
	}
	
	public int getScore() {
		return score;
	}
	
	public ArrayList<Integer> getRollNumber() {
		return rollNumber;
	}
}
