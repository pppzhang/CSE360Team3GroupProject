import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PigEngine extends Thread {

	private PigServer server;
	private int numPlayers;
	
	public PigEngine(PigServer server, int numPlayers) {
		// TODO Engine
		this.server = server;
		this.numPlayers = numPlayers;
	}
	

	public void run() {
		boolean end = false;
		do
		{
			boolean roll = server.roll(); //TODO add arguments
			if(roll)
			{
				//TODO check if playersLeft?
			}
			else
			{
				if(Rollagain)
				{
					continue; //roll again
				}
				else
				{
					//Bank score
					//check if players left?
					if(playersLeft)
					{
						//pass die
						
					}
					else
					{
						//end
						end = true;
					}
				}
			}
		}
		while(!end);
		
	}
	
	public void rollAgain(int playerID, boolean choice) {
		//TODO
	}
	

}
