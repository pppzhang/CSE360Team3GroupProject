import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PigEngine extends Thread {
	/**
	 * author : Priyanka Pakhale , Nathan Sears
	 */
	
	private PigServer server;
	private int numPlayers;
	private boolean rollAgainChoice;
	private boolean chosen;
	private int activePlayer;
	private int playerIDs[];
	private ArrayList<ArrayList<Integer>> rolls;
	
	/** 
	 * Constructor of PigEngine
	 * @param : server - object of server
	 * @param : numPlayers - Total number of active players
	 */
	public PigEngine(PigServer server, int numPlayers) {
		rolls = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < numPlayers; i++) rolls.add(new ArrayList<Integer>());
		this.server = server;
		this.numPlayers = numPlayers;
		playerIDs = new int[numPlayers];
	}
	

	/** 
	 * Creates random order of player IDs
	 */
	public void run() {
		Random rgen = new Random();
		// shuffle order
		for(int i = 0;i<numPlayers;i++)
		{
			playerIDs[i] = i;
		}
		Random rnd = ThreadLocalRandom.current();
	    for (int i = numPlayers-1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      int a = playerIDs[index];
	      playerIDs[index] = playerIDs[i];
	      playerIDs[i] = a;
	    }
		
		server.setOrder(playerIDs);
		
		for (activePlayer = 0; activePlayer < numPlayers; activePlayer++)
		{
			server.setTurn(playerIDs[activePlayer]);
			rollAgainChoice = true;
			ArrayList<Integer> thisRolls = rolls.get(playerIDs[activePlayer]);
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
	
	/**
	 * @param playerID - Id of the player
	 * @param choice - player's choice (roll again or not)
	 */
	public void rollAgain(int playerID, boolean choice) {
		if (playerIDs[activePlayer] == playerID) {
			rollAgainChoice = choice;
			chosen = true;
		}
	}
}
