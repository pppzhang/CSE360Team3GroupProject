import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PigEngine extends Thread {

	private PigServer server;
	private int numPlayers;
	private boolean rollAgainChoice;
	private boolean chosen;
	private int activePlayer;
	private ArrayList<ArrayList<Integer>> rolls;
	
	public PigEngine(PigServer server, int numPlayers) {
		// TODO Engine
		//"test"
		//all the things
		rolls = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < numPlayers; i++) rolls.add(new ArrayList<Integer>());
		this.server = server;
		this.numPlayers = numPlayers;
	}
	

	public void run() {
		Random rgen = new Random();
		//TODO shuffle order
		server.setOrder(new int[numPlayers]);
		
		for (activePlayer = 0; activePlayer < numPlayers; activePlayer++)
		{
			server.setTurn(activePlayer);
			rollAgainChoice = true;
			ArrayList<Integer> thisRolls = rolls.get(activePlayer);
			while (rollAgainChoice) {
				rollAgainChoice = true;
				chosen = false;
				int roll = rgen.nextInt(6) + 1;
				thisRolls.add(roll);
				int oldRoll = 0;
				if (thisRolls.size() > 1) oldRoll = thisRolls.get(thisRolls.size() - 2);
				server.roll(activePlayer, oldRoll, roll);
				if (roll == 1) break;
				for (int i = 0; i < 30; i++) {
					try {
						sleep(1000);
					} catch (Exception e) {
					}
					if (chosen) break;
				}
			}
		}
		int[] lastValues = new int[numPlayers];
		for (int i = 0; i < numPlayers; i++) lastValues[i] = rolls.get(i).get(rolls.get(i).size() - 1);
		server.reveal(lastValues);
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		server.setTurn(-1);
	}
	
	public void rollAgain(int playerID, boolean choice) {
		if (activePlayer == playerID) {
			rollAgainChoice = choice;
			chosen = true;
		}
	}
}
