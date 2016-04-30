import java.io.Serializable;

/**
 * PigIO handles input and output commands between players
 * @author Nathan Sears
 */

public interface PigIO {
	
	public static final int PORT = 9898;
	
	public static final int PING = 0;
	public static final int PLAYER_JOINED = 1;
	public static final int PLAYER_LEFT = 2;
	public static final int SET_TURN = 3;
	public static final int SET_ORDER = 4;
	public static final int OTHER_ROLL = 5;
	public static final int SELF_ROLL = 6;
	public static final int REVEAL = 7;
	public static final int ROLL_AGAIN = 8;
	
	/**
	 * Called by a GUI to signal that the player wishes to leave.
	 */
	public void exit();
	
	/**
	 * Called by a GUI when the player choose whether to roll again (true) or pass the die (false).
	 */
	public void rollAgain(boolean choice);
	
	/**
	 * @author Nathan Sears
	 * An object meant for sharing between a PigClient and PigServer.
	 */
	public class PigMsg implements Serializable {
		private static final long serialVersionUID = -7084128846562180341L;
		public int command;
		public int[] args;
		public PigStats stats;
		
		public PigMsg(int command, int[] args) {
			this.command = command;
			this.args = args;
		}
		
		public PigMsg(int command, int arg) {
			this.command = command;
			int[] temp = {arg};
			this.args = temp;
		}
		
		public PigMsg(int command, PigStats stats) {
			this.command = command;
			this.stats = stats;
		}
	}
}